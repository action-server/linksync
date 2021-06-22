package com.linksync.backend.nongate;

import com.linksync.backend.api.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This is a Line class, that stores the value of current.
 *
 * @author Action
 */

@RequiredArgsConstructor
public class Line {
  @Getter
  private final int index;
  @Getter
  private final Link link;
  @Setter
  private volatile boolean current = false;

  public boolean hasCurrent() {
    return current;
  }
}