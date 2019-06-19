package io.github.radcraftplay.playeractivity;

import java.util.HashMap;

public interface PlayerListGenerator {
  String generatePlayerList(HashMap<String, PlayerConnectionInfo> data);
}
