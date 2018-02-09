package edu.cnm.deepdive.ca;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class View extends Canvas {

  public void draw(boolean[][] terrian) { //draw terrain in boolean[][]
    GraphicsContext context = getGraphicsContext2D();
    double cellSize = Math.min(
        (double) getWidth() / terrian[0].length,
        (double) getHeight() / terrian.length
    );
    context.clearRect(0,0, getWidth(), getHeight()); //clear background
    context.setFill(Color.RED);
    for (int i = 0; i < terrian.length; i++) { //row
       for (int j = 0; j < terrian[i].length; j++){
         if (terrian[i][j]) {
           context.fillOval(j * cellSize, i * cellSize, cellSize, cellSize);
         }
       }
    }
  }

}
