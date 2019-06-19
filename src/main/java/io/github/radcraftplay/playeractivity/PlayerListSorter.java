package io.github.radcraftplay.playeractivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface PlayerListSorter {
  Set<Map.Entry<String, PlayerConnectionInfo>> sortPlayerList(HashMap<String, PlayerConnectionInfo> dataToSort);
}
