package com.sae.sae_juba_antoine_said;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Lanceur extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Lanceur.class.getResource("vueSae.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        System.out.println();
        stage.setTitle("Tower Defense");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}