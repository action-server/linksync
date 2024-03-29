package com.linksync.backend.gate;

import com.linksync.backend.nongate.OneBlock;
import com.linksync.backend.nongate.ZeroBlock;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing NotGate class.
 *
 * @author Action
 */

@Getter
public class NotGateTest {
  private ZeroBlock zeroBlock;
  private OneBlock oneBlock;
  private NotGate notGate;

  /**
   * Initializing variables before each test.
   */
  @BeforeEach
  public void init() {
    zeroBlock = ZeroBlock.create();
    oneBlock = OneBlock.create();
    notGate = NotGate.create();
  }

  @Test
  public void testResultTrueWithoutInput() {
    assertTrue(notGate.result(), "input: false -> output: true");
  }

  @Test
  public void testResultTrueWithInputFalseTest() {
    zeroBlock.connect(notGate.getInput());
    zeroBlock.propagate();

    assertTrue(notGate.result(), "input: false -> output: true");
  }

  @Test
  public void testResultFalseWithInputTrue() {
    oneBlock.connect(notGate.getInput());
    oneBlock.propagate();

    assertFalse(notGate.result(), "input: true -> output: false");
  }
}
