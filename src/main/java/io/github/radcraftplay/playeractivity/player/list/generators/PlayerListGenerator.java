package io.github.radcraftplay.playeractivity.player.list.generators;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.HashMap;
import java.util.Map;

public interface PlayerListGenerator {
  String getListHeader(HashMap<String, PlayerConnectionInfo> data);
  String generateRow(Map.Entry<String, PlayerConnectionInfo> entry);
}
