package io.github.radcraftplay.playeractivity;

import io.github.radcraftplay.playeractivity.listeners.PlayerActivityListener;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerActivity extends JavaPlugin {
  @Override
  public void onEnable() {
    PlayerConnectionStorage storage = PlayerConnectionStorage.getInstance();

    getServer().getPluginManager().registerEvents(new PlayerActivityListener(storage.connections), this);
    getServer().getPluginCommand("players").setExecutor(new PlayersCommandExecutor(storage.connections));
  }
}
