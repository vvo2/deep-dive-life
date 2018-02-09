package edu.cnm.deepdive.ca;

public class Model {

  private static final int WIDTH = 400;
  private static final int HEIGHT = 400;

  private int generation;
  private boolean[][] terrain;
  private byte[][] neighbors;

  public Model(){
    terrain = new boolean[HEIGHT][WIDTH];
    neighbors = new byte[HEIGHT][WIDTH];
  }

  public void populate(double density){
    generation = 0;
    for (int i = 0; i < HEIGHT; i++){
      for (int j = 0; j < WIDTH; j++){
        terrain[i][j] = (Math.random() < density); //easier way to random
//        if (Math.random() < density){
//          terrain[i][j] = true;
//        } else {
//          terrain[i][j] = false;
//        }
      }
    }
  }

  public void advance(){
    generation++;
    for (int i = 0; i < HEIGHT; i++){
      for (int j = 0; j < WIDTH; j++){
        int count = 0;
        for (int row = i -1; row <= i + 1; row++){
          for (int col = j - 1; col <= j + 1; col++){
            if (terrain[(row + HEIGHT) % HEIGHT][(col + WIDTH) % WIDTH]){
              count++;
            }
          } //col
        } //row
        if (terrain[i][j]){
          count--;
        }
        neighbors[i][j] = (byte) count;
      }
    } //end count of neighbors
    for (int i =0; i < HEIGHT; i++){
      for (int j = 0; j < WIDTH; j++){
        if (terrain[i][j] && (neighbors[i][j] < 2 || neighbors[i][j] > 3)){
          terrain[i][j] = false;
        } else if (!terrain[i][j] && neighbors[i][j] == 3){
          terrain[i][j] = true;
        }
      }
    } //end terrain
  }

  public boolean[][] getTerrain() {
    boolean[][] safeCopy = new boolean[terrain.length][terrain[0].length];
    for (int i = 0; i < terrain.length; i++) {
      System.arraycopy(terrain[i], 0, safeCopy[i], 0, terrain[i].length);
    }
    return safeCopy;
  }

  public int getGeneration() {
    return generation;
  }

}
