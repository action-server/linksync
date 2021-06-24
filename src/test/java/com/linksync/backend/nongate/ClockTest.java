package com.linksync.backend.nongate;

import com.linksync.backend.service.LinkSync;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClockTest {
  private LinkSync linkSync;

  @BeforeEach
  public void setup(){
    this.linkSync=new LinkSync();
  }

  @Test
  public void testClock() {
    Display display = Display.create();
    Clock clock = Clock.create(4, linkSync);
    clock.connect(display.getInput());
    linkSync.start();
    assertTrue(display.result(), "Should be true");
  }

  @Test
  public void testClock2() {
    Display display = Display.create();
    Clock clock = Clock.create(4, linkSync);
    clock.connect(display.getInput());
    linkSync.start();
    linkSync.start();
    linkSync.start();
    linkSync.start();
    linkSync.start();
    assertFalse(display.result(), "Should be false");
  }
}