package io.github.radcraftplay.playeractivity.player.list.filters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

public interface PlayerFilter {
  boolean suitsCriteria(String name, PlayerConnectionInfo connectionInfo);
  MismatchAction getMismatchAction();
}
