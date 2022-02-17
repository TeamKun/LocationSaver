package net.kunmc.lab.locationsaver;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.locationsaver.command.Main;
import net.kunmc.lab.locationsaver.command.Teleport;
import net.kunmc.lab.locationsaver.logic.LSManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LocationSaver extends JavaPlugin {

  public static LocationSaver plugin;

  @Override
  public void onEnable() {
    plugin = this;

    // csvロード
    LSManager.init();

    FlyLib.create(this, builder -> {
      builder.command(new Main("locsave"));
      builder.command(new Teleport("tpl"));
    });
  }

  @Override
  public void onDisable() {
    //LSManager.saveCsv();
  }
}
