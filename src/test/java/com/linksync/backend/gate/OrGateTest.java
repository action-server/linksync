package com.linksync.backend.gate;

import com.linksync.backend.nongate.OneBlock;
import com.linksync.backend.nongate.ZeroBlock;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing OrGate class.
 *
 * @author Ahmed Elhori
 */

@Getter
public class OrGateTest {
  private ZeroBlock zeroBlock0;
  private ZeroBlock zeroBlock1;
  private OneBlock oneBlock0;
  private OneBlock oneBlock1;
  private OrGate orGate;


  /**
   * Initializing variables before each test.
   */
  @BeforeEach
  public void init() {
    zeroBlock0 = ZeroBlock.create();
    zeroBlock1 = ZeroBlock.create();
    oneBlock0 = OneBlock.create();
    oneBlock1 = OneBlock.create();
    orGate = OrGate.create();
  }

  @Test
  public void testResultFalseWithoutInput() {
    assertFalse(orGate.result(), "input: (false, false) -> output: false");
  }

  @Test
  public void testResultFalseWithInputFalseFalse() {
    zeroBlock0.connect(orGate.getInput(0));
    zeroBlock1.connect(orGate.getInput(1));
    zeroBlock0.propagate();
    zeroBlock1.propagate();

    assertFalse(orGate.result(), "input: (false, false) -> output: false");
  }

  @Test
  public void testResultTrueWithInputTrueFalse() {
    oneBlock0.connect(orGate.getInput(0));
    zeroBlock0.connect(orGate.getInput(1));
    oneBlock0.propagate();
    zeroBlock0.propagate();

    assertTrue(orGate.result(), "input: (true, false) -> output: true");
  }

  @Test
  public void testResultTrueWithInputFalseTrue() {
    zeroBlock0.connect(orGate.getInput(0));
    oneBlock0.connect(orGate.getInput(1));
    zeroBlock0.propagate();
    oneBlock0.propagate();

    assertTrue(orGate.result(), "input: (false, true) -> output: true");
  }

  @Test
  public void testResultTrueWithInputTrueTrue() {
    oneBlock0.connect(orGate.getInput(0));
    oneBlock1.connect(orGate.getInput(1));
    oneBlock0.propagate();
    oneBlock1.propagate();

    assertTrue(orGate.result(), "input: (true, true) -> output: true");
  }
}