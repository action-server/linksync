package com.linksync.backend.abstracts;

import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
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
  public void connect(Line line) {
    outputs.add(line);
  }

  @Override
  public void disconnect(Line line) {
    outputs.remove(line);
  }

  public abstract boolean result();
}