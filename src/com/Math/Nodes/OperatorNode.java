package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;
import com.Math.TokenType;

import java.util.ArrayList;
import java.util.HashMap;

public class OperatorNode implements Node {
  public Token OperationToken = new Token(TokenType.Operator, "_");
  public Node LeftNode = null;
  public Node RightNode = null;

  public OperatorNode(Token tok, Node leftNode, Node rightNode) {
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

  public static float factorial(float n2) {
    float fact = 1;
    int n = Math.round(n2);
    for (int i = 2; i <= n; i++) {
      fact = fact * i;
    }
    return fact;
  }

  public @Override
  float QuickParse(HashMap<String, Float> vars) {

    if (!InvertOutput) {

      switch (OperationToken.Value) {
        case "+":
          return LeftNode.QuickParse(vars) + RightNode.QuickParse(vars);
        case "-":
          return LeftNode.QuickParse(vars) - RightNode.QuickParse(vars);
        case "*":
          return LeftNode.QuickParse(vars) * RightNode.QuickParse(vars);
        case "/":
          return LeftNode.QuickParse(vars) / RightNode.QuickParse(vars);
        case "**":
          return (float) Math.pow(LeftNode.QuickParse(vars), RightNode.QuickParse(vars));
        case "%":
          return (LeftNode.QuickParse(vars) % RightNode.QuickParse(vars));
        case "+-":
          return LeftNode.QuickParse(vars) + -RightNode.QuickParse(vars);
        case "--":
          return LeftNode.QuickParse(vars) - -RightNode.QuickParse(vars);
        case "*-":
          return LeftNode.QuickParse(vars) * -RightNode.QuickParse(vars);
        case "/-":
          return LeftNode.QuickParse(vars) + -RightNode.QuickParse(vars);
        case "%-":
          return LeftNode.QuickParse(vars) % -RightNode.QuickParse(vars);
      }

    } else {
      switch (OperationToken.Value) {
        case "+":
          return -LeftNode.QuickParse(vars) + RightNode.QuickParse(vars);
        case "-":
          return -LeftNode.QuickParse(vars) - RightNode.QuickParse(vars);
        case "*":
          return -LeftNode.QuickParse(vars) * RightNode.QuickParse(vars);
        case "/":
          return -LeftNode.QuickParse(vars) / RightNode.QuickParse(vars);
        case "**":
          return -(float) Math.pow(LeftNode.QuickParse(vars), RightNode.QuickParse(vars));
        case "%":
          return -(LeftNode.QuickParse(vars) % RightNode.QuickParse(vars));
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    //if (LeftNode==null||RightNode==null||OperationToken!=null) {return "OperatorNode{"+OperationToken+"}";}
    //if (LeftNode==null||RightNode==null||OperationToken==null) {return "OperatorNode{ERROR}";}
    if (LeftNode == null || RightNode == null) {
      return "OperatorNode{" + OperationToken.Value + "}";
    } else {
      return "OperatorNode{" + LeftNode.toString() + "," + OperationToken.Value + "," + RightNode.toString() + "}";
    }

  }

  private boolean InvertOutput = false;

  @Override
  public boolean GetInvert() {
    return InvertOutput;
  }

  @Override
  public void SetInvert(boolean v) {
    InvertOutput = v;
  }
}
