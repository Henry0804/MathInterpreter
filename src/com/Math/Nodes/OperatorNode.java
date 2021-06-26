package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;
import com.Math.TokenType;

import java.util.ArrayList;

public class OperatorNode implements Node {
  public Token OperationToken = new Token(TokenType.Operator,"_");
  public Node LeftNode = null;
  public Node RightNode = null;
  public OperatorNode(Token tok,Node leftNode,Node rightNode) {
    OperationToken = tok;
    LeftNode = leftNode;
    RightNode = rightNode;

  }

  @Override
  public Token GetToken() {
    return OperationToken;
  }

  @Override
  public ArrayList<Node> GetNodes() {
    return null;
  }

  public OperatorNode(Token tok) {
    OperationToken = tok;

  }

  public @Override int QuickParse() {
    switch (OperationToken.Value) {
      case "+": return LeftNode.QuickParse() + RightNode.QuickParse();
      case "-": return LeftNode.QuickParse() - RightNode.QuickParse();
      case "*": return LeftNode.QuickParse() * RightNode.QuickParse();
      case "/": return LeftNode.QuickParse() / RightNode.QuickParse();
      case "**": return (int) Math.pow(LeftNode.QuickParse() , RightNode.QuickParse());
    }
    return 0;
  }

  @Override
  public String toString() {
    //if (LeftNode==null||RightNode==null||OperationToken!=null) {return "OperatorNode{"+OperationToken+"}";}
    //if (LeftNode==null||RightNode==null||OperationToken==null) {return "OperatorNode{ERROR}";}
    if (LeftNode==null||RightNode==null) {
      return "OperatorNode{" + OperationToken.Value + "}";
    } else {
      return "OperatorNode{" + LeftNode.toString() + "," + OperationToken.Value + "," + RightNode.toString() + "}";
    }

  }
}