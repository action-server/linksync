package com.linksync.backend.gates;


import com.linksync.backend.abstracts.AbstractMultiGate;

/**
 * This is an Andgate implementation.
 *
 * @author ahmed elhori
 */

public class AndGate extends AbstractMultiGate {
  public AndGate(int inputNum) {
    super(inputNum, (a, b) -> a & b);
  }
}