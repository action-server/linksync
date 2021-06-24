package com.linksync.backend.nongate;

import com.linksync.backend.api.Link;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This is a Line class, that stores the value of current.
 *
 * @author Ahmed Elhori
 */

@RequiredArgsConstructor
@Getter
public class Line {
  private final int index;
  private final Link link;

  // Disable lombok generating isCurrent as method name
  @Getter(AccessLevel.NONE)
  @Setter
  private volatile boolean current;

  @Setter
  private volatile boolean connected;

  public Line(Link link){
    this.link=link;
    this.index=0;
  }

  public boolean hasCurrent() {
    return current;
  }
}