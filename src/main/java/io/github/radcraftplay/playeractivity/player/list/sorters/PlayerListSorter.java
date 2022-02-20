package io.github.radcraftplay.playeractivity.player.list.sorters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface PlayerListSorter {
    Collection<PlayerConnectionInfo> sortPlayerList(Collection<PlayerConnectionInfo> dataToSort);
}
