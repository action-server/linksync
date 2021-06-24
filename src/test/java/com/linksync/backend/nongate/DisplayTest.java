package com.linksync.backend.nongate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisplayTest {
  @Test
  public void testResultTrue() {
    OneBlock oneBlock = OneBlock.create();
    Display display = Display.create();
    oneBlock.connect(display.getInput());
    oneBlock.propagate();
    assertTrue(display.result(), "Should be true");
  }
}
