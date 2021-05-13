package com.linksync.backend.tools;

import static org.junit.jupiter.api.Assertions.assertFalse;

import lombok.Getter;
import org.junit.jupiter.api.Test;

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
