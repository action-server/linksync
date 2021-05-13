package com.linksync.backend.tools;

import com.linksync.backend.abstracts.AbstractLink;

/**
 * This is a ZeroBlock class, that returns the result false.
 *
 * @author Ahmed Elhori
 */

public class ZeroBlock extends AbstractLink {
  @Override
  public boolean result() {
    return false;
  }
}
