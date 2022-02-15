package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.locationsaver.logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class Rename extends Command {

  public Rename(@NotNull String name) {
    super(name);

    usage(usageBuilder -> {
      usageBuilder.stringArgument("name", suggestionBuilder -> {
        for (String s : LSManager.nameList()) {
          suggestionBuilder.suggest(s);
        }
      }, null).stringArgument("newName").executes(ctx -> {
        String oldName = ctx.getArgs().get(0);
        String newName = ctx.getArgs().get(1);
        if (LSManager.rename(oldName, newName)) {
          ctx.success(
              oldName.concat("を").concat(newName).concat("に変更しました。")
          );
          return;
        }
        ctx.fail(
            oldName.concat("は存在しません。")
        );
      });
    });
  }
}
