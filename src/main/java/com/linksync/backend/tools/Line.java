package com.linksync.backend.tools;

import com.linksync.backend.interfaces.Link;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a Line class, that stores the value of current.
 *
 * @author Action
 */

public class Line {
  @Getter
  @Setter
  private volatile boolean current;
  @Getter
  private Link link;

  public Line(Link link) {
    this.current = false;
    this.link = link;
  }
}