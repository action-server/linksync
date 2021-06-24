package com.linksync.backend.gate;

import com.linksync.backend.nongate.OneBlock;
import com.linksync.backend.nongate.ZeroBlock;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing AndGate class.
 *
 * @author Ahmed Elhori
 */

@Getter
public class AndGateTest {
  private ZeroBlock zeroBlock0;
  private ZeroBlock zeroBlock1;
  private OneBlock oneBlock0;
  private OneBlock oneBlock1;
  private AndGate andGate;


  /**
   * Initializing variables before each test.
   */
  @BeforeEach
  public void init() {
    zeroBlock0 = ZeroBlock.create();
    zeroBlock1 = ZeroBlock.create();
    oneBlock0 = OneBlock.create();
    oneBlock1 = OneBlock.create();
    andGate = AndGate.create();
  }

  @Test
  public void testResultFalseWithoutInput() {
    assertFalse(andGate.result(), "input: (false, false) -> output: false");
  }

  @Test
  public void testResultFalseWithInputFalseFalse() {
    zeroBlock0.connect(andGate.getInput(0));
    zeroBlock1.connect(andGate.getInput(1));
    zeroBlock0.propagate();
    zeroBlock1.propagate();

    assertFalse(andGate.result(), "input: (false, false) -> output: false");
  }

  @Test
  public void testResultFalseWithInputTrueFalse() {
    oneBlock0.connect(andGate.getInput(0));
    zeroBlock0.connect(andGate.getInput(1));
    oneBlock0.propagate();
    zeroBlock0.propagate();

    assertFalse(andGate.result(), "input: (true, false) -> output: false");
  }

  @Test
  public void testResultFalseWithInputFalseTrue() {
    zeroBlock0.connect(andGate.getInput(0));
    oneBlock0.connect(andGate.getInput(1));
    zeroBlock0.propagate();
    oneBlock0.propagate();

    assertFalse(andGate.result(), "input: (false, true) -> output: false");
  }

  @Test
  public void testResultTrueWithInputTrueTrue() {
    oneBlock0.connect(andGate.getInput(0));
    oneBlock1.connect(andGate.getInput(1));
    oneBlock0.propagate();
    oneBlock1.propagate();

    assertTrue(andGate.result(), "input: (true, true) -> output: true");
  }
}