package io.github.radcraftplay.playeractivity;

import java.util.HashMap;
import java.util.Map;

public interface PlayerListFormatter {
  String getListHeader(HashMap<String, PlayerConnectionInfo> data);
  String generateRow(Map.Entry<String, PlayerConnectionInfo> entry);
}
