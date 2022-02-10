package net.kunmc.lab.locationsaver.location;

import java.util.ArrayList;
import java.util.List;
import net.kunmc.lab.locationsaver.file.CsvIndex;
import org.bukkit.entity.Player;

public class LSLocationList {

  private List<LSLocation> list = new ArrayList<>();

  public LSLocationList(List<String[]> csvStringList) {
    for (String[] csvString : csvStringList) {
      if (Boolean.getBoolean(csvString[CsvIndex.IS_DELETED.index])) {
        continue;
      }

      this.list.add(
          new LSLocation(
              csvString[CsvIndex.NAME.index],
              csvString[CsvIndex.LOC_X.index],
              csvString[CsvIndex.LOC_Y.index],
              csvString[CsvIndex.LOC_Z.index],
              csvString[CsvIndex.WORLD_TYPE.index],
              csvString[CsvIndex.SETTER.index],
              csvString[CsvIndex.IS_DELETED.index]
          )
      );
    }
  }

  public List<LSLocation> list() {
    return this.list;
  }

  public List<String> nameList() {
    List<String> nameList = new ArrayList<>();
    for (LSLocation location : this.list) {
      if (!location.isDeleted()) {
        nameList.add(location.name());
      }
    }
    return nameList;
  }

  /**
   * 座標を登録する
   *
   * @return 登録成功/失敗
   */
  public boolean add(Player setter, String name) {

    // 登録名の重複チェック
    if (get(name) != null) {
      return false;
    }

    // 登録
    this.list.add(
        new LSLocation(setter, name)
    );

    return true;
  }

  /**
   * 登録名から座標情報を削除する
   *
   * @return 削除成功/失敗
   */
  public boolean remove(String name) {
    LSLocation location = get(name);
    if (location == null) {
      return false;
    }

    location.delete();
    return true;
  }

  /**
   * 登録名から座標情報を取得する
   */
  public LSLocation get(String name) {
    for (LSLocation location : this.list) {
      if (location.name().equalsIgnoreCase(name)) {
        return location;
      }
    }
    return null;
  }
}
