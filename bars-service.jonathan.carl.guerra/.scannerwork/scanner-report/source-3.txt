package com.accenture.barsservice.jonathan.carl.guerra.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.barsservice.jonathan.carl.guerra.domain.Record;
import com.accenture.barsservice.jonathan.carl.guerra.domain.Request;
import com.accenture.barsservice.jonathan.carl.guerra.exception.BarsException;

@RestController
public class BarsController {

	private static final Logger log 
		= LoggerFactory.getLogger(BarsController.class);

	@Autowired
	private FileProcessor fileProcessor;

	@GetMapping("/bars")
	public List<Record> requestBilling(@RequestParam("filePath") String fileName) 
			throws BarsException, IOException {
		File file = new File("C:\\BARS_TEST\\" + fileName);
		
			log.info(">>> File Path : " + file.getAbsolutePath() + "<<<");
			log.info("File : " + fileName);

		// call execute(file) from FileProcessor Class
		List<Request> requests = fileProcessor.execute(file);

		// get list of records by calling retrieveRecordfromDB(requests)
		List<Record> records = fileProcessor.retrieveRecordfromDB(requests);

		return records;
	}

}
