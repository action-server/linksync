package com.linksync.backend.abstracts;

import com.linksync.backend.interfaces.Link;
import com.linksync.backend.tools.Line;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * An abstract implementation of the link interface.
 *
 * @author Ahmed Elhori
 */

public abstract class AbstractLink implements Link {
  @Getter
  private final List<Line> outputs;

  public AbstractLink() {
    this.outputs = new ArrayList<>();
  }

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
}