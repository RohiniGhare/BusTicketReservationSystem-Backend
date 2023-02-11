package com.example.btr.service;

import java.util.List;

import com.example.btr.model.Passenger;

public interface PassengerService {
	
	public boolean addPassenger(Passenger pas);
	public void updatePassenger(Passenger passenger);
	public List<Passenger> listPassenger();
	public Passenger getPassenger(long passengerId);
	public void removePassenger(long passengerId);
	public List<Passenger> getPassengerByCustomer(long customerId);

}
