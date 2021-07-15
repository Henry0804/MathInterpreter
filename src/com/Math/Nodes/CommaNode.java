package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class CommaNode implements Node {
  public Token OperationToken = null;
  public CommaNode(Token tok) {
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

  public @Override float QuickParse(HashMap<String,Float> vars) {
    return Float.parseFloat(OperationToken.Value);
  }

  @Override
  public String toString() {
    if (OperationToken==null) {return "CommaNode{ERROR}";}
    return "CommaNode{" + OperationToken.Value+ "}";
  }
}
