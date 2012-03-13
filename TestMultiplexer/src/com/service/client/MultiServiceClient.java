package com.service.client;

import java.util.Set;

import org.apache.thrift.TConstants;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexProtocol;
import org.apache.thrift.protocol.TMultiplexProtocol.Factory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.registry.TRegistry;
import org.apache.thrift.registry.TRegistryFactory;
import org.apache.thrift.registry.URIContext;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.service.Constants;
import com.service.HRService;
import com.service.HRService.Client;

public class MultiServiceClient {

	private static final TTransport SERVICE1_TRANSPORT = new TSocket("localhost", 9011, 60);
	private static final TTransport SERVICE2_TRANSPORT = new TSocket("localhost", 9022, 60);
	
	public static void main(String[] args) {
		
		MultiServiceClient client = new MultiServiceClient();
		client.start();
	}
	
	
	private TProtocol getProtocol(TTransport tTransport, String context) {
		return new TMultiplexProtocol(new TBinaryProtocol(tTransport),context);
	}

	private void start() {
			
			TTransport transport =  null; 
		
		try {
			
			transport = SERVICE1_TRANSPORT; 
			TProtocol protocol = Factory.getProtocol(new TBinaryProtocol(transport), TConstants.LOOKUP_CONTEXT);
			TRegistry client = TRegistryFactory.getClient(protocol);
			transport.open();

			boolean status = client.ping();
			System.out.println("Ping status from server 1 lookup service:" + status);

			Set<URIContext> lookups = client.listAll(); 
			System.out.println(" Look ups [Service 1] : "+lookups);
			
			transport.close();
			
			transport = SERVICE2_TRANSPORT; 
			protocol = Factory.getProtocol(new TBinaryProtocol(transport), TConstants.LOOKUP_CONTEXT);
			client = TRegistryFactory.getClient(protocol);
			transport.open();

			status = client.ping();
			System.out.println("Ping status from server 1 lookup service:" + status);

			lookups = client.listAll(); 
			System.out.println(" Look ups [Service 2] : "+lookups);
			
			transport.close();
			
			transport = SERVICE2_TRANSPORT; 
			protocol = getProtocol(transport, Constants.NMG_CONTEXT);
			com.service.NMGService.Client nmgService = new com.service.NMGService.Client(protocol);
			
			transport.open();
			System.out.println("NMG SERVICE ping status: " + nmgService.ping());
			transport.close();
			
			transport = SERVICE1_TRANSPORT;
			protocol = getProtocol(transport, Constants.FIN_CONTEXT);
			com.service.FinanceService.Client finService = new com.service.FinanceService.Client(protocol);
			
			transport.open();
			System.out.println("FINANCE SERVICE ping status: " + finService.ping());
			transport.close();
			
			transport = SERVICE2_TRANSPORT;
			protocol = getProtocol(transport, Constants.ADM_CONTEXT);
			com.service.AdminService.Client admService = new com.service.AdminService.Client(protocol);
			
			transport.open();
			System.out.println("ADMIN SERVICE ping status: " + admService.ping());
			transport.close();
			
			
			transport = SERVICE2_TRANSPORT;
			protocol = getProtocol(transport, Constants.MAR_CONTEXT);
			com.service.MarcomService.Client marService = new com.service.MarcomService.Client(protocol);
			
			transport.open();
			System.out.println("ADMIN SERVICE ping status: " + marService.ping());
			transport.close();
			
			transport = SERVICE1_TRANSPORT;
			protocol = getProtocol(transport, Constants.HR_CONTEXT);
			HRService.Client hrClient = new HRService.Client(protocol);
			Client hrService = new HRService.Client(protocol);
			
			transport.open();
			System.out.println("HR SERVICE ping status: " + hrService.ping());
			transport.close();
		
		
		} catch (TTransportException e) {
			System.out.println(e.getMessage());
		} catch (TException e) {
			System.out.println(e.getMessage());
		} finally {
			
			if(transport!=null)
				transport.close();
		}

	}
	
	
}
