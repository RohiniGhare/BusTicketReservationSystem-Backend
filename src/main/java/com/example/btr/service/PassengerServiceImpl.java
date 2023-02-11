package com.example.btr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btr.model.Passenger;
import com.example.btr.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	@Transactional
	public boolean addPassenger(Passenger pas) {
		//Passenger checkPassenger = this.passengerRepository.findByEmailId(pas.getEmailId());
		if(pas != null) {
			this.passengerRepository.save(pas);
			return true;
		}
		return false;
	}

	@Override
	public void updatePassenger(Passenger passenger) {
		this.passengerRepository.save(passenger);
	}

	@Override
	public List<Passenger> listPassenger() {
		return this.passengerRepository.findAll();
	}

	@Override
	public Passenger getPassenger(long passengerId) {
		return passengerRepository.findByPassengerId(passengerId);
	}

	@Override
	public void removePassenger(long passengerId) {
		Passenger pass = this.passengerRepository.findByPassengerId(passengerId);
		if(pass!=null) {
			this.passengerRepository.delete(pass);
		}
	}

	@Override
	public List<Passenger> getPassengerByCustomer(long customerId) {
		return this.passengerRepository.findAllByCustomerId(customerId);
	}

}
