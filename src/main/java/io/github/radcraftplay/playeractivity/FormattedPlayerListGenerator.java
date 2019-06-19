package io.github.radcraftplay.playeractivity;

import org.bukkit.ChatColor;

import java.time.Duration;
import java.util.*;

public class FormattedPlayerListGenerator implements PlayerListGenerator {
  private final String pluginPrefix;

  public FormattedPlayerListGenerator() {
    pluginPrefix = ChatColor.GREEN
            + "[PlayerActivity] "
            + ChatColor.WHITE;
  }

  @Override
  public String generatePlayerList(HashMap<String, PlayerConnectionInfo> data) {
    StringBuilder listBuilder = new StringBuilder();
    Map<String, PlayerConnectionInfo> playerList = sortByConnection(data);
    Set<Map.Entry<String, PlayerConnectionInfo>> playerSet = playerList.entrySet();

    listBuilder.append("Recent player activity:");

    for (Map.Entry<String, PlayerConnectionInfo> entry : playerSet) {
      listBuilder.append("\n ");
      listBuilder.append(generateRow(entry));
    }

    return listBuilder.toString();
  }

  private HashMap<String, PlayerConnectionInfo> sortByConnection(HashMap<String, PlayerConnectionInfo> map) {
    List<Map.Entry<String, PlayerConnectionInfo>> list = new LinkedList<>(map.entrySet());

    list.sort((entry1, entry2) -> {
      PlayerConnectionInfo info1 = entry1.getValue();
      PlayerConnectionInfo info2 = entry2.getValue();
      return info1.getTimePassedFromLastConnection().compareTo(
              info2.getTimePassedFromLastConnection());
    });

    HashMap<String, PlayerConnectionInfo> sortedHashMap = new LinkedHashMap<>();

    for (Object o : list) {
      Map.Entry entry = (Map.Entry) o;
      sortedHashMap.put((String) entry.getKey(), (PlayerConnectionInfo) entry.getValue());
    }

    return sortedHashMap;
  }

  private String generateRow(Map.Entry<String, PlayerConnectionInfo> entry) {
    String playerName = entry.getKey();
    PlayerConnectionInfo connectionInfo = entry.getValue();

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

  private String getWithPrefix(String toPrint) {
    return pluginPrefix + toPrint;
  }

  private String colorify(String toColorify, ChatColor color) {
    return color.toString()
            + toColorify
            + ChatColor.WHITE;
  }
}
