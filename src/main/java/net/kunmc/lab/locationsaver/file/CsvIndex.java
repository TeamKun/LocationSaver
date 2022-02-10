package net.kunmc.lab.locationsaver.file;

public enum CsvIndex {
  NAME(0),
  LOC_X(1),
  LOC_Y(2),
  LOC_Z(3),
  WORLD_TYPE(4),
  SETTER(5),
  IS_DELETED(6);

  public int index;

  CsvIndex(int index) {
    this.index = index;
  }
}
