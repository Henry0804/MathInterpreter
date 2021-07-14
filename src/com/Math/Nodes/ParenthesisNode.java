package com.Math.Nodes;

import com.Math.Node;
import com.Math.Parser;
import com.Math.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class ParenthesisNode implements Node {
  public Token OperationToken = null;
  public ArrayList<Node> Nodes;
  public ParenthesisNode(Token tok) {
    OperationToken = tok;

  }

  public ParenthesisNode(Token tok, ArrayList<Node> nodes) {
    OperationToken = tok;
    Nodes = nodes;
  }

  @Override
  public Token GetToken() {
    return OperationToken;
  }

  @Override
  public ArrayList<Node> GetNodes() {
    return Nodes;
  }

  public @Override float QuickParse(HashMap<String,Float> vars) {
    //return Parser.ConvertParenthesis(Nodes);
    return Nodes.get(0).QuickParse(vars);
  }

  @Override
  public String toString() {
    if (Nodes==null) {return "ParenthesisNode{ERROR, "+OperationToken+"}";}
    return "ParenthesisNode{" + Nodes + "}";
  }


}
