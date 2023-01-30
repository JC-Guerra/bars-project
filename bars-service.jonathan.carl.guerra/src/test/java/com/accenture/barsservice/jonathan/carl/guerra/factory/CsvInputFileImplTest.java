package com.accenture.barsservice.jonathan.carl.guerra.factory;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.accenture.barsservice.jonathan.carl.guerra.domain.Request;
import com.accenture.barsservice.jonathan.carl.guerra.exception.BarsException;
import com.accenture.barsservice.jonathan.carl.guerra.file.CSVInputFileImpl;

public class CsvInputFileImplTest {

	@Test
	public List<Request> testReadValidRequestParameter() throws BarsException, IOException {
		
		CSVInputFileImpl csvInputFileImpl = new CSVInputFileImpl();
		
		String file = "01,01/15/2013,02/14/2013\n"
				+ "01,01/15/2016,02/14/2016";
		
		return csvInputFileImpl.readFile();
		
		
		
	}
	
	public void testInvalidBillingCycleParameter() {
		
	}
	
	public void testStartDateFormatParameter() {
		
	}
	
	public void testEndDateFormatParameter() {
		
	}
}
