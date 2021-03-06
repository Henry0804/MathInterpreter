package com.Math;

import java.util.ArrayList;

public class Lexer {
  public static char[] Ignored = new char[]{' '};
  public static char[][] List = new char[][]{new char[]{'0','1','2','3','4','5','6','7','8','9','.'},new char[]{'+','-','*','/','%'},new char[]{'(',')'},new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},new char[]{'='},new char[]{','}};
  public static boolean[] Rule = new boolean[]{false,false,true,false,false,false};
  //Split rule, defines if two chars together should be split into separate nodes.

  public static ArrayList<Token> Parse(String str) {
    ArrayList<Token> Out = new ArrayList<>();
    TokenType t = null;
    String Value = "";
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (CharContains(c,Ignored)) {continue;}
      if (t==null) {
        int x = -1;
        for (char[] a : List) {
          x++;
          if (CharContains(c,a) ) {t = TokenType.values()[x];Value += c;continue;}
        }
      } else {
        boolean v = CharContains(c,List[t.ordinal()]);
        if ((!v)||(Rule[t.ordinal()]) ) {i-=1;Out.add(new Token(t,Value));Value = "";t = null;continue;} else {Value += c;}
      }

    }
    Out.add(new Token(t,Value));
    return Out;
  }

  public static boolean CharContains(char c, char[] d) {
    for (char e : d) {
      if (c==e) {return true;}
    }
    return false;
  }
}
