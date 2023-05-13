package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Animation;
import com.sae.sae_juba_antoine_said.Modele.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Guerrier;
import com.sae.sae_juba_antoine_said.Vue.Vue;
import com.sae.sae_juba_antoine_said.Vue.VueGuerrier;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controlleur implements Initializable {

    private Environnement environnement;
    @FXML
    private TilePane tilePane;
    private Vue vueMap;
    @FXML
    private BorderPane borderPane;
    private Circle leCercle;
    private Acteur guerrier1,guerrier2,guerrier3;
    private ArrayList<Acteur> listeActeurs;
    private VueGuerrier vueGuerrier;

    private Timeline gameLoop;
    private int temps;
    @FXML
    private Pane pane;
    private Animation animation;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeActeurs = new ArrayList<Acteur>();
        guerrier1 = new Guerrier(100,1000,500);
        guerrier2=new Guerrier(100,10,50);
        guerrier3 = new Guerrier(100,101,100);
        this.animation=new Animation();

        this.gameLoop=new Timeline();




        this.environnement = new Environnement(92, 92,listeActeurs);
        try {
            environnement.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 50; i++){
            listeActeurs.add(new Guerrier(1,0,0));
        }








        leCercle = new Circle( 5);
        //leCercle.setId(guerrier1.getId());


        //leCercle.translateXProperty().bind(guerrier1.xProperty());
        //leCercle.translateYProperty().bind(guerrier1.yProperty());


        vueGuerrier = new VueGuerrier(pane,listeActeurs);


        this.tilePane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);

        leCercle.setFill(Color.RED);
        //pane.getChildren().add(leCercle);

        leCercle.layoutXProperty().addListener((obs,old,nouv)->{
            for (Acteur a:listeActeurs){
                a.setX((int)leCercle.getLayoutX());
                a.setY((int)leCercle.getLayoutY());
            }

        });
        gameLaunche();
        animation.initAnimation(gameLoop,leCercle);
    }

    public void gameLaunche() {
        try {
            this.vueMap = new Vue(environnement, tilePane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




}
