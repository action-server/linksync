package com.linksync.backend.abstracts;

import com.linksync.backend.tools.Line;
import java.util.function.UnaryOperator;
import lombok.Getter;

/**
 * An abstract implementation of a unary input logic gate.
 *
 * @author Ahmed Elhori
 */

@Getter
public abstract class AbstractUnaryGate extends AbstractLink {
  private final Line input;
  private final UnaryOperator<Boolean> function;

  public AbstractUnaryGate(UnaryOperator<Boolean> function) {
    this.input = new Line(this);
    this.function = function;
  }

  @Override
  public boolean result() {
    return function.apply(input.isCurrent());
  }

  public Line getInput() {
    return input;
  }
}