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

public class CSVInputFileImpl extends AbstractInputFile {

	Logger log = LoggerFactory.getLogger(BarsController.class);
    int billingCycle;
    int rowNumber = 0;
    LocalDate startDate, endDate;

    public CSVInputFileImpl() {
        // no-arg
    }

    @Override
    public List<Request> readFile() throws IOException, BarsException {

        List<Request> requests = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
//		create a fileReader
        FileReader fileReader = new FileReader(getFile());

//		create a bufferedReader
        String line;

        log.info(">>> PROCESSING CSV FILE <<<");

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
        while ((line = bufferedReader.readLine()) != null) {

        	rowNumber++;
            log.info("Processing row number " + rowNumber);
            String[] data = line.split(",");

//          validate billing cycle

            billingCycle = Integer.parseInt(data[0]);

            if (billingCycle < MIN_BILLING_CYCLE ||
            		billingCycle > MAXIMUM_BILLING_CYCLE) {
            	fileReader.close();
            	bufferedReader.close();
                log.info(BarsException.
                		INVALID_BILLLING_CYCLE + rowNumber);
                throw new BarsException(BarsException.
                		INVALID_BILLLING_CYCLE + rowNumber);
            }

//          validate start date - use try catch
            try {
                startDate = LocalDate.parse(data[1], formatter);
            } catch (DateTimeParseException e) {
            	fileReader.close();
            	bufferedReader.close();
                log.info(BarsException.
                		INVALID_START_DATE_FORMAT + rowNumber);
                throw new BarsException(BarsException.
                		INVALID_START_DATE_FORMAT + rowNumber);
            }

//          validate end date - use try catch

            try {
            	final int dataEndDate = 2;
                endDate = LocalDate.parse(data[dataEndDate], formatter);

            } catch (DateTimeParseException e) {
            	fileReader.close();
            	bufferedReader.close();
                log.info(BarsException.
                		INVALID_START_DATE_FORMAT + rowNumber);
                throw new BarsException(BarsException.
                		INVALID_START_DATE_FORMAT + rowNumber);
            }

            requests.add(new Request(billingCycle, startDate, endDate));
        }
        
        return requests;
        } finally {
			bufferedReader.close();
		}
    }

}
