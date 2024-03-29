package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

import java.util.List;

/**
 * Allows the orchestration of current flow.
 *
 * @author Action
 */

public interface Link {
  List<Line> getOutputs();

  void propagate();
}