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

  public @Override
  float QuickParse(HashMap<String, Float> vars) {
    if (vars.get(OperationToken.Value) != null) {
      float val = vars.get(OperationToken.Value);
      if (InvertOutput) {
        val = -val;
      }
      ;
      return val;
    }
    return 0f;
  }

  @Override
  public String toString() {
    if (OperationToken == null) {
      return "VarNode{ERROR}";
    }
    return "VarNode{" + OperationToken.Value + "}";
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
