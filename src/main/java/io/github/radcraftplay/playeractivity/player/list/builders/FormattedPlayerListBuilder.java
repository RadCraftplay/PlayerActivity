package io.github.radcraftplay.playeractivity.player.list.builders;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import io.github.radcraftplay.playeractivity.player.list.PlayerListSettings;
import io.github.radcraftplay.playeractivity.player.list.filters.*;
import io.github.radcraftplay.playeractivity.player.list.generators.PlayerListGenerator;
import io.github.radcraftplay.playeractivity.player.list.generators.PrettyPlayerListGenerator;
import io.github.radcraftplay.playeractivity.player.list.sorters.ConnectionInfoPlayerListSorter;
import io.github.radcraftplay.playeractivity.player.list.sorters.PlayerListSorter;

import java.util.*;

public class FormattedPlayerListBuilder implements PlayerListBuilder {
  private final PlayerListGenerator listGenerator;
  private final PlayerListSorter listSorter;
  private final PlayerListSettings listSettings;
  private final List<PlayerFilter> playerFilters;

  public FormattedPlayerListBuilder(PlayerListSettings settings) {
    listGenerator = new PrettyPlayerListGenerator();
    listSorter = new ConnectionInfoPlayerListSorter();
    listSettings = settings;
    playerFilters = getPlayerFilters(settings);
  }

  private List<PlayerFilter> getPlayerFilters(PlayerListSettings settings) {
    List<PlayerFilter> filters = new ArrayList<>();
    if (!settings.getDisplayOnlinePlayers()) {
      filters.add(new OnlinePlayerFilter(false));
    }

    if (settings.getLimitListLength()) {
      filters.add(new CountFilter(listSettings.getListLength()));
    }

    return filters;
  }

  @Override
  public String buildPlayerList(Collection<PlayerConnectionInfo> data) {
    StringBuilder listBuilder = new StringBuilder();
    Collection<PlayerConnectionInfo> playerSet = listSorter.sortPlayerList(data);

    resetFilters();

    listBuilder.append(listGenerator.getListHeader(data));

    for (PlayerConnectionInfo info : playerSet) {
      String playerName = info.getName();
      MismatchAction action = null;

      for (PlayerFilter filter : playerFilters) {
        if (!filter.suitsCriteria(playerName, info)) {
          action = filter.getMismatchAction();
        }
      }

      if (action != null) {
        notifyFiltersPlayerSkipped();

        if (action == MismatchAction.Continue) {
          continue;
        } else if (action == MismatchAction.Break) {
          break;
        }
      }

      listBuilder.append("\n ");
      listBuilder.append(listGenerator.generateRow(playerName, info));
    }

    return listBuilder.toString();
  }

  private void notifyFiltersPlayerSkipped() {
    for (PlayerFilter filter : playerFilters) {
      if (filter instanceof NotifiablePlayerFilter) {
        ((NotifiablePlayerFilter) filter).onPlayerSkipped();
      }
    }
  }

  private void resetFilters() {
    for (PlayerFilter filter : playerFilters) {
      if (filter instanceof ResettablePlayerFilter) {
        ((ResettablePlayerFilter) filter).reset();
      }
    }
  }
}
