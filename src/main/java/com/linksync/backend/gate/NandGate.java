package com.linksync.backend.gate;

import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Nandgate implementation.
 *
 * @author ahmed elhori
 */

public class NandGate extends AbstractMultiInputGate {
  private NandGate(int inputNum, List<Line> outputs, LinkSync linkSync) {
    super(inputNum, outputs,(a, b) -> !(a & b), linkSync);
  }

  public static NandGate create(int inputNum){
    return new NandGate(inputNum, new ArrayList<>(), LinkSync.getDefault());
  }

  public static NandGate create(){
    return create(2);
  }
}