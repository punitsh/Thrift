package com.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.thrift.TException;

import com.service.domain.STATUS;
import com.service.exception.NotFoundException;

public class MarcomServiceImpl implements com.service.MarcomService.Iface {

	private Map<AtomicInteger,STATUS> map = new HashMap<AtomicInteger,STATUS>();
	private AtomicInteger leadId = new AtomicInteger(1); 
	
	
	@Override
	public boolean ping() throws TException {
		return true;
	}

	@Override
	public int addLead(String clientName, STATUS status) throws TException {
		map.put(leadId, status);
		return leadId.getAndIncrement();
	}

	@Override
	public boolean setStatus(int leadId, STATUS status) throws NotFoundException, TException {
		
		if(map.containsKey(leadId)){
			
			map.put(new AtomicInteger(leadId), status);
			return true;
			
		} else	
			throw new NotFoundException();
		
	}

	@Override
	public STATUS getStatus(int leadId) throws NotFoundException, TException {
		if(map.containsKey(leadId))
			return map.get(new AtomicInteger(leadId));
		 else	
			throw new NotFoundException();
	}

}
