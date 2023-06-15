package com.sae.sae_juba_antoine_said;

import com.sae.sae_juba_antoine_said.Vue.Music;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class Lanceur extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        // Chargement du fichier FXML pour la scène du menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        // Création de la scène avec la taille spécifiée
        Scene scene = new Scene(fxmlLoader.load(), 1400, 800);
        // Configuration de la fenêtre principale
        primaryStage.setResizable(false);
        primaryStage.setTitle("Zombie Survival : La Dernière Lueur d'Espoir");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Music music = new Music();
        music.playMusicFond();
        launch();
    }


}