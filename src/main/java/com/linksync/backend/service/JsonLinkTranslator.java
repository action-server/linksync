package com.linksync.backend.service;

import com.linksync.backend.api.Link;
import com.linksync.backend.gate.*;
import com.linksync.backend.nongate.OneBlock;
import com.linksync.backend.nongate.ZeroBlock;

public class JsonLinkTranslator {
  public static Link createLinkInstance(int input, String linkClassName) throws Exception {
    switch (linkClassName) {
      case "and_gate":
        return AndGate.create(input);
      case "nand_gate":
        return NandGate.create(input);
      case "or_gate":
        return OrGate.create(input);
      case "nor_gate":
        return NorGate.create(input);
      case "xor_gate":
        return XorGate.create(input);
      case "zero_block":
        return ZeroBlock.create();
      case "one_block":
        return OneBlock.create();
    }
    throw new Exception("Link class not found");
  }

  public static String getLinkName(Link link) throws Exception {
    switch (link.getClass().getSimpleName()) {
      case "AndGate":
        return "and_gate";
      case "NandGate":
        return "nand_gate";
      case "OrGate":
        return "or_gate";
      case "NorGate":
        return "nor_gate";
      case "XorGate":
        return "xor_gate";
      case "ZeroBlock":
        return "zero_block";
      case "OneBlock":
        return "one_block";
    }
    throw new Exception("Link class not found");
  }
}
