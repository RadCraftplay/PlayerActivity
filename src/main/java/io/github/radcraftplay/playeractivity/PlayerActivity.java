package io.github.radcraftplay.playeractivity;

import io.github.radcraftplay.playeractivity.commands.executors.PlayersCommandExecutor;
import io.github.radcraftplay.playeractivity.listeners.PlayerActivityListener;
import io.github.radcraftplay.playeractivity.player.list.PlayerListSettings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerActivity extends JavaPlugin {
  @Override
  public void onEnable() {
    loadConfig();

    PlayerConnectionStorage storage = PlayerConnectionStorage.getInstance();
    PlayerListSettings listSettings = getListSettings();

    getServer().getPluginManager().registerEvents(new PlayerActivityListener(storage.connections), this);
    getServer().getPluginCommand("players").setExecutor(new PlayersCommandExecutor(storage.connections));
  }

  private PlayerListSettings getListSettings() {
    PlayerListSettings settings = new PlayerListSettings();
    FileConfiguration config = getConfig();

    settings.setListLength((int)config.get("list.length"));
    settings.setDisplayOnlinePlayers((boolean)config.get("list.displayOnlinePlayers"));

    return settings;
  }

  private void loadConfig() {
    FileConfiguration config = this.getConfig();
    config.addDefault("list.length", 9);
    config.addDefault("list.displayOnlinePlayers", true);

    config.options().copyDefaults(true);
    this.saveConfig();
  }
}
