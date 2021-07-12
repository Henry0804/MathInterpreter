package com.Math;

import java.util.ArrayList;

public interface Node {
  Token GetToken();
  Node LeftNode = null;
  Node RightNode = null;
  ArrayList<Node> GetNodes();
  public String toString();
  float QuickParse();
}
