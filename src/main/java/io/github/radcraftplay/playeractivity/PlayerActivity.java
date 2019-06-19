package io.github.radcraftplay.playeractivity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PlayerActivity extends JavaPlugin implements Listener {
  public HashMap<String, PlayerConnectionInfo> connections = new HashMap<>();

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getServer().getPluginCommand("players").setExecutor(new PlayersCommandExecutor(connections));
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
