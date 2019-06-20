package io.github.radcraftplay.playeractivity.player.list.generators;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.HashMap;

public interface PlayerListGenerator {
  String getListHeader(HashMap<String, PlayerConnectionInfo> data);
  String generateRow(String playerName, PlayerConnectionInfo connectionInfo);
}
