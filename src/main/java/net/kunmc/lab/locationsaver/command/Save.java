package net.kunmc.lab.locationsaver.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.arguments.StringArgument.Type;
import net.kunmc.lab.locationsaver.logic.LSManager;
import org.jetbrains.annotations.NotNull;

public class Save extends Command {

  public Save(@NotNull String name) {
    super(name);

    usage(usageBuilder -> {
      usageBuilder.stringArgument("name", Type.WORD).executes(ctx -> {
        // コンソールからの入力を拒否
        if (ctx.getPlayer() == null) {
          ctx.fail("このコマンドはコンソールからは実行できません");
          return;
        }

        String locationName = ctx.getArgs().get(0);
        if (LSManager.addLocation(ctx.getPlayer(), locationName)) {
          ctx.success(locationName.concat("を登録しました。"));
          return;
        }

        ctx.fail(locationName.concat("はすでに登録されています。別の名前で登録してください"));
      });
    });
  }
}
