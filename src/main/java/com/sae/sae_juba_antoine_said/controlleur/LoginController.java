package com.sae.sae_juba_antoine_said.controlleur;

import com.dlsc.formsfx.model.structure.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField identifiant;

    @FXML
    TextField motDePasse;
    @FXML
    Button loginButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void inserer(ActionEvent actionEvent){
        System.out.println("dans controlleur loginController pour ajouter les user");
        System.out.println("identifiant "+identifiant.getText());
        System.out.println("mot de pass "+motDePasse.getText());
    }
}
