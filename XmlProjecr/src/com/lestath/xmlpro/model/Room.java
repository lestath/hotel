package com.lestath.xmlpro.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class Room implements Serializable{
	
	/**
	 * 
	 */
	public final static String [] COLUMNS = {"id","room_nr","floor","cost"};
	private static final long serialVersionUID = 1L;
	private Long id;
	private int roomNr;
	private int floor;
	private BigDecimal cost;
	
	public int getRoomNr() {
		return roomNr;
	}
	public void setRoomNr(int roomNr) {
		this.roomNr = roomNr;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public String toString(){
		return 	"[ room : [id :"+getId()+
					" roomNr :"+this.getRoomNr()+
					" floor :"+this.getFloor() +
					" cost : "+this.getCost().toString()+" ]]";
		
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
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
