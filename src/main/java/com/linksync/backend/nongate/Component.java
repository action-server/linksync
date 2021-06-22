package com.linksync.backend.nongate;

import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
import com.linksync.backend.service.ComponentGenerator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Component {
  private final List<Link> links;
  private final Map<String, List<Line>> inputs;
  private final Map<String, Connection> outputs;

  public void connect(String index, Line line) {
    outputs.get(index).connect(line);
  }

  public void disconnect(String index, Line line) {
    outputs.get(index).disconnect(line);
  }

  public List<Line> getInputs(String index) {
    return inputs.get(index);
  }

  public String getJson() throws Exception {
    return ComponentGenerator.parseComponentToJson(links, inputs, outputs);
  }
}