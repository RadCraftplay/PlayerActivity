package io.github.radcraftplay.playeractivity.player.list.filters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

public class CountFilter implements PlayerFilter, ResettablePlayerFilter, NotifiablePlayerFilter {
  private final int limit;
  private int current = 0;

  public CountFilter(int maxPlayers) {
    limit = maxPlayers;
  }

  @Override
  public boolean suitsCriteria(String name, PlayerConnectionInfo connectionInfo) {
    return current++ < limit;
  }

  @Override
  public MismatchAction getMismatchAction() {
    return MismatchAction.Break;
  }

  @Override
  public void reset() {
    current = 0;
  }

  @Override
  public void onPlayerSkipped() {
    current--;
  }
}
