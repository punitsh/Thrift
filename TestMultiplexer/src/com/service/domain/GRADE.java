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

public enum GRADE implements org.apache.thrift.TEnum {
  G1(1),
  G2(2),
  G3(3),
  G4(4);

  private final int value;

  private GRADE(int value) {
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
  public static GRADE findByValue(int value) { 
    switch (value) {
      case 1:
        return G1;
      case 2:
        return G2;
      case 3:
        return G3;
      case 4:
        return G4;
      default:
        return null;
    }
  }
}