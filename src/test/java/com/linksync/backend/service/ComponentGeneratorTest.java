package com.linksync.backend.service;

import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
import com.linksync.backend.gate.AndGate;
import com.linksync.backend.gate.XorGate;
import com.linksync.backend.nongate.Component;
import com.linksync.backend.nongate.Display;
import com.linksync.backend.nongate.Line;
import com.linksync.backend.nongate.OneBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComponentGeneratorTest {
  @TempDir
  File tempDir;
  private ComponentGenerator generator;

  @BeforeEach
  public void setup() throws IOException {
    generator = new ComponentGenerator(tempDir.getPath() + "/linksync");
    generator.prepareEnv();
  }

  @Test
  public void prepareEnvTest() throws IOException {
    generator.prepareEnv();

    File dir = new File(tempDir.getPath() + "/linksync");
    assertTrue(dir.isDirectory());
  }

  @Test
  public void halfAdderSaveComponentTest() throws Exception {
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
    Component halfAdder = new Component(links, inputs, outputs, new ArrayList<>());

    generator.saveComponent("half_adder", halfAdder);

    File file = new File(tempDir.getPath() + "/linksync/half_adder");
    assertTrue(file.isFile());
  }

  @Test
  public void halfAdderCreateComponentTest() throws Exception {
    halfAdderSaveComponentTest();
    Component halfAdder = generator.createComponent("half_adder");
    LinkSync linkSync = new LinkSync(new ArrayList<>());
    OneBlock oneBlock = new OneBlock();
    Display sumDisplay = new Display();
    Display carryDisplay = new Display();
    halfAdder.getInputs("A").forEach(oneBlock::connect);
    halfAdder.getInputs("B").forEach(oneBlock::connect);
    halfAdder.connect("Sum", sumDisplay.getInput());
    halfAdder.connect("Carry", carryDisplay.getInput());
    linkSync.followLink(oneBlock);

    while (!linkSync.runBatch().isEmpty());

    assertEquals(sumDisplay.result(), false);
    assertEquals(carryDisplay.result(), true);
  }
}