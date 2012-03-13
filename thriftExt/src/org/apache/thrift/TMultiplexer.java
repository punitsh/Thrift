package org.apache.thrift;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.protocol.TMultiplexProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.registry.InvalidInputException;
import org.apache.thrift.registry.URIContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This processor class provides support for hosting multiple services on a server. 
 * It acts as a server side request broker and is responsible for identifying the 
 * service that client has requested based on the service context propagated by client.
 * Any client that has to communicate with Tmultiplexer should wrap the underlying 
 * protocol by TmultiplexProtocol instance and provide the appropriate service context.
 * 
 * @author punit
 *
 */
public class TMultiplexer implements TProcessor {
		
		/**
		 * Logger
		 */
		protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
		
		
		/**
		 * Map storing processor instances against service context.
		 */
	    protected final Map<String,TProcessor> SERVICE_PROCESSOR_MAP = new ConcurrentHashMap<String,TProcessor>();
	
	    
	    /**
		 * Constructor that accepts a list of <MultiplexerArgs>
		 * to register provided services(processors) with 
		 * multiplexer
		 * 
		 * @param list
		 */
	    public TMultiplexer(List<MultiplexerArgs> list) {
	    	
	    	//creating mapping between service context and service(processor)
			for(MultiplexerArgs args : list){
				SERVICE_PROCESSOR_MAP.put(args.getUricontext().getContext().trim(), args.getProcessor());
			}
		}
	    
	    
	    
	    /**
	     * Method for registering a service(processor) against a context with multiplexer. 
	     * @param args
	     * @throws InvalidInputException
	     */
	    public void registerProcessor(MultiplexerArgs args) throws InvalidInputException {
	        SERVICE_PROCESSOR_MAP.put(args.getUricontext().getContext().trim(), args.getProcessor());
	    }
	    
	    
	    
	    /**
	     * Method performs the desired request brocking task by reading the service context 
	     * from the underlying protocol and based on the mapping it directs the request to 
	     * the appropriate service instance.
	     */
	    public boolean process(TProtocol iprot, TProtocol oprot) throws TException {
	       
	        TMessage message = iprot.readMessageBegin();
	
	        if (message.type != TMessageType.CALL && message.type != TMessageType.ONEWAY) {
	              throw new TException("INCOMPATIBLE MESSAGE TYPE : "+ message.type);
	        }
	
	        // Extract the service name
	        int index = message.name.indexOf(TMultiplexProtocol.SEPARATOR);
	        if (index < 0) {
	            throw new TException("SERVICE CONTEXT NOT FOUND IN MESSAGE : "+message.name+". Use TMultiplexProtocol in your client.");
	        }
	
	        // Create a new TMessage, something that can be consumed by any TProtocol
	        String serviceName = message.name.substring(0, index);
	        TProcessor actualProcessor = SERVICE_PROCESSOR_MAP.get(serviceName);
	        if (actualProcessor == null) {
	            throw new TException("SERVICE NOT FOUNT: " + serviceName + ".  Use TMultiplexer to registerProcessor.");
	        }
	
	        // Create a new TMessage, removing the service name
	        TMessage standardMessage = new TMessage(
	                message.name.substring(serviceName.length()+TMultiplexProtocol.SEPARATOR.length()),
	                message.type,
	                message.seqid
	        );
	
	        // Dispatch processing to the stored processor
	        return actualProcessor.process(new TMultiplexProtocol(iprot, standardMessage), oprot);
	    }
	    
	    
	    
	    /**
	     * Class that should be used for providing the required information
	     * about the context and service(processor). so that appropriate 
	     * registrations can be done.
	     *   
	     * @author punit
	     *
	     */
	    public static class MultiplexerArgs {
			/**
			 * Service instance
			 */
			TProcessor tProcessor;
			
			/**
			 * service context
			 */
			URIContext uricontext;
			
			public MultiplexerArgs(TProcessor processor, URIContext context) {
				tProcessor = processor;
				uricontext = context;
			}

			public TProcessor getProcessor() {
				return tProcessor;
			}

			public URIContext getUricontext() {
				return uricontext;
			}
			
					
			
			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder("MultiplexerArgs(");
			    boolean first = true;

			    sb.append("URIContext:");
			    if (this.uricontext == null) {
			      sb.append("null");
			    } else {
			      sb.append(this.uricontext);
			    }
			    first = false;
			    if (!first) sb.append(", ");
			    sb.append("TProcessor:");
			    if (this.tProcessor == null) {
			      sb.append("null");
			    } else {
			      sb.append(this.tProcessor);
			    }
			    sb.append(")");
			    return sb.toString();
			}
			
		}
	
	    
}	    
