package com.lestath.xmlpro.model;

import java.io.Serializable;

public class Guest extends Person implements Serializable{

	/**
	 * 
	 */
	public final static String [] COLUMNS = {"id","name","surname"};

	private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "[guest : "+super.toString()+"]";
		
	}

}
