package io.github.radcraftplay.playeractivity;

import java.util.HashMap;

public interface PlayerListBuilder {
  String buildPlayerList(HashMap<String, PlayerConnectionInfo> data);
}
