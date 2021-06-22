package com.linksync.backend.nongate;

import com.linksync.backend.abstracts.AbstractConnection;

/**
 * This is a ZeroBlock class, that returns the result false.
 *
 * @author Ahmed Elhori
 */

public class ZeroBlock extends AbstractConnection {
  @Override
  public boolean result() {
    return false;
  }
}
