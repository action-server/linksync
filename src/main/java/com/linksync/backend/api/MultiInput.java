package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

public interface MultiInput {
  Line getInput(int inputNum);

  int getInputNum();
}
