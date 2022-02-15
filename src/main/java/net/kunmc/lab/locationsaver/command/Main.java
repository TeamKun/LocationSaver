package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import org.jetbrains.annotations.NotNull;

public class Main extends Command {

  public Main(@NotNull String name) {
    super(name);
    children(
        new Save("save"),
        new Remove("remove"),
        new List("list"),
        new Rename("rename"),
        new Reset("reset"),
        new Reload("reload")
    );
  }
}
