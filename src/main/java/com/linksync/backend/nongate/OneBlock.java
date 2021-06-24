package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a OneBlock class, that returns the result true.
 *
 * @author Ahmed Elhori
 */

public class OneBlock extends AbstractConnection {
  private final List<Line> outputs;

  private OneBlock(List<Line> outputs){
    super(outputs);
    this.outputs=outputs;
    LinkSync.followLink(this);
  }

  public static OneBlock create(){
    return new OneBlock(new ArrayList<>());
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    return true;
  }
}