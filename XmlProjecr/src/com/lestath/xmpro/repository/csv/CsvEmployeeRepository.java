package com.lestath.xmpro.repository.csv;


import java.awt.List;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.lestath.xmlpro.model.Employee;
import com.lestath.xmlpro.model.Transaction;
import com.lestath.xmpro.repository.EmployeeRepository;
import com.lestath.xmpro.repository.csv.core.MyCsvManager;
import com.lestath.xmpro.repository.csv.core.MyCsvReader;
import com.lestath.xmpro.repository.csv.core.MyCsvWriter;


public class CsvEmployeeRepository extends CsvRepository<Employee> implements EmployeeRepository {

	public  static String filename = "/tmp/Employee.csv";
	public static String delimiter= ",";

	public CsvEmployeeRepository(){
		 super(
				 new MyCsvManager(
						 	new MyCsvReader(filename,delimiter,Employee.COLUMNS),	
						 	new MyCsvWriter(filename,delimiter,Employee.COLUMNS)
						 ),
				 new ArrayList<Employee>()
				 );	
	}

	
	@Override
	protected Employee csvToObj(String [] cols){
		Employee emp = new Employee();
		emp.setId(Long.parseLong(cols[0]));
		emp.setName(cols[1]);
		emp.setSurname(cols[2]);
		emp.setSalaryStandard(new BigDecimal(cols[3]));
		return emp;
	}
	
	@Override
	protected String [] objToCsv(Employee obj){
		String [] s = new String[4];
		s[0] = Long.toString(obj.getId());
		s[1]=obj.getName();
		s[2]=obj.getSurname();
		s[3]=obj.getSalaryStandard().toString();
		return s;
	}


	@Override
	public void delete(Employee obj) {
		setEntities((ArrayList<Employee>) findAll());
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
	public Employee findById(Long id) {
		Employee emp=null;
		setEntities((ArrayList<Employee>) findAll());
		for(Employee e : getEntities()){
			if(e.getId()==id) return e;
		}
		return emp;
	}


	@Override
	public void update(Employee obj) {
		setEntities((ArrayList<Employee>) findAll());
		for(int i=1;i<getEntities().size();i++){
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
