/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.service.domain;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum STATUS implements org.apache.thrift.TEnum {
  NEW(1),
  OPEN(2),
  SUCCESS(3),
  CLOSED(4);

  private final int value;

  private STATUS(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static STATUS findByValue(int value) { 
    switch (value) {
      case 1:
        return NEW;
      case 2:
        return OPEN;
      case 3:
        return SUCCESS;
      case 4:
        return CLOSED;
      default:
        return null;
    }
  }
}