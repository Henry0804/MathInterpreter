package com.Math;

import com.Math.Nodes.NumberNode;
import com.Math.Nodes.OperatorNode;
import com.Math.Nodes.ParenthesisNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {
  public static ArrayList<Node> ToNodes(ArrayList<Token> Tokens) {
    ArrayList<Node> Out = new ArrayList<Node>();
    for (Token t : Tokens) {
      if (t.Type==TokenType.Number) {
        Out.add(new NumberNode(t) );
      } else if (t.Type==TokenType.Operator) {
        Out.add(new OperatorNode(t) );

      } else if (t.Type==TokenType.Parenthesis) {
        Out.add(new ParenthesisNode(t) );
      }


    }

    return Out;

  }

  public static ArrayList<Node> Parse(ArrayList<Node> Nodes) {
    return ConvertExpression(ConvertFactors(ConvertPow(ConvertParenthesis(Nodes))));
  }

  public static void RemoveRange(ArrayList<Node> Nodes,int start,int end) {
    int end2 = end-start;
    for (int i = 0; i < end2; i++) {
      Nodes.remove(start);
    }
  }

  public static ArrayList<Node> ConvertParenthesis(ArrayList<Node> Nodes) {
    int depth = 0;
    int start = 0;
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type==TokenType.Parenthesis&&(value.equals("("))) {
        depth ++;
        if (depth==1) {
          start = i;
        }
      } else if (type==TokenType.Parenthesis&&value.equals(")")) {
        depth --;
        if (depth==0) {
          List<Node> split = Nodes.subList(start+1,i);
          ArrayList<Node> split2 = new ArrayList<Node>(split);
          RemoveRange(Nodes,start,i);
          i = start;
          Nodes.remove(i);

          Nodes.add(start,new ParenthesisNode(n.GetToken(),split2)/*element*/);
        }

      }


    }

    //Now update all parenthesis nodes.
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);
      TokenType type = n.GetToken().Type;
      if (type==TokenType.Parenthesis) {Parse(n.GetNodes());}

    }
    return Nodes;
  }

  public static ArrayList<Node> ConvertPow(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type==TokenType.Operator&&(value.equals("**"))) {
        Node left = Nodes.get(i-1);
        Node right = Nodes.get(i+1);
        OperatorNode out = new OperatorNode(n.GetToken(),left,right);
        Nodes.set(i,out);
        Nodes.remove(i-1);
        Nodes.remove(i);
        i--;
      }


    }
    return Nodes;
  }

  public static ArrayList<Node> ConvertFactors(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type==TokenType.Operator&&(value.equals("*") || value.equals("/"))) {
        Node left = Nodes.get(i-1);
        Node right = Nodes.get(i+1);
        OperatorNode out = new OperatorNode(n.GetToken(),left,right);
        Nodes.set(i,out);
        Nodes.remove(i-1);
        Nodes.remove(i);
        i--;
      }


    }
    return Nodes;
  }

  public static ArrayList<Node> ConvertExpression(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type==TokenType.Operator&&(value.equals("+") || value.equals("-"))) {
        Node left;
        if (i-1==-1) {left = new NumberNode(new Token(TokenType.Number,"0"));} else {
          left = Nodes.get(i - 1);
        }
        Node right = Nodes.get(i+1);
        OperatorNode out = new OperatorNode(n.GetToken(),left,right);
        Nodes.set(i,out);
        if (i-1==-1) {Nodes.remove(i);Nodes.get(i).GetToken().Value = "-"+Nodes.get(i).GetToken().Value;i++;} else {
          Nodes.remove(i - 1);
          Nodes.remove(i);
        }
        i--;
      }


    }
    return Nodes;
  }

  public static int QuickParse(ArrayList<Node> Nodes) {
    return Nodes.get(0).QuickParse();
  }
}
