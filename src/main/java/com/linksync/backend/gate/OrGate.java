package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an OrGate implementation.
 *
 * @author ahmed elhori
 */

public class OrGate extends AbstractMultiInputGate {
  private OrGate(int inputNum, List<Line> outputs, LinkSync linkSync) {
    super(inputNum, outputs, (a, b) -> a | b, linkSync);
  }

  public static OrGate create(int inputNum){
    return new OrGate(inputNum, new ArrayList<>(), LinkSync.getDefault());
  }

  public static OrGate create(){
    return create(2);
  }
}