package org.apache.thrift.registry;

import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

/**
 * Service context class that contains information about the service instance.
 * @author punit
 *
 */
public class URIContext implements org.apache.thrift.TBase<URIContext, URIContext._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("URIContext");

  private static final org.apache.thrift.protocol.TField CONTEXT_FIELD_DESC = new org.apache.thrift.protocol.TField("context", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SERVICE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("serviceName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new URIContextStandardSchemeFactory());
    schemes.put(TupleScheme.class, new URIContextTupleSchemeFactory());
  }

  public String context; // required
  public String serviceName; // required
  public String description; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CONTEXT((short)1, "context"),
    SERVICE_NAME((short)2, "serviceName"),
    DESCRIPTION((short)3, "description");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CONTEXT
          return CONTEXT;
        case 2: // SERVICE_NAME
          return SERVICE_NAME;
        case 3: // DESCRIPTION
          return DESCRIPTION;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private _Fields optionals[] = {_Fields.DESCRIPTION};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CONTEXT, new org.apache.thrift.meta_data.FieldMetaData("context", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERVICE_NAME, new org.apache.thrift.meta_data.FieldMetaData("serviceName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(URIContext.class, metaDataMap);
  }

  public URIContext() {
  }

  public URIContext(
    String context,
    String serviceName)
  {
    this();
    this.context = context;
    this.serviceName = serviceName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public URIContext(URIContext other) {
    if (other.isSetContext()) {
      this.context = other.context;
    }
    if (other.isSetServiceName()) {
      this.serviceName = other.serviceName;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
  }

  public URIContext deepCopy() {
    return new URIContext(this);
  }

  @Override
  public void clear() {
    this.context = null;
    this.serviceName = null;
    this.description = null;
  }

  public String getContext() {
    return this.context;
  }

  public URIContext setContext(String context) {
    this.context = context;
    return this;
  }

  public void unsetContext() {
    this.context = null;
  }

  /** Returns true if field context is set (has been assigned a value) and false otherwise */
  public boolean isSetContext() {
    return this.context != null;
  }

  public void setContextIsSet(boolean value) {
    if (!value) {
      this.context = null;
    }
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public URIContext setServiceName(String serviceName) {
    this.serviceName = serviceName;
    return this;
  }

  public void unsetServiceName() {
    this.serviceName = null;
  }

  /** Returns true if field serviceName is set (has been assigned a value) and false otherwise */
  public boolean isSetServiceName() {
    return this.serviceName != null;
  }

  public void setServiceNameIsSet(boolean value) {
    if (!value) {
      this.serviceName = null;
    }
  }

  public String getDescription() {
    return this.description;
  }

  public URIContext setDescription(String description) {
    this.description = description;
    return this;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CONTEXT:
      if (value == null) {
        unsetContext();
      } else {
        setContext((String)value);
      }
      break;

    case SERVICE_NAME:
      if (value == null) {
        unsetServiceName();
      } else {
        setServiceName((String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CONTEXT:
      return getContext();

    case SERVICE_NAME:
      return getServiceName();

    case DESCRIPTION:
      return getDescription();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CONTEXT:
      return isSetContext();
    case SERVICE_NAME:
      return isSetServiceName();
    case DESCRIPTION:
      return isSetDescription();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof URIContext)
      return this.equals((URIContext)that);
    return false;
  }

  public boolean equals(URIContext that) {
    if (that == null)
      return false;

    boolean this_present_context = true && this.isSetContext();
    boolean that_present_context = true && that.isSetContext();
    if (this_present_context || that_present_context) {
      if (!(this_present_context && that_present_context))
        return false;
      if (!this.context.equals(that.context))
        return false;
    }

//    boolean this_present_serviceName = true && this.isSetServiceName();
//    boolean that_present_serviceName = true && that.isSetServiceName();
//    if (this_present_serviceName || that_present_serviceName) {
//      if (!(this_present_serviceName && that_present_serviceName))
//        return false;
//      if (!this.serviceName.equals(that.serviceName))
//        return false;
//    }
//
//    boolean this_present_description = true && this.isSetDescription();
//    boolean that_present_description = true && that.isSetDescription();
//    if (this_present_description || that_present_description) {
//      if (!(this_present_description && that_present_description))
//        return false;
//      if (!this.description.equals(that.description))
//        return false;
//    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(URIContext other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    URIContext typedOther = (URIContext)other;

    lastComparison = Boolean.valueOf(isSetContext()).compareTo(typedOther.isSetContext());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContext()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.context, typedOther.context);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetServiceName()).compareTo(typedOther.isSetServiceName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServiceName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serviceName, typedOther.serviceName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDescription()).compareTo(typedOther.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, typedOther.description);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("URIContext(");
    boolean first = true;

    sb.append("context:");
    if (this.context == null) {
      sb.append("null");
    } else {
      sb.append(this.context);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("serviceName:");
    if (this.serviceName == null) {
      sb.append("null");
    } else {
      sb.append(this.serviceName);
    }
    first = false;
    if (isSetDescription()) {
      if (!first) sb.append(", ");
      sb.append("description:");
      if (this.description == null) {
        sb.append("null");
      } else {
        sb.append(this.description);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class URIContextStandardSchemeFactory implements SchemeFactory {
    public URIContextStandardScheme getScheme() {
      return new URIContextStandardScheme();
    }
  }

  private static class URIContextStandardScheme extends StandardScheme<URIContext> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, URIContext struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CONTEXT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.context = iprot.readString();
              struct.setContextIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SERVICE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.serviceName = iprot.readString();
              struct.setServiceNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, URIContext struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.context != null) {
        oprot.writeFieldBegin(CONTEXT_FIELD_DESC);
        oprot.writeString(struct.context);
        oprot.writeFieldEnd();
      }
      if (struct.serviceName != null) {
        oprot.writeFieldBegin(SERVICE_NAME_FIELD_DESC);
        oprot.writeString(struct.serviceName);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        if (struct.isSetDescription()) {
          oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
          oprot.writeString(struct.description);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class URIContextTupleSchemeFactory implements SchemeFactory {
    public URIContextTupleScheme getScheme() {
      return new URIContextTupleScheme();
    }
  }

  private static class URIContextTupleScheme extends TupleScheme<URIContext> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, URIContext struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetContext()) {
        optionals.set(0);
      }
      if (struct.isSetServiceName()) {
        optionals.set(1);
      }
      if (struct.isSetDescription()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetContext()) {
        oprot.writeString(struct.context);
      }
      if (struct.isSetServiceName()) {
        oprot.writeString(struct.serviceName);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, URIContext struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.context = iprot.readString();
        struct.setContextIsSet(true);
      }
      if (incoming.get(1)) {
        struct.serviceName = iprot.readString();
        struct.setServiceNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
    }
  }

}

