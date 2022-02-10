package net.kunmc.lab.locationsaver.location;

public enum LSWorldType {
  OVERWORLD("world"),
  THE_NETHER("world_nether"),
  THE_END("world_the_end");

  
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
    return LSWorldType.OVERWORLD;
  }
}
