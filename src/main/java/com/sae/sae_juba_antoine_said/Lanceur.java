package com.sae.sae_juba_antoine_said;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Lanceur extends Application {
    BorderPane borderPane;

    @Override
    public void start(Stage stage) throws IOException {
        borderPane = new BorderPane();
        FXMLLoader fxmlLoader = new FXMLLoader(Lanceur.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 800);
        stage.setTitle("Tower");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}