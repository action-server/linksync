package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an OrGate implementation.
 *
 * @author Action
 */

public class OrGate extends AbstractMultiInputGate {
  private OrGate(int inputNum, List<Line> outputs) {
    super(inputNum, outputs, (a, b) -> a | b);
  }

  public static OrGate create(int inputNum){
    return new OrGate(inputNum, new ArrayList<>());
  }

  public static OrGate create(){
    return create(2);
  }
}