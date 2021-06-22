package com.linksync.backend.service;

import com.linksync.backend.api.Link;
import com.linksync.backend.nongate.Line;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LinkSync {
  private final List<Link> links;

  public void followLink(Link link) {
    links.add(link);
  }

  public List<Link> runBatch() {
    if (links.isEmpty()) {
      return links;
    }
    List<Link> oldLinks = new ArrayList<>();
    List<Link> newLinks = new ArrayList<>();
    for (Link link : links) {
      link.propagate();
      newLinks.addAll(link.getOutputs().stream()
        .map(Line::getLink)
        .collect(Collectors.toList()));
    }
    oldLinks.addAll(links);
    links.clear();
    links.addAll(newLinks);
    return oldLinks;
  }
}