package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Clock extends AbstractConnection {
  @Getter
  private final int delay;
  private boolean on;
  private final List<Line> outputs;
  private final LinkSync linkSync;

  private Clock(int delay, List<Line> outputs, LinkSync linkSync){
    super(outputs, linkSync);
    this.delay =delay;
    this.outputs=outputs;
    this.linkSync=linkSync;
    linkSync.followLink(this);
  }

  public static Clock create(int delay){
    return new Clock(delay, new ArrayList<>(), LinkSync.getDefault());
  }

  public static Clock create(int delay, LinkSync linkSync) {
    return new Clock(delay, new ArrayList<>(), linkSync);
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    return on;
  }

  public void trigger() {
    if(linkSync.getPropagationTime()% delay == 0){
      on=!on;
      linkSync.trigger(this);
    }
  }
}
