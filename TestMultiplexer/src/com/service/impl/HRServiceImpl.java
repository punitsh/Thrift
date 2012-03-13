package com.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.thrift.TException;

import com.service.domain.Employee;
import com.service.exception.InvalidInputException;

public class HRServiceImpl implements com.service.HRService.Iface {

	private AtomicInteger empId = new AtomicInteger(0);
	
	@Override
	public boolean ping() throws TException {
			return true;
	}

	@Override
	public int registerEmployee(Employee employee)
			throws InvalidInputException, TException {
		return empId.incrementAndGet();
	}

	@Override
	public Employee getEmployee(int empId) throws TException {
		return new Employee();
	}

}
