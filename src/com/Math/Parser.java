package com.Math;

import com.Math.Nodes.*;

import java.util.ArrayList;
import java.util.HashMap;
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
      } else if (t.Type==TokenType.Variable) {
        Out.add(new VariableNode(t));
      } else if (t.Type==TokenType.Assign) {
        Out.add(new AssignNode(t,null,null));
      } else if (t.Type==TokenType.Comma) {
        Out.add(new CommaNode(t) );
      }


    }

    return Out;

  }

  public static ArrayList<Node> Parse(ArrayList<Node> Nodes) {
    /*
    First fixes negative numbers. (Not everything is fixed though)
    Then converts all parenthesis and loops a bunch of times.
    And also fixes function based stuff.
    After that the program calculates power, factors, expressions, and then assignments aka =.
     */
    return ConvertAssignment(ConvertExpression(ConvertFactors(ConvertPow(ConvertFunctions(ConvertParenthesis(FixNegativeNumbers(Nodes)))))));
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
    boolean invert = false;
    //ArrayList<String> Values = new ArrayList<>(5);
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type==TokenType.Parenthesis&&(value.equals("("))) {
        depth ++;
        if (depth==1) {
          invert = n.GetInvert();
          start = i;
        }

      } else if (type==TokenType.Parenthesis&&value.equals(")")) {
        depth --;
        if (depth==0) {
          List<Node> split = Nodes.subList(start+1,i);
          ArrayList<Node> split2 = new ArrayList<Node>(split);
          RemoveRange(Nodes,start,i);
          i = start;
          //invert = n.GetInvert();
          Nodes.remove(i);


          Node add = new ParenthesisNode(new Token(TokenType.Parenthesis,")"),split2);
          add.SetInvert(invert);
          Nodes.add(start,add);
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
      if (type==TokenType.Operator&&(value.equals("*") || value.equals("/") || value.equals("%")  || value.equals("%-") || value.equals("*-") || value.equals("/-"))) {
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

  public static ArrayList<Node> ConvertAssignment(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type==TokenType.Assign&&(value.equals("=") )  ) {
        Node left = Nodes.get(i-1);
        Node right = Nodes.get(i+1);
        AssignNode out = new AssignNode(n.GetToken(),left,right);
        Nodes.set(i,out);
        Nodes.remove(i-1);
        Nodes.remove(i);
        i--;
      }


    }
    return Nodes;
  }

  public static ArrayList<Node> FixNegativeNumbers(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      /*if (type == TokenType.Operator && (value.equals("+-") || value.equals("--") || value.equals("*-") || value.equals("/-") || value.equals("**-"))) {
        //n.GetToken().Value = n.GetToken().Value.substring(0, n.GetToken().Value.length() - 1);
        //Nodes.get(i + 1).GetToken().Value = "-" + Nodes.get(i + 1).GetToken().Value;

      }*/

      if ((i+1)>Nodes.size()-1) {continue;}

      boolean and = true;

      if (i>0) {
        and = Nodes.get(i + 1).GetToken().Type!=TokenType.Number && Nodes.get(i - 1).GetToken().Type!=TokenType.Number;
      } //else {
      //  and = Nodes.get(i + 1).GetToken().Type!=TokenType.Number;
      //}

      if (type == TokenType.Operator && value.equals("-") && and ) {
        Nodes.remove(i);
        Nodes.get(i).SetInvert(!Nodes.get(i).GetInvert());
      }

    }
    return Nodes;
  }

  public static ArrayList<Node> ConvertFunctions(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size(); i++) {
      Node n = Nodes.get(i);

      TokenType type = n.GetToken().Type;
      String value = n.GetToken().Value;
      if (type == TokenType.Parenthesis) {
        //n.GetToken().Value = n.GetToken().Value.substring(0, n.GetToken().Value.length() - 1);
        //Nodes.get(i + 1).GetToken().Value = "-" + Nodes.get(i + 1).GetToken().Value;
        if (i==0) {continue;}
        if (Nodes.get(i-1).GetToken().Type!=TokenType.Variable) {continue;}
        n.GetToken().Value = Nodes.get(i-1).GetToken().Value;
        Nodes.remove(i-1);
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
      if (type==TokenType.Operator&&(value.equals("+") || value.equals("-") || value.equals("--") || value.equals("+-") ) ) {
        Node left;
        if (i-1==-1) {left = new NumberNode(new Token(TokenType.Number,"0"));} else {
          left = Nodes.get(i - 1);
        }
        Node right = Nodes.get(i+1);
        //if (left.GetToken().Type!=TokenType.Number) {left = new NumberNode(new Token(TokenType.Number,"0"));}
        OperatorNode out = new OperatorNode(n.GetToken(),left,right);
        Nodes.set(i,out);
        if (i-1==-1) {Nodes.remove(i);Nodes.get(i).GetToken().Value = "-"+Nodes.get(i).GetToken().Value;i++;}
        else {
          Nodes.remove(i - 1);
          Nodes.remove(i);
        }
        i--;
      }


    }
    return Nodes;
  }

  public static float QuickParse(ArrayList<Node> Nodes, HashMap<String,Float> vars) {
    return Nodes.get(0).QuickParse(vars);
  }
}
