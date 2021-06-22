package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

/**
 * Allows the interaction between links
 *
 * @author Ahmed Elhori
 */

public interface Connection {
  public void connect(Line line);

  public void disconnect(Line line);
}