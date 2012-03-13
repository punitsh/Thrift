package org.apache.thrift.registry;

import java.util.List;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class for generating registry client, registry service(processor)
 * for basic/customized implementation.
 * 
 * @author punit
 *
 */
public final class TRegistryFactory {
	
	/**
	 * Logger
	 */
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	
	/**
	 * Basic or overridden implementation of registry
	 */
	private TRegistryBase registryImpl =  null;
	
	/**
	 * Registry service(processor) instance
	 */
	private TRegistryService<TRegistry> registryService = null;
	
	/**
	 * private constructor
	 */
	private TRegistryFactory() {}
	
	
	/**
	 * Method for generating factory instance for specific implementation of registry interface
	 * @param registryImpl
	 * @return TRegistryFactory
	 */
	public static TRegistryFactory createFactory(TRegistryBase registryImpl){
		TRegistryFactory factory = new TRegistryFactory();
		factory.setRegistryImpl(registryImpl);
		return factory; 
	}
	
	
	/**
	 * Method for generating factory instance for default implementation of registry interface
	 * @param registryImpl
	 * @return TRegistryFactory
	 */
	public static TRegistryFactory createFactory(){ 
		return new TRegistryFactory(); 
	}
	
	/**
	 * Method for generating registry client for same input and output protocol
	 * @param protocol
	 * @return TRegistry
	 */
	public static TRegistry getClient(TProtocol prot) {
		return new TRegistryClient(prot);
	}

	
	/**
	 *  Method for generating registry client for different input and output protocol
	 * @param inputProtocol
	 * @param outputProtocol
	 * @return TRegistry
	 */
	public static TRegistry getClient(TProtocol iprot,	TProtocol oprot) {
		return new TRegistryClient(iprot, oprot);
	}
	
	
	/**
	 * Method for setting custom implementation of registry
	 * @param TRegistryBase
	 */
	private void setRegistryImpl(TRegistryBase impl){
		this.registryImpl = impl;
	}
	
	
	/**
	 * Method for getting service(processor) for underlying registry implementation 
	 * @return TProcessor
	 */
	public TProcessor getService(){
		
		if(registryImpl==null){
			registryImpl = new TRegistryBase();
		}
		
		if(registryService == null){
			registryService = new TRegistryService<TRegistry>(registryImpl); 
		}
		return registryService;
	}
	
	
    /**
     * Method for getting service(processor) after registering the contexts with underlying registry instance. 
     * @param contexts
     * @return TProcessor
     */
	public TProcessor getService(List<URIContext> contexts){
		
		if(registryImpl==null){
			registryImpl = new TRegistryBase();
		}
		
		for(URIContext context : contexts){
			try {
				registryImpl.bind(context);
			} catch (InvalidInputException e) {
				LOGGER.error("ERROR WHILE INITIALISING SERVER. Not able to register context."+e);
			}
		}
		
		if(registryService == null){
			registryService = new TRegistryService<TRegistry>(registryImpl); 
		}
		return registryService;
	}
	
	/**
	 * Method for getting the underlying helper instance.
	 * @return TRegistry.Helper
	 */
	public TRegistry.Helper getHelper(){
		if(registryImpl==null){
			registryImpl = new TRegistryBase();
		}
		
		return registryImpl;
		
	}
	
	

}
