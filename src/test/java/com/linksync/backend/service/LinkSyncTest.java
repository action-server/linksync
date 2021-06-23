package com.linksync.backend.service;

import com.linksync.backend.nongate.Component;
import com.linksync.backend.nongate.Display;
import com.linksync.backend.nongate.OneBlock;
import com.linksync.backend.nongate.ZeroBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkSyncTest {
  private LinkSync linkSync;

  @BeforeEach
  public void setup() {
    linkSync = new LinkSync(new ArrayList<>());
  }

  @Test
  public void halfAdderTest() throws Exception {
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
  public void fullAdderTest() throws Exception {
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

  private Component createHalfAdder() throws Exception {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("half_adder");
    ComponentGenerator componentGenerator = new ComponentGenerator("");
    Component halfAdder = componentGenerator.createComponentFromStram(inputStream);
    return halfAdder;
  }

  private Component createFullAdder() throws Exception {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("full_adder");
    ComponentGenerator componentGenerator = new ComponentGenerator("");
    Component fullAdder = componentGenerator.createComponentFromStram(inputStream);
    return fullAdder;
  }
}