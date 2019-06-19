package io.github.radcraftplay.playeractivity;

import java.util.HashMap;
import java.util.Map;

public interface PlayerListGenerator {
  String getListHeader(HashMap<String, PlayerConnectionInfo> data);
  String generateRow(Map.Entry<String, PlayerConnectionInfo> entry);
}
