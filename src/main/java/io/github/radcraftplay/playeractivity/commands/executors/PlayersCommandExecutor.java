package io.github.radcraftplay.playeractivity.commands.executors;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import io.github.radcraftplay.playeractivity.player.list.PlayerListSettings;
import io.github.radcraftplay.playeractivity.player.list.builders.FormattedPlayerListBuilder;
import io.github.radcraftplay.playeractivity.player.list.builders.PlayerListBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class PlayersCommandExecutor implements CommandExecutor {

  private HashMap<String, PlayerConnectionInfo> connections;
  private final PlayerListBuilder playerListBuilder;

  public PlayersCommandExecutor(
          HashMap<String, PlayerConnectionInfo> connections,
          PlayerListSettings listSettings) {
    this.connections = connections;
    this.playerListBuilder = new FormattedPlayerListBuilder(listSettings);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!command.getName().equalsIgnoreCase(("players"))) {
      return false;
    }
    if (args.length > 0) {
      return false;
    }
    if (!command.testPermission(sender)) {
      return true;
    }

    String playerList = getPlayerList();
    sender.sendMessage(playerList);

    return true;
  }

  private String getPlayerList() {
    return playerListBuilder.buildPlayerList(connections);
  }
}
