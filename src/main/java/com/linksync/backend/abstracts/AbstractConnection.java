package com.linksync.backend.abstracts;

import com.linksync.backend.api.Link;
import com.linksync.backend.nongate.Line;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements all methods except for:
 * public boolean result();
 *
 * @author Ahmed Elhori
 */

public abstract class AbstractLink implements Link {
  @Getter
  private final List<Line> outputs = new ArrayList<>();

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public abstract boolean result();
}