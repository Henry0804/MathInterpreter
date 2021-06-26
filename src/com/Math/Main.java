package com.Math;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int debug = 0;
    while (true) {
      System.out.print("Calc > ");
      String input = scan.nextLine();
      if (input.equals("exit")) {System.exit(0);}
      if (input.equals("debug")) {debug++;if (debug==4) {debug = 0;};System.out.println("Debug mode is: "+debug);} else {
        switch(debug) {
          case 0: System.out.println("Result is: "+  Parser.QuickParse(Parser.Parse(Parser.ToNodes(Lexer.Parse(input))))  );break;//Output
          case 1: System.out.println("Token output: "+ Lexer.Parse(input));break;
          case 2: System.out.println("Node output: "+ Parser.ToNodes(Lexer.Parse(input)));break;
          case 3: System.out.println("AST output: "+ Parser.Parse(Parser.ToNodes(Lexer.Parse(input))));break;

        }

      }

    }

  }

  public static void test() {
    ArrayList<Token> tokens = Lexer.Parse("1+1*2");
    ArrayList<Node> nodes = Parser.ToNodes(tokens);
    System.out.println(nodes);
    Parser.ConvertFactors(nodes);
    System.out.println(nodes);
    Parser.ConvertExpression(nodes);
    System.out.println(nodes);
    System.out.println(Parser.QuickParse(nodes));
  }
}
