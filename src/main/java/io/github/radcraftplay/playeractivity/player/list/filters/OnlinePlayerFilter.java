package io.github.radcraftplay.playeractivity.player.list.filters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

public class OnlinePlayerFilter implements PlayerFilter {
  private final boolean online;

  public OnlinePlayerFilter(boolean showOnlinePlayersOnline) {
    online = showOnlinePlayersOnline;
  }
  @Override
  public boolean suitsCriteria(String name, PlayerConnectionInfo connectionInfo) {
    return connectionInfo.isConnected() == online;
  }

  @Override
  public MismatchAction getMismatchAction() {
    return MismatchAction.Continue;
  }
}
