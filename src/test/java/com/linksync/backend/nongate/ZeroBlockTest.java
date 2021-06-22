package com.linksync.backend.nongates;

import static org.junit.jupiter.api.Assertions.assertFalse;

import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * Testing ZeroBlock class.
 *
 * @author Ahmed Elhori
 */

@Getter
public class ZeroBlockTest {
  @Test
  public void testResultFalse() {
    ZeroBlock zeroBlock = new ZeroBlock();
    assertFalse(zeroBlock.result(), "Should be false");
  }
}
