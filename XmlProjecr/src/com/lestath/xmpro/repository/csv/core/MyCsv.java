package com.lestath.xmpro.repository.csv.core;

import java.io.File;

public class MyCsv {

	private File csvFile;
	private String filename;
	private String spliter;
	private String [] columns;
	
	public MyCsv(String filename, String spliter, String[] columns){
		setFilename(filename);
		setCsvFile(new File(getFilename()));
		setSpliter(spliter);
		setColumns(columns);
	}

	public File getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSpliter() {
		return spliter;
	}

	public void setSpliter(String spliter) {
		this.spliter = spliter;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	
	
	
	
}
