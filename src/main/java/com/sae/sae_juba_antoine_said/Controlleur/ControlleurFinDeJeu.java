package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Vue.Music;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControlleurFinDeJeu {
    Music music;
    @FXML
    Button relancerButton;

    @FXML
    Button quitButton;
    @FXML
    AnchorPane anchorPaneIdW;

    @FXML
    public void relancerJeu(ActionEvent event) throws IOException {
        music = new Music();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/sae/sae_juba_antoine_said/vueSae.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1440, 800);
        primaryStage.setResizable(false);
        primaryStage.setTitle(" Tower Defense ");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/sae/sae_juba_antoine_said/vueSae.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1440, 800);
        primaryStage.setResizable(false);
        primaryStage.setTitle(" Tower Defense ");
        primaryStage.setScene(scene);
        primaryStage.show();
        music.stopMusicVictoire();
        music.stopMusicDefaite();

         */
    }

    public void quitJeu(ActionEvent event) {
        Platform.exit();
        System.exit(0);
        music.stopMusicVictoire();
        music.stopMusicDefaite();
    }
}
