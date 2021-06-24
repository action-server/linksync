package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a XorGate implementation.
 *
 * @author Action
 */

public class XorGate extends AbstractMultiInputGate {
  private XorGate(int inputNum, List<Line> outputs) {
    super(inputNum, outputs, (a, b) -> a ^ b);
  }

  public static XorGate create(int inputNum){
    return new XorGate(inputNum, new ArrayList<>());
  }

  public static XorGate create(){
    return create(2);
  }
}
