package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.locationsaver.Logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class Remove extends Command {

  public Remove(@NotNull String name) {
    super(name);
    usage(usageBuilder -> {
      usageBuilder.stringArgument("name", suggestionBuilder -> {
        for (String s : LSManager.nameList()) {
          suggestionBuilder.suggest(s);
        }
      }, null).executes(ctx -> {
        String locationName = ctx.getArgs().get(0);
        if (LSManager.removeLocation(locationName)) {
          ctx.success(locationName.concat("を削除しました。"));
          return;
        }

        ctx.fail(locationName.concat("は登録されていません。"));
      });
    });
  }
}
