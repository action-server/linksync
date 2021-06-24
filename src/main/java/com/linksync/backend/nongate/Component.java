package com.linksync.backend.nongate;

import com.linksync.backend.api.Link;
import com.linksync.backend.api.Connection;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class Component {
  List<Link> links;
  Map<String, List<Line>> inputs;
  Map<String, Connection> outputs;
  List<Component> components;

  public boolean connect(String index, Line line) {
    return outputs.get(index).connect(line);
  }

  public boolean disconnect(String index, Line line) {
    return outputs.get(index).disconnect(line);
  }

  public List<Line> getInputs(String index) {
    return inputs.get(index);
  }

  public Connection getOutput(String index) {
    return outputs.get(index);
  }
}