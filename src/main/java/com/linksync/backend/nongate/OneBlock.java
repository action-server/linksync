package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;

/**
 * This is a OneBlock class, that returns the result true.
 *
 * @author Ahmed Elhori
 */

public class OneBlock extends AbstractConnection {
  @Override
  public boolean result() {
    return true;
  }
}