package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;
import com.Math.TokenType;

import java.util.ArrayList;
import java.util.HashMap;

public class VariableNode implements Node {
  public Token OperationToken = null;
  public VariableNode(Token tok) {
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
    if (vars.get(OperationToken.Value)!=null) {return vars.get(OperationToken.Value);}
    return 0f;
  }

  @Override
  public String toString() {
    if (OperationToken==null) {return "NumberNode{ERROR}";}
    return "NumberNode{" + OperationToken.Value+ "}";
  }
}
