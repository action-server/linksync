package com.linksync.backend.service;

import com.linksync.backend.api.Link;
import com.linksync.backend.nongate.Line;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LinkSync {
  private final List<Link> startLinks;
  private final List<Link> ongoingLinks;
  @Getter
  private static int propagationTime;

  public void followLink(Link link) {
    startLinks.add(link);
    ongoingLinks.add(link);
  }

  public void unfollowLink(Link link) {
    startLinks.remove(link);
    ongoingLinks.add(link);
  }

  public boolean start() {
    if(ongoingLinks.isEmpty()){
      ongoingLinks.addAll(startLinks);
      return false;
    }
    List<Link> newLinks = new ArrayList<>();
    for (Link link : ongoingLinks) {
      link.propagate();
      newLinks.addAll(link.getOutputs().stream()
        .map(Line::getLink)
        .collect(Collectors.toList()));
    }
    ongoingLinks.clear();
    ongoingLinks.addAll(newLinks);
    propagationTime++;
    return true;
  }
}