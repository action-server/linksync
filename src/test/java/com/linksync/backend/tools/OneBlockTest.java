package com.linksync.backend.tools;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * Testing OneBlock class.
 *
 * @author Ahmed Elhori
 */

@Getter
public class OneBlockTest {
  @Test
  public void testResultTrue() {
    OneBlock oneBlock = new OneBlock();
    assertTrue(oneBlock.result(), "Should be true");
  }
}
