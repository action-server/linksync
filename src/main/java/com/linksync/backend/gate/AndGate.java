package com.linksync.backend.gate;


import com.linksync.backend.abstracts.AbstractMultiGate;

/**
 * This is an Andgate implementation.
 *
 * @author Action
 */

public class AndGate extends AbstractMultiGate {
  public AndGate(int inputNum) {
    super(inputNum, (a, b) -> a & b);
  }
}