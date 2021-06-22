package com.linksync.backend.abstracts;

import com.linksync.backend.api.UnaryInput;
import com.linksync.backend.nongate.Line;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.UnaryOperator;

/**
 * An abstract implementation of a unary input logic gate.
 *
 * @author Action
 */
@RequiredArgsConstructor
public abstract class AbstractUnaryGate extends AbstractConnection implements UnaryInput {
  @Getter
  private final Line input = new Line(0, this);
  private final UnaryOperator<Boolean> function;

  @Override
  public boolean result() {
    return function.apply(input.hasCurrent());
  }
}