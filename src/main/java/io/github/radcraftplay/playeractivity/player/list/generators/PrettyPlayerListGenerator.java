package io.github.radcraftplay.playeractivity.player.list.generators;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import org.bukkit.ChatColor;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;

public class PrettyPlayerListGenerator implements PlayerListGenerator {
  @Override
  public String getListHeader(Collection<PlayerConnectionInfo> data) {
    return "Recent player activity:";
  }

  @Override
  public String generateRow(String playerName, PlayerConnectionInfo connectionInfo) {
      return playerName
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
    long days = seconds / 86400;

    if (days < 366 && days >= 32) {
      long months = days / 30;
      return months + " month(s) ago";

    } else if (days < 32 && seconds >= 172800) {
      return days + " day(s) ago";

    } else if (seconds < 172800 && seconds >= 3600) {
      long hours = seconds / 3600;
      return hours + " hour(s) ago";

    } else if (seconds < 3600 && seconds >= 60) {
      long minutes = seconds / 60;
      return minutes + " minute(s) ago";

    } else if (seconds < 60) {
      return seconds + " second(s) ago";

    } else {
      long years = days / 365;
      return years + " year(s) ago";
    }
  }

  private String colorify(String toColorify, ChatColor color) {
    return color.toString()
            + toColorify
            + ChatColor.WHITE;
  }
}
