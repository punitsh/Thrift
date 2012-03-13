package org.apache.thrift.registry;

import java.util.Set;

import org.apache.thrift.registry.TRegistryService.isExist_args;
import org.apache.thrift.registry.TRegistryService.isExist_result;
import org.apache.thrift.registry.TRegistryService.listAll_args;
import org.apache.thrift.registry.TRegistryService.listAll_result;
import org.apache.thrift.registry.TRegistryService.lookupByName_args;
import org.apache.thrift.registry.TRegistryService.lookupByName_result;
import org.apache.thrift.registry.TRegistryService.lookup_args;
import org.apache.thrift.registry.TRegistryService.lookup_result;
import org.apache.thrift.registry.TRegistryService.ping_args;
import org.apache.thrift.registry.TRegistryService.ping_result;
import org.apache.thrift.registry.TRegistryService.regexlookupByName_args;
import org.apache.thrift.registry.TRegistryService.regexlookupByName_result;
import org.apache.thrift.registry.TRegistryService.regexlookup_args;
import org.apache.thrift.registry.TRegistryService.regexlookup_result;

/**
 * Thrift auto generated Client class for registry service.
 * @author punit
 *
 */
public class TRegistryClient extends org.apache.thrift.TServiceClient implements TRegistry {
	    

    public TRegistryClient(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public TRegistryClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public boolean ping() throws org.apache.thrift.TException
    {
      send_ping();
      return recv_ping();
    }

    public void send_ping() throws org.apache.thrift.TException
    {
      ping_args args = new ping_args();
      sendBase("ping", args);
    }

    public boolean recv_ping() throws org.apache.thrift.TException
    {
      ping_result result = new ping_result();
      receiveBase(result, "ping");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "ping failed: unknown result");
    }

    public boolean isExist(String context) throws org.apache.thrift.TException
    {
      send_isExist(context);
      return recv_isExist();
    }

    public void send_isExist(String context) throws org.apache.thrift.TException
    {
      isExist_args args = new isExist_args();
      args.setContext(context);
      sendBase("isExist", args);
    }

    public boolean recv_isExist() throws org.apache.thrift.TException
    {
      isExist_result result = new isExist_result();
      receiveBase(result, "isExist");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "isExist failed: unknown result");
    }

    public URIContext lookup(String context) throws NotFoundException, org.apache.thrift.TException
    {
      send_lookup(context);
      return recv_lookup();
    }

    public void send_lookup(String context) throws org.apache.thrift.TException
    {
      lookup_args args = new lookup_args();
      args.setContext(context);
      sendBase("lookup", args);
    }

    public URIContext recv_lookup() throws NotFoundException, org.apache.thrift.TException
    {
      lookup_result result = new lookup_result();
      receiveBase(result, "lookup");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.e != null) {
        throw result.e;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "lookup failed: unknown result");
    }

    public Set<URIContext> regexlookup(String regex) throws NotFoundException, org.apache.thrift.TException
    {
      send_regexlookup(regex);
      return recv_regexlookup();
    }

    public void send_regexlookup(String regex) throws org.apache.thrift.TException
    {
      regexlookup_args args = new regexlookup_args();
      args.setRegex(regex);
      sendBase("regexlookup", args);
    }

    public Set<URIContext> recv_regexlookup() throws NotFoundException, org.apache.thrift.TException
    {
      regexlookup_result result = new regexlookup_result();
      receiveBase(result, "regexlookup");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.e != null) {
        throw result.e;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "regexlookup failed: unknown result");
    }

    public Set<URIContext> lookupByName(String servicename) throws NotFoundException, org.apache.thrift.TException
    {
      send_lookupByName(servicename);
      return recv_lookupByName();
    }

    public void send_lookupByName(String servicename) throws org.apache.thrift.TException
    {
      lookupByName_args args = new lookupByName_args();
      args.setServicename(servicename);
      sendBase("lookupByName", args);
    }

    public Set<URIContext> recv_lookupByName() throws NotFoundException, org.apache.thrift.TException
    {
      lookupByName_result result = new lookupByName_result();
      receiveBase(result, "lookupByName");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.e != null) {
        throw result.e;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "lookupByName failed: unknown result");
    }

    public Set<URIContext> regexlookupByName(String regex) throws NotFoundException, org.apache.thrift.TException
    {
      send_regexlookupByName(regex);
      return recv_regexlookupByName();
    }

    public void send_regexlookupByName(String regex) throws org.apache.thrift.TException
    {
      regexlookupByName_args args = new regexlookupByName_args();
      args.setRegex(regex);
      sendBase("regexlookupByName", args);
    }

    public Set<URIContext> recv_regexlookupByName() throws NotFoundException, org.apache.thrift.TException
    {
      regexlookupByName_result result = new regexlookupByName_result();
      receiveBase(result, "regexlookupByName");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.e != null) {
        throw result.e;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "regexlookupByName failed: unknown result");
    }

    public Set<URIContext> listAll() throws org.apache.thrift.TException
    {
      send_listAll();
      return recv_listAll();
    }

    public void send_listAll() throws org.apache.thrift.TException
    {
      listAll_args args = new listAll_args();
      sendBase("listAll", args);
    }

    public Set<URIContext> recv_listAll() throws org.apache.thrift.TException
    {
      listAll_result result = new listAll_result();
      receiveBase(result, "listAll");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "listAll failed: unknown result");
    }
	    

 }


