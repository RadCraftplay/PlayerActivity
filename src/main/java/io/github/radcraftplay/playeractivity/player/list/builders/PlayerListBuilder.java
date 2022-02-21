package io.github.radcraftplay.playeractivity.player.list.builders;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.Collection;
import java.util.HashMap;

public interface PlayerListBuilder {
  String buildPlayerList(Collection<PlayerConnectionInfo> data);
}
