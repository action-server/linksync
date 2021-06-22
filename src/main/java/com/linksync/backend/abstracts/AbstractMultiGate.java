package com.linksync.backend.abstracts;

import com.linksync.backend.api.MultiInput;
import com.linksync.backend.nongate.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * An abstract implementation of a multi input logic gate.
 *
 * @author Action
 */

public abstract class AbstractMultiGate extends AbstractConnection implements MultiInput {
  private final List<Line> inputs = new ArrayList<>();
  private final BinaryOperator<Boolean> function;

  /**
   * A constructor for AbstractMultiGate.
   *
   * @param inputNum is the number of inputs the logic gate is supposed to have.
   * @param function is an implementation of the BinaryOperator interface.
   */
  public AbstractMultiGate(int inputNum, BinaryOperator<Boolean> function) {
    this.function = function;
    for (int i = 0; i < inputNum; i++) {
      inputs.add(new Line(i, this));
    }
  }

  @Override
  public boolean result() {
    boolean result = inputs.get(0).hasCurrent();
    for (int i = 1; i < inputs.size(); i++) {
      result = function.apply(result, inputs.get(i).hasCurrent());
    }
    return result;
  }

  @Override
  public Line getInput(int index) {
    return inputs.get(index);
  }

  @Override
  public int getInputNum() {
    return inputs.size();
  }
}