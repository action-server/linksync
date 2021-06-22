package com.linksync.backend;

import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
import com.linksync.backend.gate.AndGate;
import com.linksync.backend.gate.XorGate;
import com.linksync.backend.nongate.*;
import com.linksync.backend.service.LinkSync;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkSyncTest {
  private LinkSync linkSync;

  @BeforeEach
  public void setup() {
    linkSync = new LinkSync(new ArrayList<>());
  }

  @Test
  public void halfAdderZeroZeroTest() {
    XorGate xorGate = new XorGate(2);
    AndGate andGate = new AndGate(2);
    List<Link> links = new ArrayList<>();
    Map<String, List<Line>> inputs = new HashMap<>();
    Map<String, Connection> outputs = new HashMap<>();
    links.add(xorGate);
    links.add(andGate);
    inputs.put("A", List.of(xorGate.getInput(0), andGate.getInput(1)));
    inputs.put("B", List.of(xorGate.getInput(1), andGate.getInput(0)));
    outputs.put("Sum", xorGate);
    outputs.put("Carry", andGate);
    Component halfAdder = new Component(links, inputs, outputs);
    ZeroBlock zeroBlock = new ZeroBlock();
    Display sumDisplay = new Display();
    Display carryDisplay = new Display();
    halfAdder.getInputs("A").forEach(zeroBlock::connect);
    halfAdder.getInputs("B").forEach(zeroBlock::connect);
    halfAdder.connect("Sum", sumDisplay.getInput());
    halfAdder.connect("Carry", carryDisplay.getInput());
    linkSync.followLink(zeroBlock);

    while (!linkSync.runBatch().isEmpty());

    assertEquals(sumDisplay.output(), false);
    assertEquals(carryDisplay.output(), false);
  }

  @Test
  public void halfAdderZeroOneTest() {
    XorGate xorGate = new XorGate(2);
    AndGate andGate = new AndGate(2);
    List<Link> links = new ArrayList<>();
    Map<String, List<Line>> inputs = new HashMap<>();
    Map<String, Connection> outputs = new HashMap<>();
    links.add(xorGate);
    links.add(andGate);
    inputs.put("A", List.of(xorGate.getInput(0), andGate.getInput(1)));
    inputs.put("B", List.of(xorGate.getInput(1), andGate.getInput(0)));
    outputs.put("Sum", xorGate);
    outputs.put("Carry", andGate);
    Component halfAdder = new Component(links, inputs, outputs);
    ZeroBlock zeroBlock = new ZeroBlock();
    OneBlock oneBlock = new OneBlock();
    Display sumDisplay = new Display();
    Display carryDisplay = new Display();
    halfAdder.getInputs("A").forEach(zeroBlock::connect);
    halfAdder.getInputs("B").forEach(oneBlock::connect);
    halfAdder.connect("Sum", sumDisplay.getInput());
    halfAdder.connect("Carry", carryDisplay.getInput());
    linkSync.followLink(zeroBlock);
    linkSync.followLink(oneBlock);

    while (!linkSync.runBatch().isEmpty());

    assertEquals(sumDisplay.output(), true);
    assertEquals(carryDisplay.output(), false);
  }

  @Test
  public void halfAdderOneOneTest() {
    XorGate xorGate = new XorGate(2);
    AndGate andGate = new AndGate(2);
    List<Link> links = new ArrayList<>();
    Map<String, List<Line>> inputs = new HashMap<>();
    Map<String, Connection> outputs = new HashMap<>();
    links.add(xorGate);
    links.add(andGate);
    inputs.put("A", List.of(xorGate.getInput(0), andGate.getInput(1)));
    inputs.put("B", List.of(xorGate.getInput(1), andGate.getInput(0)));
    outputs.put("Sum", xorGate);
    outputs.put("Carry", andGate);
    Component halfAdder = new Component(links, inputs, outputs);
    OneBlock oneBlock = new OneBlock();
    Display sumDisplay = new Display();
    Display carryDisplay = new Display();
    halfAdder.getInputs("A").forEach(oneBlock::connect);
    halfAdder.getInputs("B").forEach(oneBlock::connect);
    halfAdder.connect("Sum", sumDisplay.getInput());
    halfAdder.connect("Carry", carryDisplay.getInput());
    linkSync.followLink(oneBlock);
    linkSync.followLink(oneBlock);

    while (!linkSync.runBatch().isEmpty());

    assertEquals(sumDisplay.output(), false);
    assertEquals(carryDisplay.output(), true);
  }
}