package edu.cnm.deepdive.ca;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    ClassLoader loader = getClass().getClassLoader();
    ResourceBundle bundle = ResourceBundle.getBundle("resources/strings"); //directory to strings.properties
    FXMLLoader fxmlLoader = new FXMLLoader(loader.getResource("resources/main.fxml"), bundle);
    Parent parent = fxmlLoader.load(); //inflation of fxml, parent is the root
    Controller controller = fxmlLoader.getController();
    controller.setModel(new Model());
    Scene scene = new Scene(parent, 620, 720);
    primaryStage.setTitle(bundle.getString("windowTitle"));
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.show(); //make this appear
  }

  public static void main(String[] args){
    launch(args);
  }
}
