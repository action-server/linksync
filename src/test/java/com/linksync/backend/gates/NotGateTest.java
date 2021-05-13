package com.linksync.backend.gates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.linksync.backend.tools.OneBlock;
import com.linksync.backend.tools.ZeroBlock;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing NotGate class.
 *
 * @author Ahmed Elhori
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
    zeroBlock = new ZeroBlock();
    oneBlock = new OneBlock();
    notGate = new NotGate();
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