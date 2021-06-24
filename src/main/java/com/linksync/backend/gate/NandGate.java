package com.linksync.backend.gate;

import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Nandgate implementation.
 *
 * @author Action
 */

public class NandGate extends AbstractMultiInputGate {
  private NandGate(int inputNum, List<Line> outputs) {
    super(inputNum, outputs,(a, b) -> !(a & b));
  }

  public static NandGate create(int inputNum){
    return new NandGate(inputNum, new ArrayList<>());
  }

  public static NandGate create(){
    return create(2);
  }
}