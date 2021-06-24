package com.linksync.backend.service;

import com.linksync.backend.api.Link;
import com.linksync.backend.nongate.Line;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkSync {
  private static final List<Link> startLinks=new ArrayList<>();
  private static final List<Link> ongoingLinks=new ArrayList<>();
  @Getter
  private static int propagationTime;

  public static void followLink(Link link) {
    startLinks.add(link);
    ongoingLinks.add(link);
  }

  public static void unfollowLink(Link link) {
    startLinks.remove(link);
    ongoingLinks.add(link);
  }

  public static boolean start() {
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