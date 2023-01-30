package com.accenture.barsservice.jonathan.carl.guerra.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.accenture.barsservice.jonathan.carl.guerra.domain.Request;
import com.accenture.barsservice.jonathan.carl.guerra.exception.BarsException;

public abstract class AbstractInputFile {
	public static final int MIN_BILLING_CYCLE = 1;
	public static final int MAXIMUM_BILLING_CYCLE = 12;
	private File file;
	
	protected AbstractInputFile() {
		//public in UML
		//no-arg
	}
	
	public abstract List<Request> readFile() throws IOException, BarsException;
	
	public File getFile(){
		return file;
		
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
