package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;
import com.linksync.backend.service.LinkSync;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Clock extends AbstractConnection {
  @Getter
  private final int rate;
  private boolean on;

  @Override
  public boolean result() {
    if(LinkSync.getPropagationTime()%rate == 0){
      on=!on;
    }
    return on;
  }
}
