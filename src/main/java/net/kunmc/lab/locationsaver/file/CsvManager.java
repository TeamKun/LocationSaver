package net.kunmc.lab.locationsaver.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kunmc.lab.locationsaver.LocationSaver;
import net.kunmc.lab.locationsaver.location.LSLocation;
import net.kunmc.lab.locationsaver.location.LSLocationList;

public class CsvManager {

  private static final String FILE_NAME = "Location.csv";

  private File csv;

  public CsvManager() {
    if (!this.existFile()) {
      this.create();
    } else {
      this.load();
    }
  }

  private void load() {
    this.csv = new File(LocationSaver.plugin.getDataFolder(), FILE_NAME);
  }

  private boolean existFile() {
    for (File file : LocationSaver.plugin.getDataFolder().listFiles()) {
      if (file.getName().equals(FILE_NAME)) {
        return true;
      }
    }
    return false;
  }

  private void create() {
    PrintWriter printWriter = null;
    try {
      //CSVデータファイル
      this.csv = new File(LocationSaver.plugin.getDataFolder(), FILE_NAME);

      printWriter = new PrintWriter(csv);
      // ヘッダー
      printWriter.print(CsvIndex.NAME.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.LOC_X.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.LOC_Y.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.LOC_Z.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.WORLD_TYPE.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.SETTER.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.IS_DELETED.name());

      printWriter.println();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      printWriter.close();
    }
  }

  public List<String[]> getStringArray() {
    BufferedReader br = null;
    List<String[]> list = new ArrayList<>();

    try {
      br = new BufferedReader(new FileReader(this.csv));

      String line;
      String[] data;

      while ((line = br.readLine()) != null) {
        // lineをカンマで分割し、配列dataに設定
        data = line.split(",");
        list.add(data);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      list.remove(0);
      return list;
    }
  }

  public void save(LSLocationList locationList) {
    PrintWriter printWriter = null;
    try {
      //CSVデータファイル
      this.csv = new File(LocationSaver.plugin.getDataFolder(), FILE_NAME);

      printWriter = new PrintWriter(csv);
      // ヘッダー
      printWriter.print(CsvIndex.NAME.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.LOC_X.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.LOC_Y.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.LOC_Z.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.WORLD_TYPE.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.SETTER.name());
      printWriter.print(",");
      printWriter.print(CsvIndex.IS_DELETED.name());

      printWriter.println();

      for (LSLocation location : locationList.list()) {
        printWriter.print(location.name());
        printWriter.print(",");
        printWriter.print(location.locX());
        printWriter.print(",");
        printWriter.print(location.locY());
        printWriter.print(",");
        printWriter.print(location.locZ());
        printWriter.print(",");
        printWriter.print(location.worldType().name().toLowerCase(Locale.ROOT));
        printWriter.print(",");
        printWriter.print(location.setterName());
        printWriter.print(",");
        printWriter.print(location.isDeleted());
        printWriter.println();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      printWriter.close();
    }
  }
}
