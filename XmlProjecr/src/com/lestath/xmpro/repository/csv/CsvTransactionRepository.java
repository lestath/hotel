package com.lestath.xmpro.repository.csv;



import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.lestath.xmlpro.model.Employee;
import com.lestath.xmlpro.model.Transaction;
import com.lestath.xmpro.repository.EmployeeRepository;
import com.lestath.xmpro.repository.GuestRepository;
import com.lestath.xmpro.repository.RoomRepository;
import com.lestath.xmpro.repository.TransactionRepository;
import com.lestath.xmpro.repository.csv.core.MyCsvManager;
import com.lestath.xmpro.repository.csv.core.MyCsvReader;
import com.lestath.xmpro.repository.csv.core.MyCsvWriter;


public class CsvTransactionRepository extends CsvRepository<Transaction> implements TransactionRepository {

	public  static String filename = "/tmp/Transaction.csv";
	public static String delimiter= ",";
	public static EmployeeRepository emprepo = new CsvEmployeeRepository();
	public static RoomRepository roomrepo = new CsvRoomRepository();
	public static GuestRepository guestrepo = new CsvGuestRepository();
	public static SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
	
	

	public CsvTransactionRepository(){
	 super(
			 new MyCsvManager(
					 	new MyCsvReader(filename,delimiter,Transaction.COLUMNS),	
					 	new MyCsvWriter(filename,delimiter,Transaction.COLUMNS)
					 ),
					 new ArrayList<Transaction>()
			 );	
	}

	
	@Override
	protected Transaction csvToObj(String [] cols){
		Transaction emp = new Transaction();
		emp.setId(Long.parseLong(cols[0]));
		emp.setFullCost(new BigDecimal(cols[1]));
		emp.setGuest(getGuestrepo().findById(Long.parseLong(cols[2])));
		emp.setEmployee(getEmprepo().findById(Long.parseLong(cols[3])));
		emp.setRoom(getRoomrepo().findById(Long.parseLong(cols[4])));
		try {
			emp.setFrom(dt.parse(cols[5]));
			emp.setTo(dt.parse(cols[6]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	
	@Override
	protected String [] objToCsv(Transaction obj){
		String [] s = new String[7];
		s[0] = Long.toString(obj.getId());
		s[1]=obj.getFullCost().toString();
		s[2]=Long.toString(obj.getGuest().getId());
		s[3]=Long.toString(obj.getEmployee().getId());
		s[4]=Long.toString(obj.getRoom().getId());
		s[5]=dt.format(obj.getFrom());
		s[6]= dt.format(obj.getTo());
		return s;
	}


	@Override
	public void delete(Transaction obj) {
		setEntities((ArrayList<Transaction>)findAll());
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
	public Transaction findById(Long id) {
		Transaction emp=null;
		setEntities((ArrayList<Transaction>)findAll());
		for(Transaction e : getEntities()){
			if(e.getId()==id) return e;
		}
		return emp;
	}


	@Override
	public void update(Transaction obj) {
		setEntities((ArrayList<Transaction>) findAll());
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


	public static EmployeeRepository getEmprepo() {
		return emprepo;
	}


	public static void setEmprepo(EmployeeRepository emprepo) {
		CsvTransactionRepository.emprepo = emprepo;
	}


	public static RoomRepository getRoomrepo() {
		return roomrepo;
	}


	public static void setRoomrepo(RoomRepository roomrepo) {
		CsvTransactionRepository.roomrepo = roomrepo;
	}


	public static GuestRepository getGuestrepo() {
		return guestrepo;
	}


	public static void setGuestrepo(GuestRepository guestrepo) {
		CsvTransactionRepository.guestrepo = guestrepo;
	}
	
	

}
