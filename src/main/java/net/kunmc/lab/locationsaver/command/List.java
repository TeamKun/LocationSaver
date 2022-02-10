package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.locationsaver.Logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class List extends Command {

  public List(@NotNull String name) {
    super(name);
  }

  @Override
  public void execute(@NotNull CommandContext ctx) {
    LSManager.showList(ctx.getSender());
  }
}
