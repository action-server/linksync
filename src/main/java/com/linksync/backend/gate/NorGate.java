package com.linksync.backend.gates;

/**
 * This is a NorGate implementation.
 *
 * @author ahmed elhori
 */

public class NorGate extends OrGate {
  public NorGate(int inputNum) {
    super(inputNum);
  }

  @Override
  public boolean result() {
    return !super.result();
  }
}