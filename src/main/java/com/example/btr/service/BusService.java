package com.example.btr.service;

import java.util.List;

import com.example.btr.model.Bus;

public interface BusService {
	
	public void removeBus(long busId);
	public Bus getBus(long busId);
	public boolean addBus(Bus bus);
	public void updateBus(Bus bus);
	public List<Bus> getAllBusDetails();
	public List<Bus> getBusesByRoute(String startPoint,String endPoint);

}
