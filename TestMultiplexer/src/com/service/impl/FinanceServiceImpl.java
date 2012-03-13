package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;

import com.service.exception.NotFoundException;

public class FinanceServiceImpl implements com.service.FinanceService.Iface {
	
private Map<Integer,Double> map = new HashMap<Integer,Double>();
	
	{
		map.put(2328, 123456.00);
		map.put(2336, 526698.00);
	}
	
	@Override
	public boolean ping() throws TException {
		return true;
	}

	@Override
	public double getGrossSalary(int empId) throws NotFoundException, TException {
		if(map.containsKey(empId))
			return map.get(empId);
	else
		throw new NotFoundException();
	}

	@Override
	public double getTaxDeductedTillDate(int empId) throws NotFoundException,TException {
		return 123.45;
	}

}
