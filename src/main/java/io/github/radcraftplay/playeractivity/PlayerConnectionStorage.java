package io.github.radcraftplay.playeractivity;

import java.util.HashMap;

public class PlayerConnectionStorage {
  public static PlayerConnectionStorage getInstance() {
    if (instance == null) {
      instance = new PlayerConnectionStorage();
    }

    return instance;
  }

  private static PlayerConnectionStorage instance;

  public HashMap<String, PlayerConnectionInfo> connections = new HashMap<>();

  private PlayerConnectionStorage() {}
}
