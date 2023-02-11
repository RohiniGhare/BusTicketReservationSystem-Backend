package com.example.btr.model;

/*import java.text.SimpleDateFormat;*/
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketBookingId;
	private String fromDest;
	private String toDest;
	private Date date;	
	private long customerId;
	private int noOfPassengers;
	private double totalPrice;
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	TicketBooking(){
		/*SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");*/
	}

	public long getTicketBookingId() {
		return ticketBookingId;
	}
	public void setTicketBookingId(long ticketBookingId) {
		this.ticketBookingId = ticketBookingId;
	}
	
	public String getFromDest() {
		return fromDest;
	}

	public void setFromDest(String fromDest) {
		this.fromDest = fromDest;
	}

	public String getToDest() {
		return toDest;
	}

	public void setToDest(String toDest) {
		this.toDest = toDest;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
