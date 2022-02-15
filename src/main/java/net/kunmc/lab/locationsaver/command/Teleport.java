package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.Permission;
import net.kunmc.lab.locationsaver.logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class Teleport extends Command {

  public Teleport(@NotNull String name) {
    super(name);
    permission(Permission.getEVERYONE());
    
    usage(usageBuilder -> {
      usageBuilder.stringArgument("name", suggestionBuilder -> {
        for (String s : LSManager.nameList()) {
          suggestionBuilder.suggest(s);
        }
      }, null).executes(ctx -> {
        // コンソールからの入力を拒否
        if (ctx.getPlayer() == null) {
          ctx.fail("このコマンドはコンソールからは実行できません");
          return;
        }
        LSManager.teleport(ctx.getSender(), ctx.getArgs().get(0));
      });
    });
  }
}
