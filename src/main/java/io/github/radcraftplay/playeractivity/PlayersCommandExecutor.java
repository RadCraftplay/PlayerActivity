package io.github.radcraftplay.playeractivity;

import io.github.radcraftplay.playeractivity.player.list.builders.FormattedPlayerListBuilder;
import io.github.radcraftplay.playeractivity.player.list.builders.PlayerListBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class PlayersCommandExecutor implements CommandExecutor {

  private HashMap<String, PlayerConnectionInfo> connections;
  private final PlayerListBuilder playerListBuilder;

  public PlayersCommandExecutor(HashMap<String, PlayerConnectionInfo> connections) {
    this.connections = connections;
    this.playerListBuilder = new FormattedPlayerListBuilder();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!command.getName().equalsIgnoreCase(("players"))) {
      return false;
    }
    if (args.length > 0) {
      return false;
    }

    String playerList = getPlayerList();
    sender.sendMessage(playerList);

    return true;
  }

  private String getPlayerList() {
    return playerListBuilder.buildPlayerList(connections);
  }
}
