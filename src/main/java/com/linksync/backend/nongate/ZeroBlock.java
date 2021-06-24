package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a ZeroBlock class, that returns the result false.
 *
 * @author Ahmed Elhori
 */

public class ZeroBlock extends AbstractConnection {
  private final List<Line> outputs;

  private ZeroBlock(List<Line> outputs){
    super(outputs);
    this.outputs=outputs;
    LinkSync.followLink(this);
  }

  public static ZeroBlock create(){
    return new ZeroBlock(new ArrayList<>());
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    return false;
  }
}