package com.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.thrift.TException;

import com.service.exception.NotFoundException;

public class NMGServiceImpl implements com.service.NMGService.Iface {

	private Map<AtomicInteger,String> map = new HashMap<AtomicInteger,String>();
	private AtomicInteger tktId = new AtomicInteger(1); 
	
	@Override
	public boolean ping() throws TException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getStatus(int tktId) throws NotFoundException, TException {
		
		if(map.containsKey(tktId))
			return map.get(new AtomicInteger(tktId));
		 else	
			throw new NotFoundException();
	}

	@Override
	public int orderSupport(String comment) throws NotFoundException,
			TException {
		map.put(tktId, "new");
		return tktId.getAndIncrement();
		
	}

}
