package com.sae.sae_juba_antoine_said.controlleur;

import com.sae.sae_juba_antoine_said.modele.bdd.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {
    @FXML
    TextField identifiant;

    @FXML
    TextField motDePasse;
    @FXML
    Button loginButton;

    @FXML
    Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void inserer(ActionEvent actionEvent) throws IOException {
        Connect connect = Connect.getConnectionInstance();
        if(!connect.verifUtilisateur(identifiant.getText())) {
            connect.creerUtilisateur(identifiant.getText(),motDePasse.getText());
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Inscription valide ! Veuillez vous connecter pour jouer.");
        }else {
            messageLabel.setText("Inscription échoué. L'utilisateur existe déjà, veuillez réesayer.");
        }
    }

    private void redirectionConnecter(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/sae/sae_juba_antoine_said/menuConnecter.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1440, 800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void retourButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sae/sae_juba_antoine_said/menu.fxml"));
        Parent menuParent = loader.load();
        Scene menuScene = new Scene(menuParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(menuScene);
        stage.show();
    }

}