package com.linksync.backend.gates;

/**
 * This is a Nandgate implementation.
 *
 * @author ahmed elhori
 */

public class NandGate extends AndGate {
  public NandGate(int inputNum) {
    super(inputNum);
  }

  @Override
  public boolean result() {
    return !super.result();
  }
}