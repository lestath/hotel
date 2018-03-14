package com.lestath.xmlpro.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Employee extends Person implements Serializable {
	/**
	 * 
	 */
	public final static String [] COLUMNS = {"id","name","surname","standard_salary"};
	
	private static final long serialVersionUID = 1L;
	BigDecimal salaryStandard;
	
	public BigDecimal getSalaryStandard() {
		return salaryStandard;
	}
	public void setSalaryStandard(BigDecimal salaryStandard) {
		this.salaryStandard = salaryStandard;
	}
	
	public String toString(){
		return "[employee : "+super.toString()+"]";
		
	}
	
}
