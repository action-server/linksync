package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a ZeroBlock class, that returns the result false.
 *
 * @author Action
 */

public class ZeroBlock extends AbstractConnection {
  private final List<Line> outputs;

  private ZeroBlock(List<Line> outputs, LinkSync linkSync){
    super(outputs, linkSync);
    this.outputs=outputs;
    linkSync.followLink(this);
  }

  public static ZeroBlock create(){
    return new ZeroBlock(new ArrayList<>(), LinkSync.getDefault());
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    return false;
  }
}