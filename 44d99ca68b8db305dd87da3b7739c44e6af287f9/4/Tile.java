package com.github.tm.glink.core.tile;

import scala.concurrent.java8.FuturesConvertersImpl;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yu Liebing
 */
public class Tile implements Serializable {
  private int level;
  private int x;
  private int y;

  public Tile(int level, int x, int y) {
    this.level = level;
    this.x = x;
    this.y = y;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tile tile = (Tile) o;
    return level == tile.level &&
            x == tile.x &&
            y == tile.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, x, y);
  }

  @Override
  public String toString() {
    return String.format("Tile{level=%d, x=%d, y=%d}", level, x, y);
  }

  /**
   * Get id of the tile.
   * @return
   */
  public long toLong() {
    long res = 0L;
    res |= ((long) level) << 38;
    for(int i = 0; i < 38; i++) {
      // even for x, odd for y
      int ind = i/2;
      if (i%2 ==0) {
        res |= ((x >> ind & 1) << i);
      } else {
        res |= ((y >> ind & 1) << i);
      }
    }
    return res;
  }

  public Tile fromLong(long tileId) {
    int level  = (int) (tileId >> 38);
    long x = 0L;
    long y = 0L;
    for (int i = 0; i<38;i++){
      // even for x, odd for y
      int ind = i/2;
      if ( i % 2 == 0 ) {
        x |= (tileId>>i & 1) << ind;
      } else {
        y |= (tileId>>i & 1) << ind;
      }
    }
    return new Tile(level, (int)x, (int)y);
  }

}
