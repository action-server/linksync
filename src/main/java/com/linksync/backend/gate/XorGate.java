package com.linksync.backend.gates;


import com.linksync.backend.abstracts.AbstractMultiGate;

/**
 * This is a XorGate implementation.
 *
 * @author Action
 */

public class XorGate extends AbstractMultiGate {

  public XorGate(int inputNum) {
    super(inputNum, (a, b) -> a ^ b);
  }
}