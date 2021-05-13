package com.linksync.backend.abstracts;

import com.linksync.backend.tools.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import lombok.Getter;

/**
 * An abstract implementation of a multi input logic gate.
 *
 * @author Action
 */

@Getter
public abstract class AbstractMultiGate extends AbstractLink {
  private final List<Line> inputs;
  private final BinaryOperator<Boolean> function;

  /**
   * A constructor for AbstractMultiGate.
   *
   * @param inputNum is the number of inputs the logic gate is supposed to have.
   * @param function is an implementation of the BinaryOperator interface.
   */
  public AbstractMultiGate(int inputNum, BinaryOperator<Boolean> function) {
    this.inputs = new ArrayList<>();
    this.function = function;
    for (int i = 0; i < inputNum; i++) {
      inputs.add(new Line(this));
    }
  }

  @Override
  public boolean result() {
    boolean result = inputs.get(0).isCurrent();
    for (int i = 1; i < inputs.size(); i++) {
      result = function.apply(result, inputs.get(i).isCurrent());
    }
    return result;
  }

  public Line getInput(int index) {
    return inputs.get(index);
  }

}