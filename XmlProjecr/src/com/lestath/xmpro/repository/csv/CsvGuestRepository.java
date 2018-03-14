package com.lestath.xmpro.repository.csv;



import java.io.IOException;
import java.util.ArrayList;

import com.lestath.xmlpro.model.Employee;
import com.lestath.xmlpro.model.Guest;
import com.lestath.xmlpro.model.Transaction;
import com.lestath.xmpro.repository.GuestRepository;
import com.lestath.xmpro.repository.csv.core.MyCsvManager;
import com.lestath.xmpro.repository.csv.core.MyCsvReader;
import com.lestath.xmpro.repository.csv.core.MyCsvWriter;


public class CsvGuestRepository extends CsvRepository<Guest> implements GuestRepository {

	public  static String filename = "/tmp/Guest.csv";
	public static String delimiter= ",";
	

	public CsvGuestRepository(){
		 super(
				 new MyCsvManager(
						 	new MyCsvReader(filename,delimiter,Guest.COLUMNS),	
						 	new MyCsvWriter(filename,delimiter,Guest.COLUMNS)
						 ),
						 new ArrayList<Guest>()
				 );		
	}

	
	@Override
	protected Guest csvToObj(String [] cols){
		Guest emp = new Guest();
		emp.setId(Long.parseLong(cols[0]));
		emp.setName(cols[1]);
		emp.setSurname(cols[2]);
		return emp;
	}
	
	@Override
	protected String [] objToCsv(Guest obj){
		String [] s = new String[3];
		s[0] = Long.toString(obj.getId());
		s[1]=obj.getName();
		s[2]=obj.getSurname();
		return s;
	}


	@Override
	public void delete(Guest obj) {
		setEntities((ArrayList<Guest>) findAll());
		for(int i =0;i<getEntities().size();i++){
			if(obj.equals(getEntities().get(i))){
				try {
					getManager().delete(i+1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
		}
		
	}


	@Override
	public Guest findById(Long id) {
		Guest emp=null;
		setEntities((ArrayList<Guest>) findAll());
		for(Guest e : getEntities()){
			if(e.getId()==id) return e;
		}
		return emp;
	}


	@Override
	public void update(Guest obj) {
		setEntities((ArrayList<Guest>) findAll());
		for(int i=0;i<getEntities().size();i++){
			if(obj.equals(getEntities().get(i))){
				try {
					super.getManager().update(i,objToCsv(obj));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
