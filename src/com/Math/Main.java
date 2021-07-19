package com.Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    HashMap<String,Float> variables = new HashMap<String,Float>();
    variables.put("pi", ((float) Math.PI));
    variables.put("infinity",Float.POSITIVE_INFINITY);
    variables.put("nan",Float.NaN);
    System.out.println("Starting calc...");
    Scanner scan = new Scanner(System.in);
    int debug = 0;
    while (true) {
      System.out.print("Calc > ");
      String input = scan.nextLine();
      if (input.equals("exit")) {System.exit(0);}

      if (input.equals("test") ) {
        int v = 0;
        v+=Test("1+1+1",3f);
        v+=Test("2*3*4",24f);
        v+=Test("2**3",8f);
        v+=Test("1/2",0.5f);
        v+=Test("5**8-7*(90)/3-84+6",390337f);
        v+=Test("x = 3",3f);
        v+=Test("x = x + 1",1f);
        v+=Test("-(1+1)",-2f);
        System.out.println(v+"/8 tests succeeded.");
      }
      else if (input.equals("debug")) {debug++;if (debug==4) {debug = 0;};System.out.println("Debug mode is: "+debug);} else if (input.equals("version")) {System.out.println("Calc Version > v1.1.1");}
    else {
        switch(debug) {
          case 0: System.out.println("Result is: "+  Parser.QuickParse(Parser.Parse(Parser.ToNodes(Lexer.Parse(input))),variables)  );break;//Output
          case 1: System.out.println("Token output: "+ Lexer.Parse(input));break;
          case 2: System.out.println("Node output: "+ Parser.ToNodes(Lexer.Parse(input)));break;
          case 3: System.out.println("AST output: "+ Parser.Parse(Parser.ToNodes(Lexer.Parse(input))));break;

        }

      }



    }

  }

  public static int Test(String val, float expected) {
    HashMap<String,Float> variables = new HashMap<String,Float>();
    variables.put("pi", ((float) Math.PI));
    float outVal = Parser.QuickParse(Parser.Parse(Parser.ToNodes(Lexer.Parse(val))),variables);
    boolean v = outVal==expected;
    String ret = "error";
    if (v) {ret = "Test suceeded.";} else {ret = "Test failed, value "+expected+" was expected, but returned "+outVal+". ("+val+")";}
    System.out.println(ret);
    if (v) {return 1;} else {return 0;}
  }
}
