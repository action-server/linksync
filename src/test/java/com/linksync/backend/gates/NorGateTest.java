package com.linksync.backend.gates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linksync.backend.tools.OneBlock;
import com.linksync.backend.tools.ZeroBlock;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing NorGate class.
 *
 * @author Action
 */

@Getter
public class NorGateTest {
  private ZeroBlock zeroBlock0;
  private ZeroBlock zeroBlock1;
  private OneBlock oneBlock0;
  private OneBlock oneBlock1;
  private NorGate norGate;


  /**
   * Initializing variables before each test.
   */
  @BeforeEach
  public void init() {
    zeroBlock0 = new ZeroBlock();
    zeroBlock1 = new ZeroBlock();
    oneBlock0 = new OneBlock();
    oneBlock1 = new OneBlock();
    norGate = new NorGate(2);
  }

  @Test
  public void testResultTrueWithoutInput() {
    assertTrue(norGate.result(), "input: (false, false) -> output: true");
  }

  @Test
  public void testResultTrueWithInputFalseFalse() {
    zeroBlock0.connect(norGate.getInput(0));
    zeroBlock1.connect(norGate.getInput(1));
    zeroBlock0.propagate();
    zeroBlock1.propagate();

    assertTrue(norGate.result(), "input: (false, false) -> output: true");
  }

  @Test
  public void testResultFalseWithInputTrueFalse() {
    oneBlock0.connect(norGate.getInput(0));
    zeroBlock0.connect(norGate.getInput(1));
    oneBlock0.propagate();
    zeroBlock0.propagate();

    assertFalse(norGate.result(), "input: (true, false) -> output: false");
  }

  @Test
  public void testResultFalseWithInputFalseTrue() {
    zeroBlock0.connect(norGate.getInput(0));
    oneBlock0.connect(norGate.getInput(1));
    zeroBlock0.propagate();
    oneBlock0.propagate();

    assertFalse(norGate.result(), "input: (false, true) -> output: false");
  }

  @Test
  public void testResultFalseWithInputTrueTrue() {
    oneBlock0.connect(norGate.getInput(0));
    oneBlock1.connect(norGate.getInput(1));
    oneBlock0.propagate();
    oneBlock1.propagate();

    assertFalse(norGate.result(), "input: (true, true) -> output: false");
  }
}