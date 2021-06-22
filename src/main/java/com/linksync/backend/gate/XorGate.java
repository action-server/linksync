package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiGate;

/**
 * This is a XorGate implementation.
 *
 * @author ahmed elhori
 */

public class XorGate extends AbstractMultiGate {

  public XorGate(int inputNum) {
    super(inputNum, (a, b) -> a ^ b);
  }
}
