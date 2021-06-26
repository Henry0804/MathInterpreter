package com.Math;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    while (true) {
      System.out.print("Calc > ");
      String input = scan.nextLine();
      if (input.equals("exit")) {System.exit(0);}
      System.out.println("Result is: "+  Parser.QuickParse(Parser.Parse(Parser.ToNodes(Lexer.Parse(input))))  );
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
