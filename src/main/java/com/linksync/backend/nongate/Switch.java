package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;

import java.util.ArrayList;
import java.util.List;

public class Switch extends AbstractConnection {
  private boolean on;
  private final List<Line> outputs;
  private final LinkSync linkSync;

  private Switch(List<Line> outputs, LinkSync linkSync){
    super(outputs, linkSync);
    this.outputs=outputs;
    this.linkSync=linkSync;
    linkSync.followLink(this);
  }

  public static Switch create(){
    return new Switch(new ArrayList<>(), LinkSync.getDefault());
  }

  public static Switch create(LinkSync linkSync) {
    return new Switch(new ArrayList<>(), linkSync);
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    return on;
  }

  public void trigger() {
    on=!on;
    linkSync.trigger(this);
  }
}
