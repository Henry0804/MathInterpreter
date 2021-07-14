package com.Math;

import java.util.ArrayList;
import java.util.HashMap;

public interface Node {
  Token GetToken();
  Node LeftNode = null;
  Node RightNode = null;
  ArrayList<Node> GetNodes();
  public String toString();
  float QuickParse(HashMap<String,Float> vars);
}
