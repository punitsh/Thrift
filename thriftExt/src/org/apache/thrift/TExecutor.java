package org.apache.thrift;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The executor component that has capability of managing multiple worker threads.
 * @author punit
 *
 * @param <T>
 */
public class TExecutor<T extends TWorker> {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	
	private ExecutorService executorService;
	private List<T> workers;
	
	public TExecutor() {
		super();
	}
	
	public TExecutor(List<T> workers) {
		this();
		this.workers = workers;
	}

	public TExecutor(ExecutorService executorService) {
		this();
		this.executorService = executorService;
	}
	
	public TExecutor(ExecutorService executorService, List<T> workers) {
		this(workers);
		this.executorService = executorService;
	}
	
	/**
	 * Method for adding single worker instance to the existing list of workers.
	 * @param worker
	 */
	public void addWorker(T worker){
		if(this.workers==null){
			this.workers = new ArrayList<T>();
		}
		
		if(worker!=null)
			this.workers.add(worker);
	}
	
	/**
	 * Method for adding multiple worker instance to the existing list of workers.
	 * @param workers
	 */
	public void addWorkers(List<T> workers){
		if(workers!=null
				&& workers.size()>0){
			
			if(this.workers==null){
				this.workers = workers;
			}else{
				this.workers.addAll(workers);
			}	
		
		}
	}
	
	
	/**
	 * Primary method for executing all registered worker threads using the 
	 * provided executor service, if available. This class will use the fixed 
	 * thread pool if no executor service is provided.
	 */
	public void execute(){
		
		LOGGER.debug("CHECKING EXECUTOR SERVICE...");
		
		if(executorService==null){
			
			LOGGER.warn("NO EXECUTOR SERVICE FOUND. Creating FixedThreadPool from workers.");
			executorService = Executors.newFixedThreadPool(workers.size());
			
		}
		
		LOGGER.debug("STARTING "+workers.size()+" SERVERS USING EXECUTOR SERVICE...");
		
		for(T worker : workers){
			
			LOGGER.debug("STARTING SERVER  : "+worker.serverName);
			executorService.execute(worker);
			
		}
		
	}
	
	
	/**
	 * This method can be used for graceful shutdown of the worker threads. 
	 */
	public void stop(){
		
		LOGGER.debug("SHUTTING DOWN ALL WORKERS...");
		
		for(T worker : workers){
			
			try {
			
				worker.stopWorker();
				LOGGER.debug("SHUTTING DOWN WORKER ["+worker.serverName+"]");
				
			} catch (TException e) {
				LOGGER.error("NOT ABLE TO STOP SERVER : "+worker.serverName, e);
			}
		}
		
		LOGGER.debug("SHUTTING DOWN EXECUTORSERVICE...");
		
		if(!executorService.isShutdown())
			executorService.shutdown();
		
		LOGGER.debug("SHUTTING DOWN COMPLETE");
	}
	
	
	
	
	
	
}
