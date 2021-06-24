package com.linksync.backend.service;

import com.linksync.backend.api.Link;
import com.linksync.backend.nongate.Clock;
import com.linksync.backend.nongate.Line;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkSync {
  private final List<Link> startLinks = new ArrayList<>();
  private final List<Link> ongoingLinks = new ArrayList<>();
  private final List<Clock> clocks = new ArrayList<>();
  @Getter
  private int propagationTime;
  private final static LinkSync linkSync = new LinkSync();

  public static LinkSync getDefault(){
    return linkSync;
  }

  public void trigger(Link link){
    ongoingLinks.add(link);
  }

  public void followLink(Link link) {
    startLinks.add(link);
    ongoingLinks.add(link);
    if(link instanceof Clock){
      clocks.add((Clock)link);
    }
  }

  public void unfollowLink(Link link) {
    startLinks.remove(link);
    ongoingLinks.remove(link);
    if(link instanceof Clock){
      clocks.remove((Clock)link);
    }
  }

  public boolean start() {
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
    clocks.forEach(Clock::trigger);
    if(ongoingLinks.isEmpty()){
      ongoingLinks.addAll(startLinks);
      return false;
    }
    return true;
  }
}