package com.service.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TExecutor;
import org.apache.thrift.TWorker;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.service.Constants;

public class Executor {

	
	public static void main(String[] args) {

		try {
			

			//identifying server transport
			TServerSocket SERVER1_TRANSPORT = new TServerSocket(Constants.SERVICE1_PORT);
			TServerSocket SERVER2_TRANSPORT = new TServerSocket(Constants.SERVICE2_PORT);

			//identifying server protocol
			Factory SERVER1_FACTORY = new TBinaryProtocol.Factory();
			Factory SERVER2_FACTORY = new TBinaryProtocol.Factory();

			//creating server instances for specific transport and protocol
			Server1<TServerSocket, TBinaryProtocol.Factory> server1 = 
				new Server1<TServerSocket, TBinaryProtocol.Factory>(SERVER1_TRANSPORT, SERVER1_FACTORY, "SERVER_1");
			
			Server2<TServerSocket, TBinaryProtocol.Factory> server2 = 
				new Server2<TServerSocket, TBinaryProtocol.Factory>(SERVER2_TRANSPORT, SERVER2_FACTORY,"SERVER_2");

			//creating workers
			List<TWorker> workers = new ArrayList<TWorker>();
			workers.add(server1.getWorker());
			workers.add(server2.getWorker());
			
			//using executor component to start server
			TExecutor<TWorker> executor = new TExecutor<TWorker>(workers);
			executor.execute();

		} catch (TTransportException e) {
			System.out.println("ERROR : " + e.getMessage());
		}

	}

}
