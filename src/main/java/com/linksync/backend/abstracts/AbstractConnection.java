package com.linksync.backend.abstracts;

import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Implements all methods except for:
 * public boolean result();
 *
 * @author Action
 */

@RequiredArgsConstructor
public abstract class AbstractConnection implements Link, Connection {
  @Getter
  private final List<Line> outputs;
  private final LinkSync linkSync;

  @Override
  public boolean connect(Line line) {
    if(line.isConnected()){
      return false;
    }
    outputs.add(line);
    line.setConnected(true);
    linkSync.unfollowLink(line.getLink());
    return true;
  }

  @Override
  public boolean disconnect(Line line) {
    if(!line.isConnected()){
      return false;
    }
    outputs.remove(line);
    line.setConnected(false);
    line.setCurrent(false);
    linkSync.followLink(line.getLink());
    return true;
  }
}