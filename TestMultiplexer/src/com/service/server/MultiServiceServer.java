package com.service.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TLookupMultiplexer;
import org.apache.thrift.TMultiplexer.MultiplexerArgs;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TMultiplexProtocol;
import org.apache.thrift.registry.URIContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import com.service.AdminService;
import com.service.Constants;
import com.service.FinanceService;
import com.service.HRService;
import com.service.MarcomService;
import com.service.NMGService;
import com.service.impl.AdminServiceImpl;
import com.service.impl.FinanceServiceImpl;
import com.service.impl.HRServiceImpl;
import com.service.impl.MarcomServiceImpl;
import com.service.impl.NMGServiceImpl;


public class MultiServiceServer {
	
	private static ExecutorService serverExecutor;
	
	public static abstract class Server implements Runnable {
		
		protected TServer server;
		
		@Override
 		public void run() {

			//prepare server instance
			prepare();
			
			//start serving
			if(server!=null) server.serve();
			else System.out.println("Not able to start server...");
		}
		
		public abstract void prepare();
		
		public void stopServer(){
			if(server!=null && server.isServing()){
				server.stop();
			}
		}
		
	}
	
	private static class Server1 extends Server {
	
	public void prepare() {
		try {
		
		  
		  //list of multiplexer arguments
		  List<MultiplexerArgs> args = new ArrayList<MultiplexerArgs>();
		  
		  //HR service argument
		  TProcessor processor = new HRService.Processor<HRServiceImpl>(new HRServiceImpl());
		  URIContext context = new URIContext(Constants.HR_CONTEXT, "HumanResource_Service");
		  MultiplexerArgs arg = new MultiplexerArgs(processor, context);
		  args.add(arg);
		  
		  processor = new FinanceService.Processor<FinanceServiceImpl>(new FinanceServiceImpl());
		  context = new URIContext(Constants.FIN_CONTEXT, "Finance_Service");
		  arg = new MultiplexerArgs(processor, context);
		  args.add(arg);
		  
		  //getting lookup multiplexer
		  TLookupMultiplexer mProcessor = new TLookupMultiplexer(args);
		  
		  //creating multiplex protocol factory instance
		  Factory binaryFactory = new TBinaryProtocol.Factory();
	      org.apache.thrift.protocol.TMultiplexProtocol.Factory protFactory = new TMultiplexProtocol.Factory(binaryFactory);
	      
	      // getting transport 	
		  TServerSocket serverTransport = new TServerSocket(Constants.SERVICE1_PORT);
	      
	      //creating args
	      Args serverArgs =  new Args(serverTransport);
	      serverArgs.protocolFactory(protFactory);
	      serverArgs.transportFactory(new TTransportFactory());
	      serverArgs.processor(mProcessor);
	      serverArgs.minWorkerThreads=1;
	      serverArgs.maxWorkerThreads=5;
	      
	      //using TSimpleserver
	      server = new TThreadPoolServer(serverArgs);
	      System.out.println("Starting server on port "+Constants.SERVICE1_PORT+" ...");
	       
	    }catch(TTransportException e){
	    	System.out.println(e.getMessage());
	    }
	    
	  }
	
	}
	
	
	private static class Server2 extends Server {
		
		public void prepare() {
			try {
				
				 //getting transport 	
				  TServerSocket serverTransport = new TServerSocket(Constants.SERVICE2_PORT);
				  
				  //list of multiplexer arguments
				  List<MultiplexerArgs> args = new ArrayList<MultiplexerArgs>();
				  
				  //HR service argument
				  TProcessor processor = new AdminService.Processor<AdminServiceImpl>(new AdminServiceImpl());
				  URIContext context = new URIContext(Constants.ADM_CONTEXT, "Admin_Service");
				  MultiplexerArgs arg = new MultiplexerArgs(processor, context);
				  args.add(arg);
				  
				  processor = new MarcomService.Processor<MarcomServiceImpl>(new MarcomServiceImpl());
				  context = new URIContext(Constants.MAR_CONTEXT, "Marketing_Service");
				  arg = new MultiplexerArgs(processor, context);
				  args.add(arg);
				  
				  processor = new NMGService.Processor<NMGServiceImpl>(new NMGServiceImpl());
				  context = new URIContext(Constants.NMG_CONTEXT, "NMG_Service");
				  arg = new MultiplexerArgs(processor, context);
				  args.add(arg);
				  
				  //getting lookup multiplexer
				  TLookupMultiplexer mProcessor = new TLookupMultiplexer(args);
				  
				  //creating multiplex protocol factory instance
				  Factory binaryFactory = new TBinaryProtocol.Factory();
			      org.apache.thrift.protocol.TMultiplexProtocol.Factory protFactory = new TMultiplexProtocol.Factory(binaryFactory);
			      
			      //creating args
			      Args serverArgs =  new Args(serverTransport);
			      serverArgs.protocolFactory(protFactory);
			      serverArgs.transportFactory(new TTransportFactory());
			      serverArgs.processor(mProcessor);
			      serverArgs.minWorkerThreads=1;
			      serverArgs.maxWorkerThreads=5;
			      
			      //using TSimpleserver
			      server = new TThreadPoolServer(serverArgs);
			      System.out.println("Starting server on port "+Constants.SERVICE2_PORT+" ...");
			   
		    }catch(TTransportException e){
		    	System.out.println(e.getMessage());
		    }
		    
		  }

		}
	
	
	private static void execute(List<Runnable> list, ExecutorService executor) {
		executor = Executors.newFixedThreadPool(list.size());
		for (Runnable obj : list ) {
			executor.execute(obj);
		}
	}

	public static void main(String[] args) {
		List<Runnable> servers = new ArrayList<Runnable>();
		servers.add(new Server1());
		servers.add(new Server2());
		execute(servers,serverExecutor);
		
	}
		
}
