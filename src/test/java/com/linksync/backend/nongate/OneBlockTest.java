package com.linksync.backend.nongate;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing OneBlock class.
 *
 * @author Ahmed Elhori
 */

@Getter
public class OneBlockTest {
  @Test
  public void testResultTrue() {
    OneBlock oneBlock = OneBlock.create();
    assertTrue(oneBlock.result(), "Should be true");
  }
}
