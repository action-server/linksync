package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

public interface MultiInput {
  public Line getInput(int inputNum);

  public int getInputNum();
}
