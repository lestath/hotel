package com.lestath.xmlpro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
	/**
	 * 
	 */
	public final static String [] COLUMNS = {"id","full_cost","guest_id","employee_id","room_id","from","to"};

	private static final long serialVersionUID = 1L;
	public static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

	private Long id;
	private BigDecimal fullCost;
	private Guest guest;
	private Employee employee;
	private Room room;
	private Date from;
	private Date to;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getFullCost() {
		return fullCost;
	}

	public void setFullCost(BigDecimal fullCost) {
		this.fullCost = fullCost;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String toString(){
		return "[Transaction : \n id "+getId()+
				"\n fullcost :"+getFullCost().toString()+"\n"+
				" "+getGuest().toString()+ "\n"+
				" "+getEmployee().toString()+"\n"+
				" "+getRoom().toString()+"\n"+
				"from : "+DEFAULT_DATE_FORMAT.format(getFrom())+"\n"+
				"to :  "+DEFAULT_DATE_FORMAT.format(getTo())+"\n"+
				"\n]";
			
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}


	
}
