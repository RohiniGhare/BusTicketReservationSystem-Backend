package com.example.btr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btr.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
	//public List<Bus> findAllByRoute(String startPoint,String endPoint);
	public List<Bus> findAllByStartPoint(String startPoint);
	public List<Bus> findAllByEndPoint(String endPoint);
	public List<Bus> findByBusName(String busName);
	public Bus findByBusId(long busId);

}
