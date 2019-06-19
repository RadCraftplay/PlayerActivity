package io.github.radcraftplay.playeractivity.player.list.sorters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.*;


/**
 * Sorts list of players by connection info
 */
public class ConnectionInfoPlayerListSorter implements PlayerListSorter {
  @Override
  public Set<Map.Entry<String, PlayerConnectionInfo>> sortPlayerList(HashMap<String, PlayerConnectionInfo> dataToSort) {
    List<Map.Entry<String, PlayerConnectionInfo>> list = new LinkedList<>(dataToSort.entrySet());

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

    return ((Map<String, PlayerConnectionInfo>) sortedHashMap).entrySet();
  }
}
