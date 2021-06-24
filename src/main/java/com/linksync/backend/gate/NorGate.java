package com.linksync.backend.gate;

import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a NorGate implementation.
 *
 * @author Action
 */

public class NorGate extends AbstractMultiInputGate {
  private NorGate(int inputNum, List<Line> outputs, LinkSync linkSync) {
    super(inputNum, outputs, (a, b) -> a | b, linkSync);
  }

  public static NorGate create(int inputNum){
    return new NorGate(inputNum, new ArrayList<>(), LinkSync.getDefault());
  }

  public static NorGate create(){
    return create(2);
  }

  @Override
  public boolean result() {
    return !super.result();
  }
}