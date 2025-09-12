package com.codedifferently.lesson16.tobyevans;

import java.util.ArrayList;
import java.util.List;

public class VideoProject {
  private String ownerName;
  private List<String> clips;
  private int totalSeconds;
  private Status status;
  private boolean monetized;
  private int clipCount;

  public VideoProject(String ownerName, boolean monetized) {
    this.ownerName = ownerName;
    this.monetized = monetized;
    this.clips = new ArrayList<>();
    this.totalSeconds = 0;
    this.status = Status.PENDING;
    this.clipCount = 0;
  }

  public void addClip(String name, int seconds) {
    if (name == null || name.isEmpty() || seconds < 0) {
      throw new InvalidOrderOperationException("Invalid clip");
    }
    clips.add(name);
    totalSeconds += seconds;
    clipCount = clips.size();
  }

  public void trim(int seconds) {
    if (seconds < 0 || seconds > totalSeconds) {
      throw new InvalidOrderOperationException("Trim out of range");
    }
    totalSeconds -= seconds;
  }

  public void publish() {
    if (status != Status.PENDING) {
      throw new InvalidOrderOperationException("Only pending can publish");
    }
    status = Status.PAID;
  }

  public void cancel() {
    if (status == Status.PAID) {
      throw new InvalidOrderOperationException("Cannot cancel after publish");
    }
    status = Status.CANCELLED;
  }

  public String getSummary() {
    StringBuilder sb = new StringBuilder();
    sb.append("VideoProject{owner=")
        .append(ownerName)
        .append(", status=")
        .append(status)
        .append(", clips=[");
    for (int i = 0; i < clips.size(); i++) {
      sb.append(clips.get(i));
      if (i < clips.size() - 1) sb.append(",");
    }
    sb.append("], totalSeconds=")
        .append(totalSeconds)
        .append(", monetized=")
        .append(monetized)
        .append("}");
    return sb.toString();
  }

  public String getOwnerName() {
    return ownerName;
  }

  public int getClipCount() {
    return clipCount;
  }

  public int getTotalSeconds() {
    return totalSeconds;
  }

  public Status getStatus() {
    return status;
  }

  public boolean isMonetized() {
    return monetized;
  }
}
