package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

/**
 * Allows the interaction between links
 *
 * @author Ahmed Elhori
 */

public interface Connection {
  boolean connect(Line line);

  boolean disconnect(Line line);
}