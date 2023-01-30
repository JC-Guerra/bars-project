package com.accenture.barsservice.jonathan.carl.guerra.factory;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.barsservice.jonathan.carl.guerra.controller.BarsController;
import com.accenture.barsservice.jonathan.carl.guerra.exception.BarsException;
import com.accenture.barsservice.jonathan.carl.guerra.file.AbstractInputFile;
import com.accenture.barsservice.jonathan.carl.guerra.file.CSVInputFileImpl;
import com.accenture.barsservice.jonathan.carl.guerra.file.TextInputFileImpl;


public class InputFileFactory {

	private static InputFileFactory factory;
	

	private InputFileFactory() {

	}

	public static InputFileFactory getInstance() {

		if (factory == null) {
			factory = new InputFileFactory();
		}
		return factory;
	}

	public AbstractInputFile getInputFile(File file) {

		Logger log = LoggerFactory.getLogger(BarsController.class);
		
		String fileName = file.getName();

		
		log.info(">>> Checking file type validity <<<");
		
		//verify file type
		if (file.exists()) {
			if(file.length()==0) {
				log.info(BarsException.NO_REQUESTS_TO_READ);
				throw new BarsException(BarsException.NO_REQUESTS_TO_READ);
			} else{
				
				if (fileName.endsWith("txt")) {
					
					return new TextInputFileImpl();
				} else if (fileName.endsWith("csv")){
					
					return new CSVInputFileImpl();
				}
				else {
					log.info(BarsException.FILE_NOT_SUPPORTED);
					throw new BarsException(BarsException.FILE_NOT_SUPPORTED);
				}
			}
		} else {
			log.info(BarsException.PATH_DOES_NOT_EXIST);
			throw new BarsException(BarsException.PATH_DOES_NOT_EXIST);
		}
	}

}
