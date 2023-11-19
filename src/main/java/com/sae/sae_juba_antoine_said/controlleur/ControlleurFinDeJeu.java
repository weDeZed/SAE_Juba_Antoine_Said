package com.sae.sae_juba_antoine_said.controlleur;

import com.sae.sae_juba_antoine_said.bdd.Connect;
import com.sae.sae_juba_antoine_said.bdd.SessionUtilisateur;
import com.sae.sae_juba_antoine_said.vue.Music;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurFinDeJeu implements Initializable {
    Music music;
    @FXML
    Button relancerButton;

    @FXML
    Button quitButton;
    @FXML
    AnchorPane anchorPaneIdW;

    @FXML
    VBox feedbackForm;
    @FXML
    TextArea feedbackTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateVisibility();
    }

    private void updateVisibility() {
        boolean estConnecte = SessionUtilisateur.estConnecte();
        feedbackForm.setVisible(estConnecte);
    }

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

    }

    public void quitJeu(ActionEvent event) {
        Platform.exit();
        System.exit(0);
        music.stopMusicVictoire();
        music.stopMusicDefaite();
    }
    public void envoyerFeedbackAction(ActionEvent event) {
        String message = feedbackTextArea.getText();
        Connect.getConnectionInstance().updateFeedback(message);
    }
}
