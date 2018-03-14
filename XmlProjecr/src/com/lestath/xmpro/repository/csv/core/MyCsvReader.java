package com.lestath.xmpro.repository.csv.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCsvReader extends MyCsv {

	
	public MyCsvReader(String filename, String spliter, String [] columns) {
		super(filename, spliter, columns);
		// TODO Auto-generated constructor stub
	}
	
	public List<String [] >read() throws IOException{
		List<String[]> list = new ArrayList<String[]>();
		FileReader reader = new FileReader(getCsvFile());
		BufferedReader br = new BufferedReader(reader);
		String line;
		while((line=br.readLine())!=null){
			list.add(line.split(getSpliter()));
		}
		br.close();
		return list;
	}
	

}
