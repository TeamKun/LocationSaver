package net.kunmc.lab.locationsaver.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LSLocation {

  private String name;
  private String locX;
  private String locY;
  private String locZ;
  private LSWorldType worldType;
  private String setterName;
  private boolean isDeleted;

  public LSLocation(String name,
      String x,
      String y,
      String z,
      String worldTypeName,
      String sender,
      String isDeleted) {

    this.name = name;
    this.locX = String.valueOf(x);
    this.locY = String.valueOf(y);
    this.locZ = String.valueOf(z);
    this.worldType = LSWorldType.getByWorldName(worldTypeName);
    this.setterName = sender;
    this.isDeleted = Boolean.valueOf(isDeleted);
  }

  public LSLocation(Player setter, String name) {
    Location location = setter.getLocation();
    this.name = name;
    this.locX = String.valueOf(location.getBlockX());
    this.locY = String.valueOf(location.getBlockY());
    this.locZ = String.valueOf(location.getBlockZ());
    this.worldType = LSWorldType.getByWorldName(location.getWorld().getName());
    this.setterName = setter.getName();
  }

  public String name() {
    return name;
  }

  public String locX() {
    return locX;
  }

  public String locY() {
    return locY;
  }

  public String locZ() {
    return locZ;
  }

  public LSWorldType worldType() {
    return worldType;
  }

  public String setterName() {
    return setterName;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  void delete() {
    this.isDeleted = true;
  }

  public void teleport(CommandSender target) {
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
        "execute in "
            .concat(this.worldType.typeName)
            .concat(" run tp ")
            .concat(target.getName())
            .concat(" ")
            .concat(locX)
            .concat(" ")
            .concat(locY)
            .concat(" ")
            .concat(locZ)
    );
  }
}
