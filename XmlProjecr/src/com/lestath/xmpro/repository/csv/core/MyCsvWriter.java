package com.lestath.xmpro.repository.csv.core;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyCsvWriter extends MyCsv{

	public MyCsvWriter(String filename, String spliter, String [] columns) {
		super(filename, spliter,columns);
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<String [] > list) throws IOException{
		FileWriter writer = new FileWriter(getCsvFile());
		BufferedWriter br = new BufferedWriter(writer);
		String line;
		String [] tab;
		if(!list.isEmpty())
			for(int i =0;i<list.size();i++){
				tab = list.get(i);
				br.write(String.join(getSpliter(),tab)+'\n');
			}
		br.flush();
		br.close();
	}
	
	
	
}
