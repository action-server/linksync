package com.linksync;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Testing if the application runs.
 *
 * @author Action
 */

public class MainTest {
  @Test
  public void appRun() {
    Main main = new Main();
    assertNotNull(main.greeting(), "main.greeting() must be non Null.");
  }
}