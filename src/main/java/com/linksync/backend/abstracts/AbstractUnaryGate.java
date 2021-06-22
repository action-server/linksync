package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;
import lombok.Getter;

import java.util.function.UnaryOperator;

/**
 * An abstract implementation of a unary input logic gate.
 *
 * @author Ahmed Elhori
 */

@Getter
public abstract class AbstractUnaryGate extends AbstractLink {
  private final Line input = new Line(this);
  private final UnaryOperator<Boolean> function;

  public AbstractUnaryGate(UnaryOperator<Boolean> function) {
    this.function = function;
  }

  @Override
  public boolean result() {
    return function.apply(input.hasCurrent());
  }

  public Line getInput() {
    return input;
  }
}