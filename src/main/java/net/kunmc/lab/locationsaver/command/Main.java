package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.configlib.ConfigCommand;
import org.jetbrains.annotations.NotNull;

public class Main extends Command {

  public Main(@NotNull String name, ConfigCommand configCommand) {
    super(name);
    children(configCommand,
        new Save("save"),
        new Remove("remove"),
        new List("list"),
        new Rename("rename"),
        new Reset("reset")
    );
  }
}
