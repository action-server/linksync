package com.linksync.backend.abstracts;

import com.linksync.backend.api.Link;
import com.linksync.backend.api.Connection;
import com.linksync.backend.nongate.Line;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements all methods except for:
 * public boolean result();
 *
 * @author Action
 */

public abstract class AbstractConnection implements Link, Connection {
  @Getter
  private final List<Line> outputs = new ArrayList<>();

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  @Override
  public boolean connect(Line line) {
    if(line.isConnected()){
      return false;
    }
    outputs.add(line);
    line.setConnected(true);
    return true;
  }

  @Override
  public boolean disconnect(Line line) {
    if(!line.isConnected()){
      return false;
    }
    outputs.remove(line);
    line.setConnected(false);
    return true;
  }

  public abstract boolean result();
}