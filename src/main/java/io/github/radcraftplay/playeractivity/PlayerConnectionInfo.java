package io.github.radcraftplay.playeractivity;

import java.time.Duration;
import java.time.LocalDateTime;

public class PlayerConnectionInfo {
  private boolean connected = true;
  private LocalDateTime lastDisconnected = null;

  public boolean isConnected() {
    return connected;
  }

  public void setConnected() {
    connected = true;
    lastDisconnected = null;
  }

  public void setDisconnected() {
    connected = false;
    lastDisconnected = LocalDateTime.now();
  }

  public Duration getTimePassedFromLastConnection() {
    if (connected) {
      return Duration.ZERO;
    } else {
      LocalDateTime now = LocalDateTime.now();
      return Duration.between(lastDisconnected, now);
    }
  }
}
