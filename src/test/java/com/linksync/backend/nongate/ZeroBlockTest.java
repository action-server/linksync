package com.linksync.backend.nongate;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Testing ZeroBlock class.
 *
 * @author Action
 */

@Getter
public class ZeroBlockTest {
  @Test
  public void testResultFalse() {
    ZeroBlock zeroBlock = new ZeroBlock();
    assertFalse(zeroBlock.result(), "Should be false");
  }
}
