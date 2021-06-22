package com.linksync.backend.nongate;

import com.linksync.backend.api.Link;
import com.linksync.backend.api.UnaryInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Display implements Link, UnaryInput {
  @Getter
  private final List<Line> outputs = new ArrayList<>();
  @Getter
  private final Line input = new Line(0, this);

  @Override
  public void propagate() {
    return;
  }

  public boolean output() {
    return input.hasCurrent();
  }
}
