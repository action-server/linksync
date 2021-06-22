package com.linksync.backend.nongates;

import com.linksync.backend.abstracts.AbstractLink;

/**
 * This is a ZeroBlock class, that returns the result false.
 *
 * @author Action
 */

public class ZeroBlock extends AbstractLink {
  @Override
  public boolean result() {
    return false;
  }
}
