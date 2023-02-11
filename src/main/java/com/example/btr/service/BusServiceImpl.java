package com.example.btr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btr.model.Bus;
import com.example.btr.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
	private BusRepository busRepository;

	@Override
	public void removeBus(long busId) {
		Bus bus = this.busRepository.findByBusId(busId);
		this.busRepository.delete(bus);
	}

	@Override
	public Bus getBus(long busId) {
		return this.busRepository.findByBusId(busId);
	}

	@Override
	public boolean addBus(Bus bus) {
		this.busRepository.save(bus);
		return true;
	}

	@Override
	public void updateBus(Bus bus) {
		this.busRepository.save(bus);		
	}

	@Override
	public List<Bus> getAllBusDetails() {
		return this.busRepository.findAll();
	}
	
	@Override
	public List<Bus> getBusesByRoute(String startPoint,String endPoint){
		List<Bus> buses = this.busRepository.findAllByStartPoint(startPoint);
		for(Bus b:buses) {
			if(b.getEndPoint()!=endPoint) {
				buses.remove(b);
			}
		}
		return buses;
		//return this.busRepository.findAllByRoute(startPoint, endPoint);
	}
	
	

}
