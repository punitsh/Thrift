package org.apache.thrift;

import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract worker class that provides the facility to start
 * and stop a TServer instance.
 * 
 * @author punit
 *
 */
public abstract class TWorker implements Runnable {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	
	protected TServer server;
	protected String serverName;
	
	public TWorker(TServer server, String serverName) {
		this.server = server;
		this.serverName = serverName;
	}
	
	@Override
	public void run() {
		try {
			
			startWorker(server);
			
		} catch (TException e) {
			LOGGER.error("NOT ABLE TO START SERVER : "+serverName,e);
		}
	}
	
	
	
	/**
	 * This method should implement the desired startup code to start the server.
	 * @param server
	 * @throws TException
	 */
	public abstract void startWorker(TServer server) throws TException;
	
	
	
	/**
	 * This method should implement the desired shutdown code for graceful shutdown of the server.
	 * @throws TException
	 */
	public abstract void stopWorker() throws TException;
}
