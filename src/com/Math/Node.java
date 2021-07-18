package com.Math;

import java.util.ArrayList;
import java.util.HashMap;

public interface Node {
  Token GetToken();
  Node LeftNode = null;
  Node RightNode = null;

  boolean GetInvert();
  void SetInvert(boolean v);
  boolean InvertOutput = false;

  ArrayList<Node> GetNodes();
  String toString();
  float QuickParse(HashMap<String,Float> vars);
}
