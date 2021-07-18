package com.Math.Nodes;

import com.Math.Node;
import com.Math.Token;
import com.Math.TokenType;

import java.util.ArrayList;
import java.util.HashMap;

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

  public @Override
  float QuickParse(HashMap<String, Float> vars) {
    float val = Float.parseFloat(OperationToken.Value);
    if (InvertOutput) {
      val = -val;
    }
    return val;
  }

  @Override
  public String toString() {
    if (OperationToken == null) {
      return "NumberNode{ERROR}";
    }
    if (InvertOutput) {return "NumberNode{-" + OperationToken.Value + "}";} else {
      return "NumberNode{" + OperationToken.Value + "}";
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
