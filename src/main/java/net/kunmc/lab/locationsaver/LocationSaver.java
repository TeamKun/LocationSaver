package net.kunmc.lab.locationsaver;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import net.kunmc.lab.locationsaver.Logic.LSManager;
import net.kunmc.lab.locationsaver.command.Main;
import net.kunmc.lab.locationsaver.command.Teleport;
import net.kunmc.lab.locationsaver.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class LocationSaver extends JavaPlugin {

  public static LocationSaver plugin;
  public static Config config;

  @Override
  public void onEnable() {
    plugin = this;

    config = new Config(this);
    config.saveConfigIfAbsent();
    config.loadConfig();

    // csvロード
    LSManager.init();

    FlyLib.create(this, builder -> {
      builder.command(new Main("locsave", new ConfigCommandBuilder(config).build()));
      builder.command(new Teleport("tpl"));
    });
  }

  @Override
  public void onDisable() {
  }
}
