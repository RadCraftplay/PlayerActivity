package io.github.radcraftplay.playeractivity.player.list.generators;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.Collection;
import java.util.HashMap;

public interface PlayerListGenerator {
  String getListHeader(Collection<PlayerConnectionInfo> data);
  String generateRow(String playerName, PlayerConnectionInfo connectionInfo);
}
