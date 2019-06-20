package io.github.radcraftplay.playeractivity.player.list;

public class PlayerListSettings {
  private int listLength;
  private boolean displayOnlinePlayers;
  private boolean limitListLength;

  public int getListLength() {
    return listLength;
  }

  public void setListLength(int listLength) {
    this.listLength = listLength;
  }

  public boolean getDisplayOnlinePlayers() {
    return displayOnlinePlayers;
  }

  public void setDisplayOnlinePlayers(boolean displayOnlinePlayers) {
    this.displayOnlinePlayers = displayOnlinePlayers;
  }

  public boolean getLimitListLength() {
    return limitListLength;
  }

  public void setLimitListLength(boolean limitListLength) {
    this.limitListLength = limitListLength;
  }
}
