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
  private final Line input = new Line( this);

  private Display(){}

  public static Display create(){
    return new Display();
  }

  @Override
  public void propagate() {}

  public boolean result() {
    return input.hasCurrent();
  }
}
