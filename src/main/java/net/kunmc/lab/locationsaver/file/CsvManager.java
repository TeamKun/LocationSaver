package net.kunmc.lab.locationsaver.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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
    for (File file : Objects.requireNonNull(LocationSaver.plugin.getDataFolder().listFiles())) {
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
      writeCSVHeader(printWriter);
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
      writeCSVHeader(printWriter);

      for (LSLocation location : locationList.list()) {
        writeCSVRow(
            printWriter,
            location.name(),
            location.locX(),
            location.locY(),
            location.locZ(),
            location.worldType().name().toLowerCase(Locale.ROOT),
            location.setterName(),
            String.valueOf(location.isDeleted())
        );
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      printWriter.close();
    }
  }

  private PrintWriter writeCSVHeader(PrintWriter printWriter) {
    return writeCSVRow(
        printWriter,
        CsvIndex.NAME.name(),
        CsvIndex.LOC_X.name(),
        CsvIndex.LOC_Y.name(),
        CsvIndex.LOC_Z.name(),
        CsvIndex.WORLD_TYPE.name(),
        CsvIndex.SETTER.name(),
        CsvIndex.IS_DELETED.name()
    );
  }

  private PrintWriter writeCSVRow(
      PrintWriter printWriter,
      String name,
      String locX,
      String locY,
      String locZ,
      String worldType,
      String setter,
      String isDeleted) {
    printWriter.print(name);
    printWriter.print(",");
    printWriter.print(locX);
    printWriter.print(",");
    printWriter.print(locY);
    printWriter.print(",");
    printWriter.print(locZ);
    printWriter.print(",");
    printWriter.print(worldType);
    printWriter.print(",");
    printWriter.print(setter);
    printWriter.print(",");
    printWriter.print(isDeleted);

    printWriter.println();

    return printWriter;
  }
}
