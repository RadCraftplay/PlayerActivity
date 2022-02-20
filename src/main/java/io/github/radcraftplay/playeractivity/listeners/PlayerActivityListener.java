package io.github.radcraftplay.playeractivity.listeners;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import io.github.radcraftplay.playeractivity.repositories.Repository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerActivityListener implements Listener {
  private Repository<String, PlayerConnectionInfo> repository;

  public PlayerActivityListener(Repository<String, PlayerConnectionInfo> repository) {
      this.repository = repository;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    String playerName = event.getPlayer().getDisplayName();

    if (repository.get(playerName) == null) {
      repository.add(new PlayerConnectionInfo(playerName, true));
    } else {
      repository.update(playerName, new PlayerConnectionInfo(playerName, true));
    }
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    String playerName = event.getPlayer().getDisplayName();

      if (repository.get(playerName) == null) {
          repository.add(new PlayerConnectionInfo(playerName, false));
      } else {
          repository.update(playerName, new PlayerConnectionInfo(playerName, false));
      }
  }

  @EventHandler
  public void onPlayerKick(PlayerKickEvent event) {
    String playerName = event.getPlayer().getDisplayName();

      if (repository.get(playerName) != null) {
          repository.add(new PlayerConnectionInfo(playerName, false));
      } else {
          repository.update(playerName, new PlayerConnectionInfo(playerName, false));
      }
  }
}
