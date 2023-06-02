package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.util.ArrayList;

public class VueActeur {
    private Pane pane;
    private Acteur acteur;
    private Image image;
    private ImageView imageView;

    private ProgressBar progressBar;
    public VueActeur(Pane pane, Acteur acteur) {
        this.pane = pane;
        this.acteur = acteur;
        this.progressBar = new ProgressBar();

        FileInputStream fichierGuerrier = null;

        if (acteur instanceof Guerrier) {
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/saidkamal.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (acteur instanceof Bandit) {
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/enemy/e4/dir.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (acteur instanceof Archer) {
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/saidkamal.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Archer ");

        }

        this.image = new Image(fichierGuerrier);
        this.imageView = new ImageView(image);

        //Bar de vie
        progressBar.translateYProperty().bind(acteur.yProperty());
        progressBar.translateXProperty().bind(acteur.xProperty());
        progressBar.setPrefWidth(30);
        progressBar.setPrefHeight(10);
        if (acteur.getPv() > 20 ) {
            progressBar.progressProperty().bind(acteur.getPvProperty().divide(80.0));
            String barColor = "-fx-accent: red;";
            String trackColor = "-fx-control-inner-background: white;";
            progressBar.setStyle(barColor + trackColor);
        }else {
            progressBar.setStyle("-fx-accent: red;");
        }


        imageView.xProperty().bind(acteur.xProperty().subtract(3));
        imageView.yProperty().bind(acteur.yProperty().add(7));
        progressBar.setId(acteur.getId());
        this.pane.getChildren().add(progressBar);
        this.pane.getChildren().add(imageView);

        if(acteur instanceof Bandit){
            imageView.setViewport(new Rectangle2D(0, 0, 48, 48));

           /*     final Sprite animation = new Sprite(
                        imageView,
                        Duration.millis(500),
                        2, 2,
                        0, 87,
                        58, 52
                );
                animation.setCycleCount(Animation.INDEFINITE);
                animation.play();
                */
                final Sprite animation = new Sprite(
                        imageView,
                        Duration.millis(500),
                        2, 2,
                        16, 136,
                        58, 52
                );
                animation.setCycleCount(Animation.INDEFINITE);
                animation.play();
            }

        }
}
