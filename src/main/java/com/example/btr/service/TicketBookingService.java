package com.example.btr.service;

import java.util.List;

import com.example.btr.model.Bus;
import com.example.btr.model.TicketBooking;

public interface TicketBookingService {
	
	public void addBooking(TicketBooking ticketbooking);
	public List<TicketBooking> displayAllBookings();
	public List<Bus> busesSuitable(TicketBooking ticketBooking);

}
