package net.kunmc.lab.locationsaver.logic;

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

    saveCsv();
    return true;
  }

  public static boolean removeLocation(String locationName) {
    if (!locationList.remove(locationName)) {
      return false;
    }

    saveCsv();
    return true;
  }

  public static List<String> nameList() {
    return locationList.nameList();
  }

  public static void showList(CommandSender sender) {

    if (locationList.enableList().size() == 0) {
      sender.sendMessage(DecorationConst.RED + "座標が登録されていません");
      return;
    }
    sender.sendMessage(DecorationConst.GREEN + "登録された座標を表示します");
    sender.sendMessage(
        "登録件数："
            .concat(DecorationConst.YELLOW)
            .concat(String.valueOf(locationList.enableList().size()))
            .concat(DecorationConst.RESET)
            .concat("件")
    );

    for (LSLocation location : locationList.enableList()) {
      sender.sendMessage("-----------------------------------------------");
      sender.sendMessage(
          DecorationConst.AQUA
              .concat(location.name())
      );
      sender.sendMessage(
          "X= "
              .concat(DecorationConst.YELLOW).concat(location.locX())
              .concat(DecorationConst.RESET).concat(",")
              .concat("Y= ")
              .concat(DecorationConst.YELLOW).concat(location.locY())
              .concat(DecorationConst.RESET).concat(",")
              .concat("Z= ")
              .concat(DecorationConst.YELLOW).concat(location.locZ())
              .concat(DecorationConst.RESET)
              .concat(" | ")
              .concat(DecorationConst.LIGHT_PURPLE).concat(location.worldType().typeName)
      );
      sender.sendMessage(
          "保存者： "
              .concat(DecorationConst.YELLOW)
              .concat(location.setterName())
      );
    }
    sender.sendMessage("-----------------------------------------------");
  }

  public static void teleport(CommandSender sender, String name) {
    locationList.get(name).teleport(sender);
  }

  public static boolean rename(String oldName, String newName) {
    if (!locationList.rename(oldName, newName)) {
      return false;
    }

    saveCsv();
    return true;
  }

  public static boolean reset(String locationName, Player sender) {
    if (!locationList.reset(locationName, sender)) {
      return false;
    }

    saveCsv();
    return true;
  }

  public static void saveCsv() {
    if (csvManager != null) {
      csvManager.save(locationList);
    }
  }
}
