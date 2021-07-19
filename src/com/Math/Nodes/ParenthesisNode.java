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
    ArrayList<Node> Args = GetArgs();
    float f = 0;
    if (OperationToken.Value.equals(")")) {
      for (Node a : Args) {
        f += a.QuickParse(vars);
      }

    } else if (OperationToken.Value.equals("test")) {
      f = 1;
      for (Node a : Args) {
        f *= a.QuickParse(vars);
      }

    }
    //return Nodes.get(0).QuickParse(vars);
    if (InvertOutput) {f = -f;}
    return f;
  }

  public ArrayList<Node> GetArgs() {
    ArrayList<Node> Out = new ArrayList<>();
    Out.add(Nodes.get(0));
    for (int i = 0;i < Nodes.size();i++) {
      Token t = Nodes.get(i).GetToken();
      if (t.Value.equals(",")) {Out.add( Nodes.get(i+1) );}
    }
    return Out;
  }

  @Override
  public String toString() {
    //if (InvertOutput) {return "1";} else {return "0";}
    if (Nodes==null) {return "ParenthesisNode{ERROR, "+OperationToken+"}";}
    return "ParenthesisNode{" + Nodes + "}";
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
