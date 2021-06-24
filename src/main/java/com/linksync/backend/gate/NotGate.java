package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractUnaryInputGate;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a NotGate implementation.
 *
 * @author ahmed elhori
 */

public class NotGate extends AbstractUnaryInputGate {
  private NotGate(List<Line> outputs, LinkSync linkSync) {
    super(outputs, a -> !a, linkSync);
  }

  public static NotGate create(){
    return new NotGate(new ArrayList<>(), LinkSync.getDefault());
  }
}