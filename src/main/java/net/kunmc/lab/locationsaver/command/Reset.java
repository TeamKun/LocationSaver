package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.locationsaver.logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class Reset extends Command {

  public Reset(@NotNull String name) {
    super(name);
    usage(usageBuilder -> {
      usageBuilder.stringArgument("name", suggestionBuilder -> {
        for (String s : LSManager.nameList()) {
          suggestionBuilder.suggest(s);
        }
      }, ctx -> {
        // コンソールからの入力を拒否
        if (ctx.getPlayer() == null) {
          ctx.fail("このコマンドはコンソールからは実行できません");
          return;
        }

        String locationName = ctx.getArgs().get(0);
        if (LSManager.reset(locationName, ctx.getPlayer())) {
          ctx.success(locationName.concat("の座標を現在位置に設定しました。"));
          return;
        }

        ctx.fail(locationName.concat("は存在しません。"));
      });
    });
  }
}
