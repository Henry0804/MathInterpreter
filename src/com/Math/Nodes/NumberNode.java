package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;
import com.Math.TokenType;

import java.util.ArrayList;

public class NumberNode implements Node {
  public Token OperationToken = null;
  public NumberNode(Token tok) {
    OperationToken = tok;

  }

  @Override
  public Token GetToken() {
    return OperationToken;
  }

  @Override
  public ArrayList<Node> GetNodes() {
    return null;
  }

  public @Override int QuickParse() {
    return Integer.parseInt(OperationToken.Value);
  }

  @Override
  public String toString() {
    if (OperationToken==null) {return "NumberNode{ERROR}";}
    return "NumberNode{" + OperationToken.Value+ "}";
  }
}
