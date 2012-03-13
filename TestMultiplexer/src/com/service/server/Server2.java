package com.service.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TMultiplexer.MultiplexerArgs;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.registry.URIContext;
import org.apache.thrift.server.TMultiplexingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

import com.service.AdminService;
import com.service.Constants;
import com.service.MarcomService;
import com.service.NMGService;
import com.service.impl.AdminServiceImpl;
import com.service.impl.MarcomServiceImpl;
import com.service.impl.NMGServiceImpl;

public class Server2<T extends TServerTransport,F extends TProtocolFactory> extends TMultiplexingServer<T, F> {

	public Server2(T serverTransport, F protFactory, String name) {
		super(serverTransport, protFactory, name);
	}

	@Override
	protected List<MultiplexerArgs> getMultiplexerConf() {
		  
		  //list of multiplexer arguments
		  List<MultiplexerArgs> args = new ArrayList<MultiplexerArgs>();
		  
		  // configuring ADMIN service context
		  TProcessor processor = new AdminService.Processor<AdminServiceImpl>(new AdminServiceImpl());
		  URIContext context = new URIContext(Constants.ADM_CONTEXT, "Admin_Service");
		  MultiplexerArgs arg = new MultiplexerArgs(processor, context);
		  args.add(arg);
		  
  		  // configuring MARCOM service context
		  processor = new MarcomService.Processor<MarcomServiceImpl>(new MarcomServiceImpl());
		  context = new URIContext(Constants.MAR_CONTEXT, "Marketing_Service");
		  arg = new MultiplexerArgs(processor, context);
		  args.add(arg);
		  
		  // configuring NMG service context
		  processor = new NMGService.Processor<NMGServiceImpl>(new NMGServiceImpl());
		  context = new URIContext(Constants.NMG_CONTEXT, "NMG_Service");
		  arg = new MultiplexerArgs(processor, context);
		  args.add(arg);
		  
		  return args;
		  
	}

	@Override
	protected TServer getServer(TServerTransport serverTransport,
			TProtocolFactory protFactory, TProcessor processor) {
		  
		  //creating server args
	      Args serverArgs =  new Args(serverTransport);
	      serverArgs.protocolFactory(protFactory);
	      serverArgs.transportFactory(new TTransportFactory());
	      serverArgs.processor(processor);
	      serverArgs.minWorkerThreads=1;
	      serverArgs.maxWorkerThreads=5;
	      
	      // creating server
	      return new TThreadPoolServer(serverArgs);
	}

}
