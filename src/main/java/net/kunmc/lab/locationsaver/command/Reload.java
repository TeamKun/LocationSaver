package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.locationsaver.logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class Reload extends Command {

  public Reload(@NotNull String name) {
    super(name);
  }

  @Override
  public void execute(@NotNull CommandContext ctx) {
    LSManager.init();
    ctx.success("座標リストをリロードしました");
  }
}
