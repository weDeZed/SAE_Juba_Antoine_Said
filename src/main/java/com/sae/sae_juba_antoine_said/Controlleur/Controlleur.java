package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Environnement;
import com.sae.sae_juba_antoine_said.Vue.Vue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controlleur implements Initializable {

    private Environnement environnement;
    @FXML
    private TilePane tileP;
    private Vue vueMap;
    @FXML
    private Pane pane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.environnement = new Environnement(92, 92);
        try {
            environnement.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.tileP.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tileP.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tileP.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);

        this.pane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);

        gameLaunche();
    }

    public void gameLaunche() {
        try {
            this.vueMap = new Vue (environnement, tileP);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
