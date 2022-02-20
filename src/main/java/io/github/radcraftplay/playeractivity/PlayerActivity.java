package io.github.radcraftplay.playeractivity;

import io.github.radcraftplay.playeractivity.commands.executors.PlayersCommandExecutor;
import io.github.radcraftplay.playeractivity.listeners.PlayerActivityListener;
import io.github.radcraftplay.playeractivity.player.list.PlayerListSettings;
import io.github.radcraftplay.playeractivity.repositories.MysqlRepository;
import io.github.radcraftplay.playeractivity.repositories.mysql.MysqlSettings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.logging.Level;

public class PlayerActivity extends JavaPlugin {
    @Override
    public void onEnable() {
        loadConfig();

        PlayerListSettings listSettings = getListSettings();
        MysqlSettings dbSettings = getMysqlSettings();
        MysqlRepository repo;

        try {
            repo = new MysqlRepository(dbSettings);
        } catch (SQLException throwables) {
            getServer()
                    .getLogger()
                    .log(Level.SEVERE, throwables.getLocalizedMessage());
            throwables.printStackTrace();

            return; // Too bad, quit
        }

        getServer()
                .getPluginManager()
                .registerEvents(new PlayerActivityListener(repo), this);
        getServer()
                .getPluginCommand("players")
                .setExecutor(new PlayersCommandExecutor(repo, listSettings));
    }

    private PlayerListSettings getListSettings() {
        PlayerListSettings settings = new PlayerListSettings();
        FileConfiguration config = getConfig();

        settings.setDisplayOnlinePlayers((boolean) config.get("list.displayOnlinePlayers"));
        settings.setLimitListLength((boolean) config.get("list.limitListLength"));
        settings.setListLength((int) config.get("list.maxPlayers"));

        return settings;
    }

    private MysqlSettings getMysqlSettings() {
        MysqlSettings settings = new MysqlSettings();
        FileConfiguration config = getConfig();

        settings.setServerAddress((String) config.get("mysql.serverAddress"));
        settings.setDatabaseName((String) config.get("mysql.databaseName"));
        settings.setUsername((String) config.get("mysql.username"));
        settings.setPassword((String) config.get("mysql.password"));

        return settings;
    }

    private void loadConfig() {
        FileConfiguration config = this.getConfig();
        config.addDefault("list.displayOnlinePlayers", true);
        config.addDefault("list.limitListLength", true);
        config.addDefault("list.maxPlayers", 9);
        config.addDefault("mysql.serverAddress", "localhost:1433");
        config.addDefault("mysql.databaseName", "name");
        config.addDefault("mysql.username", "name");
        config.addDefault("mysql.password", "password");

        config.options().copyDefaults(true);
        this.saveConfig();
    }
}
