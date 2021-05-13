package com.linksync.backend.gates;


import com.linksync.backend.abstracts.AbstractMultiGate;

/**
 * This is an OrGate implementation.
 *
 * @author Action
 */

public class OrGate extends AbstractMultiGate {

  public OrGate(int inputNum) {
    super(inputNum, (a, b) -> a | b);
  }
}