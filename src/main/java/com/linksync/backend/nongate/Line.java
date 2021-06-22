package com.linksync.backend.nongates;

import com.linksync.backend.interfaces.Link;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a Line class, that stores the value of current.
 *
 * @author Action
 */

public class Line {
  @Setter
  private volatile boolean current = false;

  @Getter
  private Link link;

  public Line(Link link) {
    this.link = link;
  }

  public boolean hasCurrent(){
    return current;
  }
}