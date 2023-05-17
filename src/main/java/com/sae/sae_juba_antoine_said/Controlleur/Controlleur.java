package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Guerrier;
import com.sae.sae_juba_antoine_said.Modele.Tour;
import com.sae.sae_juba_antoine_said.Vue.Vue;
import com.sae.sae_juba_antoine_said.Vue.VueGuerrier;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
    private Acteur guerrier1, guerrier2, guerrier3;
    private ArrayList<Acteur> listeActeurs;
    private ArrayList<Tour> listeTours;

    private VueTour vueTour;
    private VueGuerrier vueGuerrier;

    private Timeline gameLoop;
    private int temps;
    @FXML
    private Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.environnement = new Environnement(90, 90);

        leCercle = new Circle(5, Color.RED);


        try {
            environnement.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {
            int y = (int) (Math.random() *64);
            environnement.ajouterActeur(new Guerrier(1,  28*16, 45*16));
        }

        environnement.ajouterTour(new Tour(535,435,2,10));
        vueGuerrier = new VueGuerrier(pane, environnement.getActeurs());
        vueTour = new VueTour(pane,environnement.getTours());


        pane.getChildren().add(leCercle);
        this.tilePane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);


        leCercle.layoutXProperty().addListener((obs, old, nouv) -> {
            //environnement.suivereLeChemin();

            for (Acteur a : environnement.getActeurs()) {
                if(!a.collisionDroitV(a,environnement)){
                    System.out.println("droit");
                    a.setX(a.getX());
                    a.setY(a.getY());
                    System.out.println("DrX "+a.getX()+" y "+a.getY());
                }
                if ((!a.collisionDevant(a,environnement))){
                    System.out.println("devant");
                    a.setX(a.getX());
                    a.setY(a.getY()-16);
                    System.out.println("DvX "+a.getX()+" y "+a.getY());
                }
                if(!a.collisionGauche(a,environnement)){
                    System.out.println("gauche");
                    a.setX(a.getX()-16);
                    a.setY(a.getY());
                    System.out.println("GX "+a.getX()+" y "+a.getY());
                }
                a.collisionDroitH(a,environnement);
                a.collisionGaucheH(a,environnement);

            }
        });

        coordoneeGetCoordSouris();
        gameLaunche();
        initAnimation();

        gameLoop.play();


    }

    public void coordoneeGetCoordSouris(){
        pane.setOnMousePressed( mouseEvent -> {
            System.out.println("X de la souris :" + mouseEvent.getX());
            System.out.println( "Y de la souris : " + mouseEvent.getY());

        });
    }

    public void gameLaunche() {
        try {
            this.vueMap = new Vue(environnement, tilePane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.7),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {
                    if (temps == 100000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else if (temps % 20 == 0) {
                        leCercle.setLayoutX(leCercle.getLayoutX() + 5);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}
