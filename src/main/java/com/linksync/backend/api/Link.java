package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

import java.util.List;

/**
 * Allows the orchestration of current flow.
 *
 * @author Ahmed Elhori
 */

public interface Link {
  public List<Line> getOutputs();

  public void propagate();
}