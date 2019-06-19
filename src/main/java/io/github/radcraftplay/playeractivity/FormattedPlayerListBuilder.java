package io.github.radcraftplay.playeractivity;

import java.util.*;

public class FormattedPlayerListBuilder implements PlayerListBuilder {
  private PlayerListFormatter listFormatter;

  public FormattedPlayerListBuilder() {
    listFormatter = new PrettyPlayerListFormatter();
  }

  @Override
  public String buildPlayerList(HashMap<String, PlayerConnectionInfo> data) {
    StringBuilder listBuilder = new StringBuilder();
    Map<String, PlayerConnectionInfo> playerList = sortByConnection(data);
    Set<Map.Entry<String, PlayerConnectionInfo>> playerSet = playerList.entrySet();

    listBuilder.append(listFormatter.getListHeader(data));

    for (Map.Entry<String, PlayerConnectionInfo> entry : playerSet) {
      listBuilder.append("\n ");
      listBuilder.append(listFormatter.generateRow(entry));
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
}
