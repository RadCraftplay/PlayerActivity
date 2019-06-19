package io.github.radcraftplay.playeractivity.player.list.sorters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface PlayerListSorter {
  Set<Map.Entry<String, PlayerConnectionInfo>> sortPlayerList(HashMap<String, PlayerConnectionInfo> dataToSort);
}
