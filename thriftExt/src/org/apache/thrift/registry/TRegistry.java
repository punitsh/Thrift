package org.apache.thrift.registry;

import java.util.Set;

/**
 * Registry interface that provides basic lookup methods
 * to locate service context.
 * 
 * @author punit
 *
 */
public interface TRegistry {
	
		/**
		 * Method to check if registry is up or not. It should always return true. 
		 * @return boolean
		 * @throws org.apache.thrift.TException
		 */
	    public boolean ping() throws org.apache.thrift.TException;

	    /**
	     * Method to check if the desired context is existing in the server registry or not.
	     * This method returns true if the context exist else return false.
	     * @param context
	     * @return boolean
	     * @throws org.apache.thrift.TException
	     */
	    public boolean isExist(String context) throws org.apache.thrift.TException;

	    /**
	     * Basic registry lookup method for getting desired service context instance from registry
	     * @param context
	     * @return URIContext
	     * @throws NotFoundException when context is not registered with registry
	     * @throws org.apache.thrift.TException
	     */
	    public URIContext lookup(String context) throws NotFoundException, org.apache.thrift.TException;

	    /**
	     * Registry lookup method for getting desired service context instance(s) from registry 
	     * after matching available contexts with the provided regular expression argument. 
	     * @param regex[regular expression]
	     * @return Set<URICOntext>
	     * @throws NotFoundException when no matching context is available with the registry
	     * @throws org.apache.thrift.TException
	     */
	    public Set<URIContext> regexlookup(String regex) throws NotFoundException, org.apache.thrift.TException;

	    /**
	     * Basic registry lookup method for getting desired service context instance from registry based 
	     * on the serviceName provided as argument.
	     * @param servicename
	     * @return Set<URIContext>
	     * @throws NotFoundException when no service with the desired service name is available
	     * @throws org.apache.thrift.TException
	     */
	    public Set<URIContext> lookupByName(String servicename) throws NotFoundException, org.apache.thrift.TException;

	    /**
	     * Registry lookup method for getting desired service context instance(s) from registry 
	     * after matching available service name with the provided regular expression argument. 
	     * @param regex
	     * @return
	     * @throws NotFoundException
	     * @throws org.apache.thrift.TException
	     */
	    public Set<URIContext> regexlookupByName(String regex) throws NotFoundException, org.apache.thrift.TException;

	    /**
	     * Method for getting all registered context with the registry
	     * @return
	     * @throws org.apache.thrift.TException
	     */
	    public Set<URIContext> listAll() throws org.apache.thrift.TException;
	    
	    
	    
	    /**
	     * Helper interface that will facilitate server instance to perform
	     * bind, unbinding and rebinding of context.
	     * 
	     * @author punit
	     *
	     */
	    public interface Helper {
	    	
	    	/**
	    	 * Method for binding context to the registry
	    	 * @param context
	    	 * @return true if binding a context for the first time else false
	    	 * @throws InvalidInputException
	    	 */
	    	public boolean bind(URIContext context) throws InvalidInputException;
	
	    	/**
	    	 * Method for updating/changing the existing context of registry
	    	 * @param context
	    	 * @return true if existing context is updated successfully else false
	    	 * @throws NotFoundException when context is not available with the registry
	    	 * @throws InvalidInputException
	    	 */
			public boolean rebind(URIContext context) throws NotFoundException,InvalidInputException;
	
			/**
			 * Method for removing a context from registry
			 * @param context
			 * @return true when context is removed successfully else false
			 * @throws NotFoundException when context is not available with the registry
			 */
			public boolean unbind(String context) throws NotFoundException;
			
			/**
			 * Method for removing a context from registry
			 * @param context
			 * @return true when context is removed successfully else false
			 * @throws NotFoundException when context is not available with the registry
			 */
			public boolean unbind(URIContext context) throws NotFoundException;;
		
	    }

}
