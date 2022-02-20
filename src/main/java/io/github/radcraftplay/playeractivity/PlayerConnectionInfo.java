package io.github.radcraftplay.playeractivity;

import java.time.Duration;
import java.time.LocalDateTime;

public class PlayerConnectionInfo {
    private String name;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    private boolean connected;

    public LocalDateTime getLastDisconnected() {
        return lastDisconnected;
    }

    public void setLastDisconnected(LocalDateTime lastDisconnected) {
        this.lastDisconnected = lastDisconnected;
    }

    private LocalDateTime lastDisconnected;

    public PlayerConnectionInfo(String name, boolean connected) {
        this.name = name;
        this.connected = connected;
        this.lastDisconnected = LocalDateTime.now();
    }

    public PlayerConnectionInfo(String name, boolean connected, LocalDateTime lastDisconnected) {
        this.name = name;
        this.connected = connected;
        this.lastDisconnected = lastDisconnected;
    }

    public Duration getTimePassedFromLastConnection() {
        if (connected) {
            return Duration.ZERO;
        } else {
            LocalDateTime now = LocalDateTime.now();
            return Duration.between(lastDisconnected, now);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
