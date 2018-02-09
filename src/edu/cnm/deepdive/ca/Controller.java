package edu.cnm.deepdive.ca;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

public class Controller {

  private static final int UPDATE_INTERVAL = 1;

  private Model model;
  private boolean running = false;
  private Runner runner = null;
  private Boolean updatePending = false;

  @FXML
  private View terrainView;

  @FXML  //auto wiring from main to controller
  private Slider densitySlider;

  @FXML
  private Button resetButton;

  @FXML
  private Button runOnceButton;

  @FXML
  private ToggleButton runForeverButton;

  @FXML
  private void reset() {
    model.populate(densitySlider.getValue() / 100); //scale it by 100
    updateView(model.getTerrain()); //get from model for view to display
  }

  @FXML
  private void runOnce() {
    model.advance();
    updateView(model.getTerrain());
  }

  @FXML
  private void runForever() {
    if (runForeverButton.isSelected()) {
      resetButton.setDisable(true);
      runOnceButton.setDisable(true);
      running = true;
      runner = new Runner();
      runner.start();
    } else {
      running = false;
      runner = null;
      resetButton.setDisable(false);
      runOnceButton.setDisable(false);
    }
  }

  public Model getModel() {
    return model;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  private void updateView(boolean[][] terrain) {
    terrainView.draw(terrain);
    updatePending = false;
  }

  private class Runner extends Thread {

    private void refresh() {
      boolean[][] terrain = model.getTerrain();
      updatePending = true;
      Platform.runLater(new Runnable() { //does the update
        @Override
        public void run() {
          updateView(terrain);
        }
      }); //end platform
    }

    @Override
    public void run() {
      while (running) {
        synchronized (model) { //talking stick
          model.advance();
          if (!updatePending && model.getGeneration() % UPDATE_INTERVAL == 0) {
            refresh();
          }
        }
      }
      refresh();
    }
  }
}


