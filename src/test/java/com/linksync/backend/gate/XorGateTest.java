package com.linksync.backend.gate;

import com.linksync.backend.nongate.OneBlock;
import com.linksync.backend.nongate.ZeroBlock;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing XorGate class.
 *
 * @author Action
 */

@Getter
public class XorGateTest {
  private ZeroBlock zeroBlock0;
  private ZeroBlock zeroBlock1;
  private OneBlock oneBlock0;
  private OneBlock oneBlock1;
  private XorGate xorGate;


  /**
   * Initializing variables before each test.
   */
  @BeforeEach
  public void init() {
    zeroBlock0 = ZeroBlock.create();
    zeroBlock1 = ZeroBlock.create();
    oneBlock0 = OneBlock.create();
    oneBlock1 = OneBlock.create();
    xorGate = XorGate.create();
  }

  @Test
  public void testResultFalseWithoutInput() {
    assertFalse(xorGate.result(), "input: (false, false) -> output: false");
  }

  @Test
  public void testResultFalseWithInputFalseFalse() {
    zeroBlock0.connect(xorGate.getInput(0));
    zeroBlock1.connect(xorGate.getInput(1));
    zeroBlock0.propagate();
    zeroBlock1.propagate();

    assertFalse(xorGate.result(), "input: (false, false) -> output: false");
  }

  @Test
  public void testResultTrueWithInputTrueFalse() {
    oneBlock0.connect(xorGate.getInput(0));
    zeroBlock0.connect(xorGate.getInput(1));
    oneBlock0.propagate();
    zeroBlock0.propagate();

    assertTrue(xorGate.result(), "input: (true, false) -> output: true");
  }

  @Test
  public void testResultTrueWithInputFalseTrue() {
    zeroBlock0.connect(xorGate.getInput(0));
    oneBlock0.connect(xorGate.getInput(1));
    zeroBlock0.propagate();
    oneBlock0.propagate();

    assertTrue(xorGate.result(), "input: (false, true) -> output: true");
  }

  @Test
  public void testResultFalseWithInputTrueTrue() {
    oneBlock0.connect(xorGate.getInput(0));
    oneBlock1.connect(xorGate.getInput(1));
    oneBlock0.propagate();
    oneBlock1.propagate();

    assertFalse(xorGate.result(), "input: (true, true) -> output: false");
  }
}