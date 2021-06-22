package com.linksync;
import com.linksync.frontend.HelloFX;

/**
 * This is the main entrypoint to the application.
 *
 * @author Action
 */

public class Main {
  public static void main(String[] args) {
    HelloFX helloFX = new HelloFX();
    helloFX.main();
  }

  public String greeting() {
    return "App is running";
  }
}