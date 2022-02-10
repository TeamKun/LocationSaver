package net.kunmc.lab.locationsaver.Logic;

import java.util.List;
import net.kunmc.lab.locationsaver.file.CsvManager;
import net.kunmc.lab.locationsaver.location.LSLocation;
import net.kunmc.lab.locationsaver.location.LSLocationList;
import net.kunmc.lab.locationsaver.util.DecorationConst;
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

  public static void showList(CommandSender sender) {
    sender.sendMessage(
        DecorationConst.GREEN
            .concat("登録件数：")
            .concat(String.valueOf(locationList.enableList().size()))
            .concat("件")
    );

    for (LSLocation location : locationList.enableList()) {
      sender.sendMessage(
          DecorationConst.GREEN
              .concat(location.name())
              .concat("　；　")
              .concat("x=").concat(location.locX()).concat(",")
              .concat("y").concat(location.locY()).concat(",")
              .concat("z=").concat(location.locZ()).concat(",")
              .concat(location.worldType().typeName).concat("｜")
              .concat("保存者：").concat(location.setterName())
      );
    }
  }

  public static void teleport(CommandSender sender, String name) {
    locationList.get(name).teleport(sender);
  }

  public static boolean rename(String oldName, String newName) {
    if (!locationList.rename(oldName, newName)) {
      return false;
    }

    csvManager.save(locationList);
    return true;
  }
}
