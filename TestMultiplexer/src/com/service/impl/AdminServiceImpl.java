package com.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.thrift.TException;

import com.service.exception.NotFoundException;

public class AdminServiceImpl implements com.service.AdminService.Iface{
	
	private AtomicInteger orderId = new AtomicInteger(1); 
	private Map<Integer,Integer> extMap = new HashMap<Integer, Integer>();
	
	{
		extMap.put(2328, 4955);
		extMap.put(2336, 4956);
	}
	
	@Override
	public boolean ping() throws TException {
		return true;
	}

	@Override
	public int getExtn(int empId) throws NotFoundException, TException {
		if(extMap.containsKey(empId))
				return extMap.get(empId);
		else
			throw new NotFoundException();
	}

	@Override
	public int orderSupport(String comment) throws NotFoundException, TException {
		return orderId.getAndIncrement();
	}

}
