package com.accenture.barsservice.jonathan.carl.guerra.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.barsservice.jonathan.carl.guerra.domain.Record;
import com.accenture.barsservice.jonathan.carl.guerra.domain.Request;
import com.accenture.barsservice.jonathan.carl.guerra.entity.Billing;
import com.accenture.barsservice.jonathan.carl.guerra.exception.BarsException;
import com.accenture.barsservice.jonathan.carl.guerra.factory.InputFileFactory;
import com.accenture.barsservice.jonathan.carl.guerra.file.AbstractInputFile;
import com.accenture.barsservice.jonathan.carl.guerra.repository.BillingRepository;

@Component
public class FileProcessor {

	@Autowired
	private BillingRepository billingRepository;
	private InputFileFactory inputFileFactory;

	public FileProcessor() {
		//non-arg
	}

	public List<Request> execute(File file) throws BarsException, IOException {
		

		// get instance of InputFileFactory singleton
		inputFileFactory = InputFileFactory.getInstance();

		// setFile method
		AbstractInputFile abstractInputFile = inputFileFactory.getInputFile(file);
		abstractInputFile.setFile(file);

		// get array list
		List<Request> requests = abstractInputFile.readFile();

		// return list of request
		return requests;

	}

	public List<Record> retrieveRecordfromDB(List<Request> requests) 
			throws BarsException{
		Logger log = LoggerFactory.getLogger(BarsController.class);
		List<Record> records = new ArrayList<>();

		for (Request request : requests) {
			Billing billing = billingRepository.
					findByBillingCycleAndStartDateAndEndDate(
					request.getBillingCycle(),
					request.getStartDate(),
					request.getEndDate());
			
			if (billing != null) {

				Record record = new Record();
				record.setBillingCycle(billing.getBillingCycle());
				record.setStartDate(billing.getStartDate());
				record.setEndDate(billing.getEndDate());
				record.setAccountName(billing.getAccountId().getAccountName());
				record.setAmount(billing.getAmount());
				record.setFirstName(billing.getAccountId().getCustomerId().getFirstName());
				record.setLastName(billing.getAccountId().getCustomerId().getLastName());
				records.add(record);
			} else {
				log.info(BarsException.NO_RECORDS_TO_WRITE);
				throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
			}			
		}
		return records;
	}

}
