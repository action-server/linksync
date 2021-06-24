package com.linksync.backend.service;

import com.google.gson.*;
import com.linksync.backend.abstracts.AbstractMultiInputGate;
import com.linksync.backend.api.Connection;
import com.linksync.backend.api.Link;
import com.linksync.backend.api.MultiInput;
import com.linksync.backend.api.UnaryInput;
import com.linksync.backend.nongate.Component;
import com.linksync.backend.nongate.Line;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComponentGenerator {
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private final String path;

  public ComponentGenerator(String path) {
    this.path = path;
  }

  public static Component parseJsonToComponent(String jsonString) throws Exception {
    JsonObject jsonBody = gson.fromJson(jsonString, JsonObject.class);
    JsonArray jsonLinks = jsonBody.getAsJsonArray("links");

    List<Link> links = new ArrayList<>();
    for (JsonElement jsonLinkElement : jsonLinks) {
      JsonObject jsonLink = jsonLinkElement.getAsJsonObject();
      String linkClassName = jsonLink.get("type").getAsString();
      int linkInputNum = jsonLink.get("input_num").getAsInt();
      Link link = JsonLinkTranslator.createLinkInstance(linkInputNum, linkClassName);
      links.add(link);
    }

    for (JsonElement jsonLinkElement : jsonLinks) {
      JsonObject jsonLink = jsonLinkElement.getAsJsonObject();
      int linkId = jsonLink.get("id").getAsInt();
      JsonArray jsonOutputs = jsonLink.getAsJsonArray("outputs");
      for (JsonElement jsonOutputElement : jsonOutputs) {
        JsonObject jsonOutput = jsonOutputElement.getAsJsonObject();
        int outputId = jsonOutput.get("id").getAsInt();
        int outputIndex = jsonOutput.get("index").getAsInt();
        Link outputLink = links.get(outputId);
        Line outputLine;
        if (outputLink instanceof MultiInput) {
          outputLine = ((MultiInput) outputLink).getInput(outputIndex);
        } else {
          outputLine = ((UnaryInput) outputLink).getInput();
        }
        Connection connectingLink = (Connection) links.get(linkId);
        connectingLink.connect(outputLine);
      }
    }


    JsonArray jsonInputs = jsonBody.getAsJsonArray("inputs");
    Map<String, List<Line>> inputs = new HashMap<>();
    for (JsonElement jsonInputElement : jsonInputs) {
      JsonObject jsonInput = jsonInputElement.getAsJsonObject();
      String inputIndex = jsonInput.get("index").getAsString();
      JsonArray jsonLinkInputs = jsonInput.get("link_inputs").getAsJsonArray();
      List<Line> inputLines = new ArrayList<>();
      for (JsonElement jsonLinkInputElement : jsonLinkInputs) {
        JsonObject jsonLinkInput = jsonLinkInputElement.getAsJsonObject();
        int linkId = jsonLinkInput.get("id").getAsInt();
        int linkIndex = jsonLinkInput.get("index").getAsInt();
        Link inputLink = links.get(linkId);
        Line inputLine;
        if (inputLink instanceof MultiInput) {
          inputLine = ((MultiInput) inputLink).getInput(linkIndex);
        } else {
          inputLine = ((UnaryInput) inputLink).getInput();
        }
        inputLines.add(inputLine);
      }
      inputs.put(inputIndex, inputLines);
    }

    JsonArray jsonOutputs = jsonBody.getAsJsonArray("outputs");
    Map<String, Connection> outputs = new HashMap<>();
    for (JsonElement jsonOutputElement : jsonOutputs) {
      JsonObject jsonOutput = jsonOutputElement.getAsJsonObject();
      String outputIndex = jsonOutput.get("index").getAsString();
      int linkId = jsonOutput.get("id").getAsInt();
      outputs.put(outputIndex, (Connection) links.get(linkId));
    }

    JsonArray jsonComponents = jsonBody.getAsJsonArray("components");
    List<Component> components = new ArrayList<>();
    for (JsonElement jsonComponentElement : jsonComponents) {
      JsonObject jsonComponent = jsonComponentElement.getAsJsonObject();
      Component component = parseJsonToComponent(jsonComponent.toString());
      components.add(component);
    }

    Component component = new Component(links, inputs, outputs, components);
    return component;
  }

  public static String parseComponentToJson(List<Link> links, Map<String, List<Line>> inputs, Map<String, Connection> outputs, List<Component> components) throws Exception {
    JsonObject jsonBody = new JsonObject();

    JsonArray jsonLinks = JsonParser.parseString(parseLinksToJson(links)).getAsJsonArray();
    JsonArray jsonComponentInputs = JsonParser.parseString(parseInputsToJson(links, inputs)).getAsJsonArray();
    JsonArray jsonComponentOutputs = JsonParser.parseString(parseOutputsToJson(links, outputs)).getAsJsonArray();
    JsonArray jsonComponentComponents = JsonParser.parseString(parseComponentsToJson(links, components)).getAsJsonArray();

    jsonBody.add("links", jsonLinks);
    jsonBody.add("inputs", jsonComponentInputs);
    jsonBody.add("outputs", jsonComponentOutputs);
    jsonBody.add("components", jsonComponentComponents);

    return gson.toJson(jsonBody);
  }

  private static String parseLinksToJson(List<Link> links) throws Exception {
    JsonArray jsonLinks = new JsonArray();
    for (Link link : links) {
      JsonObject jsonLink = new JsonObject();
      jsonLink.addProperty("type", JsonLinkTranslator.getLinkName(link));
      jsonLink.addProperty("id", links.indexOf(link));
      int inputNum = 0;
      if (link instanceof AbstractMultiInputGate) {
        inputNum = ((AbstractMultiInputGate) link).getInputNum();
      }
      jsonLink.addProperty("input_num", inputNum);
      JsonArray jsonOutputs = new JsonArray();
      for (Line line : link.getOutputs()) {
        int outputId = links.indexOf(line.getLink());
        if (outputId == -1) {
          continue;
        }
        JsonObject output = new JsonObject();
        output.addProperty("id", outputId);
        output.addProperty("index", line.getIndex());
        jsonOutputs.add(output);
      }
      jsonLink.add("outputs", jsonOutputs);
      jsonLinks.add(jsonLink);
    }
    return jsonLinks.toString();
  }

  private static String parseInputsToJson(List<Link> links, Map<String, List<Line>> inputs) {
    JsonArray jsonComponentInputs = new JsonArray();
    for (Map.Entry<String, List<Line>> entry : inputs.entrySet()) {
      JsonObject jsonComponentInput = new JsonObject();
      jsonComponentInput.addProperty("index", entry.getKey());
      JsonArray jsonLinkInputs = new JsonArray();
      for (Line line : entry.getValue()) {
        JsonObject jsonLinkInput = new JsonObject();
        jsonLinkInput.addProperty("id", links.indexOf(line.getLink()));
        jsonLinkInput.addProperty("index", line.getIndex());
        jsonLinkInputs.add(jsonLinkInput);
      }
      jsonComponentInput.add("link_inputs", jsonLinkInputs);
      jsonComponentInputs.add(jsonComponentInput);
    }
    return jsonComponentInputs.toString();
  }

  private static String parseOutputsToJson(List<Link> links, Map<String, Connection> outputs) {
    JsonArray jsonComponentOutputs = new JsonArray();
    for (Map.Entry<String, Connection> entry : outputs.entrySet()) {
      JsonObject jsonComponentOutput = new JsonObject();
      jsonComponentOutput.addProperty("index", entry.getKey());
      jsonComponentOutput.addProperty("id", links.indexOf(entry.getValue()));
      jsonComponentOutputs.add(jsonComponentOutput);
    }
    return jsonComponentOutputs.toString();
  }

  private static String parseComponentsToJson(List<Link> links, List<Component> components) throws Exception {
    JsonArray jsonComponentComponents = new JsonArray();
    for (Component component : components) {
      JsonObject jsonComponentComponent = JsonParser
        .parseString(parseComponentToJson(component.getLinks(),component.getInputs(), component.getOutputs(), component.getComponents()))
        .getAsJsonObject();
      jsonComponentComponents.add(jsonComponentComponent);
    }
    return jsonComponentComponents.toString();
  }

  public void prepareEnv() throws IOException {
    Path dirPath = Path.of(path);
    if (!Files.isDirectory(dirPath)) {
      Files.createDirectories(dirPath);
    }
  }

  public Component createComponent(String name) throws Exception {
    String jsonString = Files.readAllLines(Path.of(path + "/" + name), StandardCharsets.UTF_8).stream()
      .collect(Collectors.joining());
    Component component = parseJsonToComponent(jsonString);
    return component;
  }

  public Component createComponentFromStram(InputStream inputStream) throws Exception {
    String jsonString = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
      .lines()
      .collect(Collectors.joining());
    Component component = parseJsonToComponent(jsonString);
    return component;
  }

  public void saveComponent(String name, Component component) throws Exception {
    String jsonComponent = parseComponentToJson(component.getLinks(), component.getInputs(), component.getOutputs(), component.getComponents());
    BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + name, StandardCharsets.UTF_8));
    writer.write(jsonComponent);
    writer.close();
  }
}