package com.linksync.backend.interfaces;

import com.linksync.backend.nongates.Line;
import java.util.List;

/**
 * An interface that allows the interactions beween
 * different electronic components.
 *
 * @author Ahmed Elhori
 */

public interface Link {
  public List<Line> getOutputs();

  public void propagate();

  public boolean result();

  public void connect(Line line);

  public void disconnect(Line line);
}