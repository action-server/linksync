package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an Andgate implementation.
 *
 * @author Action
 */

public class AndGate extends AbstractMultiInputGate {
  private AndGate(int inputNum, List<Line> outputs) {
    super(inputNum, outputs,(a, b) -> a & b);
  }

  public static AndGate create(int inputNum){
    return new AndGate(inputNum, new ArrayList<>());
  }

  public static AndGate create(){
    return create(2);
  }
}