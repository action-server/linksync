package com.linksync.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LinkSyncUiApp extends Application {
  @Override
  public void start(Stage primaryStage) throws IOException {
    Pane pane = loadRootFromFxml();
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.show();
//    Controller controller = new Controller();
//    fxmlLoader.setController(controller);
//    scene.getStylesheets().add("/resources/main.css");

  }

  private Pane loadRootFromFxml() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    URL myResource = ClassLoader.getSystemResource("frontend/fxml/main.fxml");
    fxmlLoader.setLocation(myResource);
    return fxmlLoader.load();
  }
}