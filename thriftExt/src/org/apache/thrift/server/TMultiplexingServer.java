package org.apache.thrift.server;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.TLookupMultiplexer;
import org.apache.thrift.TMultiplexer.MultiplexerArgs;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TWorker;
import org.apache.thrift.protocol.TMultiplexProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This abstract class facilitates user to quickly create and configure lookup multiplexing server
 * that can use varied server transport and protocol.
 * 
 * @author punit
 * @param <TServerTransport>
 * @param <TProtocolFactory>
 */
public abstract class TMultiplexingServer<T extends TServerTransport,F extends TProtocolFactory > {
	
	/**
	 * Logger
	 */
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	
	/**
	 * worker instance
	 */
	protected TWorker worker;
	
	/**
	 * Constructor using arguments
	 * @param serverTransport
	 * @param protocolFactory
	 * @param serverName
	 */
	public TMultiplexingServer (T serverTransport, F protFactory, String serverName){
		
		// getting protocol factory
		TProtocolFactory tProtFactory = getProtocolFactory(protFactory);
		
		//getting processor
		TProcessor processor = getProcessor(getMultiplexerConf());
		
		//getting server
		TServer server = getServer(serverTransport,tProtFactory, processor);
		
		//creating worker instance
		worker = new Worker(server, serverName);
	}
	
	/**
	 * This method should provide the list of multiplexer arguments that
	 * can be used to configure multiplexer instance.  
	 * @return List of MultiplexerArgs
	 */
	protected abstract List<MultiplexerArgs> getMultiplexerConf();
	
	
	/**
	 * Method that should return the desired server instance for the provided arguments.
	 * @param serverTransport
	 * @param protFactory
	 * @param processor
	 * @return TServer
	 */
	protected abstract TServer getServer( TServerTransport serverTransport,TProtocolFactory protFactory,TProcessor processor);
	
	
	/**
	 * Method for getting lookup multiplexer instance based on the configurations
	 * @param args
	 * @return TProcessor
	 */
	private static TProcessor getProcessor(List<MultiplexerArgs> args){
		return new TLookupMultiplexer(args);
	}
	
	
	/**
	 * Method for getting multiplex protocol instance for the underlying server protocol
	 * @param baseFactory - underlying server protocol factory
	 * @return TProtocolFactory
	 */
	private TProtocolFactory getProtocolFactory(F baseFactory) {
		return new TMultiplexProtocol.Factory(baseFactory);
		
	}
	
	
	/**
	 * Method that returns the TWorker instance that can be used
	 * by executor component.
	 * @return TWorker instance
	 */
	public TWorker getWorker(){
		return worker;
	}
	
	
	
	
	/**
	 * Worker class for the server for easy start and shutdown of
	 * server using executor component.
	 * @author punit
	 *
	 */
	private class Worker extends TWorker {
		
		public Worker(TServer server, String name) {
			super(server, name);
		}

		@Override
		public void startWorker(TServer server) throws TException {
			
			if(server!=null) 
				server.serve();
			else 
				throw new TException("NO SERVER INSTANCE FOUND.");
			
		}

		@Override
		public void stopWorker() throws TException {
			if(server!=null && server.isServing()){
				server.stop();
			}
			
		}
		
	}
	

}
