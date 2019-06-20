package io.github.radcraftplay.playeractivity.player.list.generators;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import org.bukkit.ChatColor;

import java.time.Duration;
import java.util.HashMap;

public class PrettyPlayerListGenerator implements PlayerListGenerator {
  @Override
  public String getListHeader(HashMap<String, PlayerConnectionInfo> data) {
    return "Recent player activity:";
  }

  @Override
  public String generateRow(String playerName, PlayerConnectionInfo connectionInfo) {
      return '\n'
              + playerName
              + " - "
              + getLastOnlineString(connectionInfo);
  }

  private String getLastOnlineString(PlayerConnectionInfo connectionInfo) {
    if (connectionInfo.isConnected()) {
      return colorify("Online", ChatColor.GREEN);
    } else {
      return colorify(
              "Last online "  + getRepresentativeTimestamp(connectionInfo.getTimePassedFromLastConnection()),
              ChatColor.YELLOW);
    }
  }

  private String getRepresentativeTimestamp(Duration timePassedSinceLastConnection) {
    long seconds = timePassedSinceLastConnection.getSeconds();

    if (seconds < 172800 && seconds >= 3600) {
      long hours = seconds / 3600;
      return hours + " hours ago";

    } else if (seconds < 3600 && seconds >= 60) {
      long minutes = seconds / 60;
      return minutes + " minute(s) ago";

    } else if (seconds < 60) {
      return seconds + " second(s) ago";

    } else {
      long days = seconds / 86400;
      return days + " day(s) ago";
    }
  }

  private String colorify(String toColorify, ChatColor color) {
    return color.toString()
            + toColorify
            + ChatColor.WHITE;
  }
}
