package com.example.btr.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btr.model.Bus;
import com.example.btr.model.TicketBooking;
import com.example.btr.repository.BusRepository;
import com.example.btr.repository.TicketBookingRepository;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {
	
	@Autowired
	private TicketBookingRepository bookingRepository;
	@Autowired
	private BusRepository busRepository;
	//private BusService busService;

	@Override
	public void addBooking(TicketBooking ticketbooking) {
		this.bookingRepository.save(ticketbooking);
	}

	@Override
	public List<TicketBooking> displayAllBookings() {
		return this.bookingRepository.findAll();
	}
	
	@Override
	public List<Bus> busesSuitable(TicketBooking ticketBooking){
//		List<Bus> buslist = this.busService.getBusesByRoute(ticketBooking.getFromDest(), ticketBooking.getToDest());
//		return buslist;
		List<Bus> buses = this.busRepository.findAllByStartPoint(ticketBooking.getFromDest());
//		for(Bus b:buses) {
//			if(b.getEndPoint()!=ticketBooking.getToDest()) {
//				buses.remove(b);
//			}
//		}
		
		Iterator<Bus> it = buses.iterator();
	    while(it.hasNext()) {
	    	Bus b = it.next();
	    	
	    	if(!b.getEndPoint().equalsIgnoreCase(ticketBooking.getToDest())) {
	    		it.remove();
	    	}
	    }
		return buses;
	}
	
}
