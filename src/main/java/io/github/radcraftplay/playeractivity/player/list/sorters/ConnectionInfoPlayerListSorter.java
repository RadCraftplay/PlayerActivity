package io.github.radcraftplay.playeractivity.player.list.sorters;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;

import java.util.*;


/**
 * Sorts list of players by connection info
 */
public class ConnectionInfoPlayerListSorter implements PlayerListSorter {
    @Override
    public Collection<PlayerConnectionInfo> sortPlayerList(Collection<PlayerConnectionInfo> dataToSort) {
        LinkedList<PlayerConnectionInfo> list = new LinkedList<>(dataToSort);
        list.sort(Comparator.comparing(PlayerConnectionInfo::getTimePassedFromLastConnection));
        return list;
    }
}
