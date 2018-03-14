package com.lestath.xmpro.repository.csv.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCsvManager {
	private MyCsvReader reader;
	private MyCsvWriter writer;

	public MyCsvManager(MyCsvReader reader, MyCsvWriter writer){
		setReader(reader);
		setWriter(writer);
	}
	
	public List<String[]> getAll() throws IOException{
		creation();
		return getReader().read();
	}
	
	public void  delete(int index) throws IOException{
		creation();
		List<String[]> list = getAll();
		list.remove(index+1);
		getWriter().write(list);
	}
	
	public void update(int index, String [] update) throws IOException{
		creation();
		List<String[]> list = getAll();
		list.set(index+1,update);
		getWriter().write(list);
	}
	
	
	public void add(String [] line) throws IOException{
		creation();
		List<String[]> list = getAll();
		list.add(line);
		getWriter().write(list);
	}
	
	private  void creation(){
		File f = new File(getReader().getFilename());
		if(!f.exists()){
			   try {
				f.createNewFile();
				createColumns();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createColumns() throws IOException{
		add(getReader().getColumns());
	}
	
	public MyCsvReader getReader() {
		return reader;
	}
	public void setReader(MyCsvReader reader) {
		this.reader = reader;
	}
	public MyCsvWriter getWriter() {
		return writer;
	}
	public void setWriter(MyCsvWriter writer) {
		this.writer = writer;
	}
}
