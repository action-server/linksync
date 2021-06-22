package com.linksync.backend.gate;

/**
 * This is a NorGate implementation.
 *
 * @author Action
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