package org.apache.thrift.protocol;

import java.nio.ByteBuffer;

import org.apache.thrift.TConstants;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

/**
 * A wrapper around the underlying protocol ( any TProtocol instance) that is 
 * capable of embedding service context to message on client side and fetching 
 * the same on server side.
 * 
 * @author punit
 *
 */
public class TMultiplexProtocol extends TProtocol {

	/**
	 * separator that distingush service context from method name in a message.
	 */
	public static final String SEPARATOR = TConstants.SEPERATOR_DEFAULT;

	
	/**
	 * underlying protocol instance
	 */
	private final TProtocol tProtocol;
	private TMessage tMessage = null;
	private String context = null;

	
	/**
	 * 
	 * Factory class for generating protocol instance 
	 * @author punit
	 *
	 */
	public static class Factory implements TProtocolFactory {
	   
		private final TProtocolFactory tProtocolFactory;
			
		public Factory(TProtocolFactory tProtocolFactory) {
			this.tProtocolFactory = tProtocolFactory;
		}
		  
		public TProtocol getProtocol(TTransport trans) {
		    return tProtocolFactory.getProtocol(trans);
		}
		
		public static TProtocol getProtocol(TProtocol wrappedProtocol, String serviceContext) {
		    return new TMultiplexProtocol(wrappedProtocol, serviceContext);
		}
		
	}
	
	
	/**
	 * This constructor should be used by client to create context aware
	 * client.
	 * @param protocol
	 * @param serviceContext
	 */
	public TMultiplexProtocol(TProtocol protocol, String serviceContext) {
		super(protocol.getTransport());
		tProtocol = protocol;
		context = serviceContext;
	}
	
		
	/**
	 * This constructor should be used at the server end for passing the 
	 * instance of standard <TMessage> to processor class.
	 * @param protocol
	 * @param message
	 */
	public TMultiplexProtocol(TProtocol protocol, TMessage message) {
	     super(protocol.getTransport());
	     tProtocol = protocol;
		 tMessage = message;
	}

	/**
	 * This method provides the overridden behaviour of appending service context to 
	 * the underlying protocol. 
	 */
	public void writeMessageBegin(TMessage tMessage) throws TException {
		
		if (tMessage.type == TMessageType.CALL
				|| tMessage.type == TMessageType.ONEWAY) {
			
			tProtocol.writeMessageBegin(new TMessage(context + SEPARATOR
					+ tMessage.name, tMessage.type, tMessage.seqid));
			
		} else {
			
			tProtocol.writeMessageBegin(tMessage);
			
		}
		
	}
	
	/**
	 * This method provides the overridden behaviour of reading context 
	 * aware message on server side. 
	 */
	public TMessage readMessageBegin() throws TException {
		return (tMessage!=null)?tMessage:tProtocol.readMessageBegin();
	}

	public void writeMessageEnd() throws TException {
		tProtocol.writeMessageEnd();
	}

	public void writeStructBegin(TStruct tStruct) throws TException {
		tProtocol.writeStructBegin(tStruct);
	}

	public void writeStructEnd() throws TException {
		tProtocol.writeStructEnd();
	}

	public void writeFieldBegin(TField tField) throws TException {
		tProtocol.writeFieldBegin(tField);
	}

	public void writeFieldEnd() throws TException {
		tProtocol.writeFieldEnd();
	}

	public void writeFieldStop() throws TException {
		tProtocol.writeFieldStop();
	}

	public void writeMapBegin(TMap tMap) throws TException {
		tProtocol.writeMapBegin(tMap);
	}

	public void writeMapEnd() throws TException {
		tProtocol.writeMapEnd();
	}

	public void writeListBegin(TList tList) throws TException {
		tProtocol.writeListBegin(tList);
	}

	public void writeListEnd() throws TException {
		tProtocol.writeListEnd();
	}

	public void writeSetBegin(TSet tSet) throws TException {
		tProtocol.writeSetBegin(tSet);
	}

	public void writeSetEnd() throws TException {
		tProtocol.writeSetEnd();
	}

	public void writeBool(boolean b) throws TException {
		tProtocol.writeBool(b);
	}

	public void writeByte(byte b) throws TException {
		tProtocol.writeByte(b);
	}

	public void writeI16(short i) throws TException {
		tProtocol.writeI16(i);
	}

	public void writeI32(int i) throws TException {
		tProtocol.writeI32(i);
	}

	public void writeI64(long l) throws TException {
		tProtocol.writeI64(l);
	}

	public void writeDouble(double v) throws TException {
		tProtocol.writeDouble(v);
	}

	public void writeString(String s) throws TException {
		tProtocol.writeString(s);
	}

	public void writeBinary(ByteBuffer bytes) throws TException {
		tProtocol.writeBinary(bytes);
	}

	public void readMessageEnd() throws TException {
		tProtocol.readMessageEnd();
	}

	public TStruct readStructBegin() throws TException {
		return tProtocol.readStructBegin();
	}

	public void readStructEnd() throws TException {
		tProtocol.readStructEnd();
	}

	public TField readFieldBegin() throws TException {
		return tProtocol.readFieldBegin();
	}

	public void readFieldEnd() throws TException {
		tProtocol.readFieldEnd();
	}

	public TMap readMapBegin() throws TException {
		return tProtocol.readMapBegin();
	}

	public void readMapEnd() throws TException {
		tProtocol.readMapEnd();
	}

	public TList readListBegin() throws TException {
		return tProtocol.readListBegin();
	}

	public void readListEnd() throws TException {
		tProtocol.readListEnd();
	}

	public TSet readSetBegin() throws TException {
		return tProtocol.readSetBegin();
	}

	public void readSetEnd() throws TException {
		tProtocol.readSetEnd();
	}

	public boolean readBool() throws TException {
		return tProtocol.readBool();
	}

	public byte readByte() throws TException {
		return tProtocol.readByte();
	}

	public short readI16() throws TException {
		return tProtocol.readI16();
	}

	public int readI32() throws TException {
		return tProtocol.readI32();
	}

	public long readI64() throws TException {
		return tProtocol.readI64();
	}

	public double readDouble() throws TException {
		return tProtocol.readDouble();
	}

	public String readString() throws TException {
		return tProtocol.readString();
	}

	public ByteBuffer readBinary() throws TException {
		return tProtocol.readBinary();
	}
}
