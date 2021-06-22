package com.linksync.backend.nongates;

import com.linksync.backend.abstracts.AbstractLink;

/**
 * This is a OneBlock class, that returns the result true.
 *
 * @author Action
 */

public class OneBlock extends AbstractLink {
  @Override
  public boolean result() {
    return true;
  }
}