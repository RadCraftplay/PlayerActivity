package io.github.radcraftplay.playeractivity.player.list.builders;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import io.github.radcraftplay.playeractivity.player.list.generators.PlayerListGenerator;
import io.github.radcraftplay.playeractivity.player.list.generators.PrettyPlayerListGenerator;
import io.github.radcraftplay.playeractivity.player.list.sorters.ConnectionInfoPlayerListSorter;
import io.github.radcraftplay.playeractivity.player.list.sorters.PlayerListSorter;

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
      listBuilder.append(listGenerator.generateRow(entry.getKey(), entry.getValue()));
    }

    return listBuilder.toString();
  }
}
