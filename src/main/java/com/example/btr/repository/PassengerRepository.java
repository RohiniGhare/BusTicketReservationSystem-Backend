package com.example.btr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btr.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	public List<Passenger> findAllByCustomerId(long customerId);
	public Passenger findByEmailId(String emailId);
	public Passenger findByPassengerId(long passengerId);

}
