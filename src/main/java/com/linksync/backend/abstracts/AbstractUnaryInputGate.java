package com.linksync.backend.abstracts;

import com.linksync.backend.api.UnaryInput;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.service.LinkSync;
import lombok.Getter;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * An abstract implementation of a unary input logic gate.
 *
 * @author Action
 */
public abstract class AbstractUnaryInputGate extends AbstractConnection implements UnaryInput {
  @Getter
  private final Line input = new Line(this);
  private final List<Line> outputs;
  private final UnaryOperator<Boolean> function;

  public AbstractUnaryInputGate(List<Line> outputs, UnaryOperator<Boolean> function) {
    super(outputs);
    this.outputs=outputs;
    this.function=function;
    LinkSync.followLink(this);
  }

  @Override
  public void propagate() {
    outputs.forEach(x -> x.setCurrent(result()));
  }

  public boolean result() {
    return function.apply(input.hasCurrent());
  }
}