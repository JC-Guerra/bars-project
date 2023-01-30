package com.accenture.barsservice.jonathan.carl.guerra.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.barsservice.jonathan.carl.guerra.controller.BarsController;
import com.accenture.barsservice.jonathan.carl.guerra.domain.Request;
import com.accenture.barsservice.jonathan.carl.guerra.exception.BarsException;

public class TextInputFileImpl extends AbstractInputFile{

	Logger log = LoggerFactory.getLogger(BarsController.class);
	int billingCycle;
	int rowNumber = 0;
	LocalDate startDate, endDate;
	
	public TextInputFileImpl() {
		// no-arg
	}

	@Override
	public List<Request> readFile() throws IOException, BarsException{

		List<Request> requests = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
//		create a fileReader
		FileReader fileReader = new FileReader(super.getFile());

//		create a bufferedReader
		String line;
		
		log.info(">>> PROCESSING TXT FILE <<<");

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		try {
		while ((line = bufferedReader.readLine()) != null) {

			rowNumber++;
			log.info("Processing row number " + rowNumber);

			final int firstBillingChar = 0;
			final int secondBillingChar = 2;
			final int startChar = 10;
			final int endChar = 18;
			
//          validate billing cycle
			billingCycle = Integer.parseInt
					(line.substring(firstBillingChar, secondBillingChar));
			log.info("the billing cycle is " + billingCycle);

			if (billingCycle < MIN_BILLING_CYCLE ||
					billingCycle > MAXIMUM_BILLING_CYCLE) {

				log.info(BarsException.INVALID_BILLLING_CYCLE);
				throw new BarsException(BarsException.
						INVALID_BILLLING_CYCLE + rowNumber);
			}

//            		validate start date - use try catch     
			try {
				startDate = LocalDate.parse(
						(line.substring(secondBillingChar, startChar)), formatter);
				log.info("start date is " + startDate);

			} catch (DateTimeParseException e) {

				log.info(BarsException.
						INVALID_START_DATE_FORMAT + rowNumber);
				throw new BarsException(BarsException.
						INVALID_START_DATE_FORMAT + rowNumber);
			}

//            		validate end date - use try catch

			try {
				endDate = LocalDate.parse(
					(line.substring
					(startChar, endChar)),
					formatter);

			} catch (DateTimeParseException e) {

				log.info(BarsException.
						INVALID_END_DATE_FORMAT +rowNumber);
				throw new BarsException(BarsException.
						INVALID_END_DATE_FORMAT + rowNumber);
			}

			requests.add(new Request(billingCycle, startDate, endDate));
		}
		return requests;
		} finally {
			
			bufferedReader.close();
		}
		
	}
}
