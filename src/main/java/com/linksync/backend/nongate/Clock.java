package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Clock extends AbstractConnection {
  @Getter
  private final int rate;
  private boolean on;
  private final List<Line> outputs;

  private Clock(int rate, List<Line> outputs){
    super(outputs);
    this.rate=rate;
    this.outputs=outputs;
    LinkSync.followLink(this);
  }

  public static Clock create(int rate){
    return new Clock(rate, new ArrayList<>());
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    if(LinkSync.getPropagationTime()%rate == 0){
      on=!on;
    }
    return on;
  }
}
