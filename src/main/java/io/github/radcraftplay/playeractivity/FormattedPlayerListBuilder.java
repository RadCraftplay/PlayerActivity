package io.github.radcraftplay.playeractivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FormattedPlayerListBuilder implements PlayerListBuilder {
  private final PlayerListGenerator listGenerator;
  private final PlayerListSorter listSorter;

  public FormattedPlayerListBuilder() {
    listGenerator = new PrettyPlayerListGenerator();
    listSorter = new ConnectionInfoPlayerListSorter();
  }

  @Override
  public String buildPlayerList(HashMap<String, PlayerConnectionInfo> data) {
    StringBuilder listBuilder = new StringBuilder();
    Set<Map.Entry<String, PlayerConnectionInfo>> playerSet = listSorter.sortPlayerList(data);

    listBuilder.append(listGenerator.getListHeader(data));

    for (Map.Entry<String, PlayerConnectionInfo> entry : playerSet) {
      listBuilder.append("\n ");
      listBuilder.append(listGenerator.generateRow(entry));
    }

    return listBuilder.toString();
  }
}
