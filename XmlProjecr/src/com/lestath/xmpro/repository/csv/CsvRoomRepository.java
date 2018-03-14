package com.lestath.xmpro.repository.csv;



import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.lestath.xmlpro.model.Employee;
import com.lestath.xmlpro.model.Room;
import com.lestath.xmlpro.model.Transaction;
import com.lestath.xmpro.repository.RoomRepository;
import com.lestath.xmpro.repository.csv.core.MyCsvManager;
import com.lestath.xmpro.repository.csv.core.MyCsvReader;
import com.lestath.xmpro.repository.csv.core.MyCsvWriter;


public class CsvRoomRepository extends CsvRepository<Room> implements RoomRepository {

	public  static String filename = "/tmp/Room.csv";
	public static String delimiter= ",";
	

	public CsvRoomRepository(){
		 super(
				 new MyCsvManager(
						 	new MyCsvReader(filename,delimiter,Room.COLUMNS),	
						 	new MyCsvWriter(filename,delimiter,Room.COLUMNS)
						 ),
						 new ArrayList<Room>()
				 );	
	}

	
	@Override
	protected Room csvToObj(String [] cols){
		Room emp = new Room();
		emp.setId(Long.parseLong(cols[0]));
		emp.setRoomNr(Integer.parseInt(cols[1]));
		emp.setFloor(Integer.parseInt(cols[2]));
		emp.setCost(new BigDecimal(cols[3]));
		return emp;
	}
	
	@Override
	protected String [] objToCsv(Room obj){
		String [] s = new String[4];
		s[0] = Long.toString(obj.getId());
		s[1]=Integer.toString(obj.getRoomNr());
		s[2]=Integer.toString(obj.getFloor());
		s[3]=obj.getCost().toString();
		return s;
	}


	@Override
	public void delete(Room obj) {
		setEntities((ArrayList<Room>) findAll());
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
	public Room findById(Long id) {
		Room emp=null;
		setEntities((ArrayList<Room>) findAll());
		for(Room e : getEntities()){
			if(e.getId()==id) return e;
		}
		return emp;
	}


	@Override
	public void update(Room obj) {
		setEntities((ArrayList<Room>) findAll());
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
