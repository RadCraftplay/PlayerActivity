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
    getServer().getPluginCommand("players").setExecutor(new PlayersCommandExecutor(storage.connections, listSettings));
  }

  private PlayerListSettings getListSettings() {
    PlayerListSettings settings = new PlayerListSettings();
    FileConfiguration config = getConfig();

    settings.setDisplayOnlinePlayers((boolean)config.get("list.displayOnlinePlayers"));
    settings.setLimitListLength((boolean)config.get("list.limitListLength"));
    settings.setListLength((int)config.get("list.maxPlayers"));

    return settings;
  }

  private void loadConfig() {
    FileConfiguration config = this.getConfig();
    config.addDefault("list.displayOnlinePlayers", true);
    config.addDefault("list.limitListLength", true);
    config.addDefault("list.maxPlayers", 9);

    config.options().copyDefaults(true);
    this.saveConfig();
  }
}
