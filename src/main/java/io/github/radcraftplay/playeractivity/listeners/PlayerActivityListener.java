package io.github.radcraftplay.playeractivity.listeners;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class PlayerActivityListener implements Listener {
  private HashMap<String, PlayerConnectionInfo> connections;

  public PlayerActivityListener(HashMap<String, PlayerConnectionInfo> connections) {
    this.connections = connections;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    String playerName = event.getPlayer().getDisplayName();

    if (connections.containsKey(playerName)) {
      connections.get(playerName).setConnected();
    } else {
      connections.put(playerName, new PlayerConnectionInfo());
    }
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    String playerName = event.getPlayer().getDisplayName();

    if (connections.containsKey(playerName)) {
      connections.get(playerName).setDisconnected();
    } else {
      PlayerConnectionInfo info = new PlayerConnectionInfo();
      info.setDisconnected();

      connections.put(playerName, info);
    }
  }

  @EventHandler
  public void onPlayerKick(PlayerKickEvent event) {
    String playerName = event.getPlayer().getDisplayName();

    if (connections.containsKey(playerName)) {
      connections.get(playerName).setDisconnected();
    } else {
      PlayerConnectionInfo info = new PlayerConnectionInfo();
      info.setDisconnected();

      connections.put(playerName, info);
    }
  }
}
