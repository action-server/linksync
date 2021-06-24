package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a XorGate implementation.
 *
 * @author Action
 */

public class XorGate extends AbstractMultiInputGate {
  private XorGate(int inputNum, List<Line> outputs, LinkSync linkSync) {
    super(inputNum, outputs, (a, b) -> a ^ b, linkSync);
  }

  public static XorGate create(int inputNum){
    return new XorGate(inputNum, new ArrayList<>(), LinkSync.getDefault());
  }

  public static XorGate create(){
    return create(2);
  }
}
