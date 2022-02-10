package net.kunmc.lab.locationsaver.Logic;

import java.util.List;
import net.kunmc.lab.locationsaver.file.CsvManager;
import net.kunmc.lab.locationsaver.location.LSLocationList;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LSManager {

  private static LSLocationList locationList;
  private static CsvManager csvManager;

  public static void init() {
    csvManager = new CsvManager();
    locationList = new LSLocationList(csvManager.getStringArray());
  }

  public static boolean addLocation(Player player, String locationName) {
    if (!locationList.add(player, locationName)) {
      return false;
    }

    csvManager.save(locationList);
    return true;
  }

  public static boolean removeLocation(String locationName) {
    if (!locationList.remove(locationName)) {
      return false;
    }

    csvManager.save(locationList);
    return true;
  }

  public static List<String> nameList() {
    return locationList.nameList();
  }

  public static void teleport(CommandSender sender, String name) {
    locationList.get(name).teleport(sender);
  }
}
