package com.service.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TMultiplexer.MultiplexerArgs;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.registry.URIContext;
import org.apache.thrift.server.TMultiplexingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

import com.service.Constants;
import com.service.FinanceService;
import com.service.HRService;
import com.service.impl.FinanceServiceImpl;
import com.service.impl.HRServiceImpl;

public class Server1<T extends TServerTransport,F extends TProtocolFactory> extends TMultiplexingServer<T, F> {

	public Server1(T serverTransport, F protFactory, String name) {
		super(serverTransport, protFactory, name);
	}

	@Override
	protected List<MultiplexerArgs> getMultiplexerConf() {
		  
		  //list of multiplexer arguments
		  List<MultiplexerArgs> args = new ArrayList<MultiplexerArgs>();
		  
		  // configuring HR service context
		  TProcessor processor = new HRService.Processor<HRServiceImpl>(new HRServiceImpl());
		  URIContext context = new URIContext(Constants.HR_CONTEXT, "HumanResource_Service");
		  MultiplexerArgs arg = new MultiplexerArgs(processor, context);
		  args.add(arg);
		  
		  // configuring FIN service context
		  processor = new FinanceService.Processor<FinanceServiceImpl>(new FinanceServiceImpl());
		  context = new URIContext(Constants.FIN_CONTEXT, "Finance_Service");
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
	      
	      //creating server instance
	      return new TThreadPoolServer(serverArgs);
	}

}
