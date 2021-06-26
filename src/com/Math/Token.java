package com.Math;

public class Token implements Cloneable {
  public TokenType Type;
  public String Value;
  public Token(TokenType type, String value) {
    Type = type;
    Value = value;
  }

  @Override
  public String toString() {
    return Type.toString()+":"+Value;
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
