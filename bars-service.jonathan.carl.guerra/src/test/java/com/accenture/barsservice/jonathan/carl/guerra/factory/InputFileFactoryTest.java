package com.accenture.barsservice.jonathan.carl.guerra.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InputFileFactoryTest {

	@Test
	public void testGetInstance() {
		
		assertEquals(InputFileFactory.getInstance() != null, InputFileFactory.getInstance());
		
	}
	
	public void testGetInputFileTxt() {
		
	}
	
	public void testGetInputCsvI() {
		
	}
	
	public void testFileNotSupported() {
		
	}
}
