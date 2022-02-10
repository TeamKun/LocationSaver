package net.kunmc.lab.locationsaver.location;

public enum LSWorldType {
  OVER_WORLD("overworld"),
  NETHER("the_nether"),
  THE_END("the_end");

  public String typeName;

  LSWorldType(String typeName) {
    this.typeName = typeName;
  }

  public static LSWorldType getByWorldName(String name) {
    for (LSWorldType value : LSWorldType.values()) {
      if (value.typeName.equalsIgnoreCase(name)) {
        return value;
      }
    }
    return LSWorldType.OVER_WORLD;
  }
}
