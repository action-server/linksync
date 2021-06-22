package com.linksync.backend.api;

import com.linksync.backend.nongate.Line;

/**
 * Allows the interaction between links
 *
 * @author Ahmed Elhori
 */

public interface Connection {
  void connect(Line line);

  void disconnect(Line line);
}