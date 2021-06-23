package com.linksync.backend.service;

import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
import com.linksync.backend.gate.AndGate;
import com.linksync.backend.gate.OrGate;
import com.linksync.backend.gate.XorGate;
import com.linksync.backend.nongate.*;
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
  public void halfAdderTest(){
    Component halfAdder = createHalfAdder();
    ZeroBlock zeroBlock = new ZeroBlock();
    OneBlock oneBlock = new OneBlock();

    boolean[][] truthtable =
      {{false, false, false, false},
        {false, true, false, true},
        {true, false, false, true},
        {true, true, true, false}};

    String[] inputColumn = {"A", "B"};
    String[] outputColumn = {"Carry", "Sum"};

    for(int i = 0; i<truthtable.length; i++){
      for(int j = 0; j<inputColumn.length; j++){
        if(truthtable[i][j]==false){
          halfAdder.getInputs(inputColumn[j]).forEach(zeroBlock::connect);
        }else{
          halfAdder.getInputs(inputColumn[j]).forEach(oneBlock::connect);
        }
      }
      for(int j = 0; j<outputColumn.length; j++) {
        Display display = new Display();
        halfAdder.connect(outputColumn[j], display.getInput());
        linkSync.followLink(oneBlock);
        linkSync.followLink(zeroBlock);
        while (!linkSync.runBatch().isEmpty());
        assertEquals(truthtable[i][inputColumn.length+j], display.result());
      }
      for(int j = 0; j<inputColumn.length; j++){
        if(truthtable[i][j]==false){
          halfAdder.getInputs(inputColumn[j]).forEach(zeroBlock::disconnect);
        }else{
          halfAdder.getInputs(inputColumn[j]).forEach(oneBlock::disconnect);
        }
      }
    }
  }

  @Test
  public void fullAdderTest(){
    Component fullAdder = createFullAdder();
    ZeroBlock zeroBlock = new ZeroBlock();
    OneBlock oneBlock = new OneBlock();

    boolean[][] truthtable =
      {{false, false, false, false, false},
        {false, false, true, false, true},
        {false, true, false, false, true},
        {false, true, true, true, false},
        {true, false, false, false, true},
        {true, false, true, true, false},
        {true, true, false, true, false},
        {true, true, true, true, true}};

    String[] inputColumn = {"A", "B", "C"};
    String[] outputColumn = {"Carry", "Sum"};

    for(int i = 0; i<truthtable.length; i++){
      for(int j = 0; j<inputColumn.length; j++){
        if(truthtable[i][j]==false){
          fullAdder.getInputs(inputColumn[j]).forEach(zeroBlock::connect);
        }else{
          fullAdder.getInputs(inputColumn[j]).forEach(oneBlock::connect);
        }
      }
      for(int j = 0; j<outputColumn.length; j++) {
        Display display = new Display();
        fullAdder.connect(outputColumn[j], display.getInput());
        linkSync.followLink(oneBlock);
        linkSync.followLink(zeroBlock);
        while (!linkSync.runBatch().isEmpty());
        assertEquals(truthtable[i][inputColumn.length+j], display.result());
      }
      for(int j = 0; j<inputColumn.length; j++){
        if(truthtable[i][j]==false){
          fullAdder.getInputs(inputColumn[j]).forEach(zeroBlock::disconnect);
        }else{
          fullAdder.getInputs(inputColumn[j]).forEach(oneBlock::disconnect);
        }
      }
    }
}

  private Component createHalfAdder(){
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
    return halfAdder;
  }

  private Component createFullAdder() {
    Component halfAdder1 = createHalfAdder();
    Component halfAdder2 = createHalfAdder();
    halfAdder2.getInputs("A").stream().forEach(x -> halfAdder1.connect("Sum", x));
    OrGate orGate = new OrGate(2);
    halfAdder1.connect("Carry", orGate.getInput(0));
    halfAdder2.connect("Carry", orGate.getInput(1));
    List<Link> links = new ArrayList<>();
    Map<String, List<Line>> inputs = new HashMap<>();
    Map<String, Connection> outputs = new HashMap<>();
    List<Component> components = new ArrayList<>();

    links.addAll(halfAdder1.getLinks());
    links.addAll(halfAdder2.getLinks());
    links.add(orGate);

    inputs.put("A", halfAdder1.getInputs("A"));
    inputs.put("B", halfAdder1.getInputs("B"));
    inputs.put("C", halfAdder2.getInputs("B"));

    outputs.put("Sum", halfAdder2.getOutput("Sum"));
    outputs.put("Carry", orGate);

    components.add(halfAdder1);
    components.add(halfAdder2);

    Component fullAdder = new Component(links, inputs, outputs, components);
    return fullAdder;
  }
}