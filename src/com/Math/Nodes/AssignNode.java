package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignNode implements Node {
  public Token OperationToken = null;
  Node LeftNode = null;
  Node RightNode = null;
  public AssignNode(Token tok,Node left,Node right) {
    OperationToken = tok;
    LeftNode = left;
    RightNode = right;

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
    vars.remove(LeftNode.GetToken().Value);
    vars.put(LeftNode.GetToken().Value,RightNode.QuickParse(vars));
    return RightNode.QuickParse(vars);
  }

  @Override
  public String toString() {
    if (OperationToken==null) {return "AssignNode{ERROR}";}
    return "AssignNode{" + OperationToken.Value+ "}";
  }
}
