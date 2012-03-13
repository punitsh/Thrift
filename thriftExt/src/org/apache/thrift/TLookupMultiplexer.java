package org.apache.thrift;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.registry.InvalidInputException;
import org.apache.thrift.registry.TRegistryFactory;
import org.apache.thrift.registry.URIContext;

/**
 * Processor class that provides support for lookup registry along with multiplexing capability.
 * 
 * @author punit
 *
 */
public class TLookupMultiplexer extends TMultiplexer {

	TRegistryFactory factory; 
	
	/**
	 * Constructor that accepts a list of <MultiplexerArgs>
	 * to register provided services(processors) with 
	 * lookup registry and multiplexer
	 * 
	 * @param list
	 */
	public TLookupMultiplexer(List<MultiplexerArgs> list) {
		
		//creating multiplexer using arguments
		super(list);
		
		//creating factory
		factory = TRegistryFactory.createFactory();
		
		//creating list of URIContext from argumentList
		List<URIContext> urilist = new ArrayList<URIContext>();
		for(MultiplexerArgs arg : list){
			urilist.add(arg.getUricontext());
		}
		
		//configuring lookup service 
		MultiplexerArgs arg = new MultiplexerArgs(factory.getService(urilist), new URIContext(TConstants.LOOKUP_CONTEXT,"Lookup Service"));
		
		try {
			
			//registering lookup service with multiplexer
			super.registerProcessor(arg);
			
		} catch (InvalidInputException e) {
			LOGGER.error("LOOKUP SERVICE REGISTRATION FAILED. DETAILS : "+e.what+"||"+e.why);
		}
		
	}
	
	
	
	/**
	 * Method for registering a service(processor) against a context with
	 * lookup registry and multiplexer. 
	 */
	public void registerProcessor(MultiplexerArgs args) throws InvalidInputException {
		factory.getHelper().bind(args.getUricontext());	
		super.registerProcessor(args);
		
	}
	
	

}
