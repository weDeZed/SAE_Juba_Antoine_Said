package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class VueActeur {
    private Pane pane;
    private Acteur acteur;
    private Image image;
    private ImageView imageView;
    FileInputStream fichierActeur;
    private ProgressBar progressBar;

    public VueActeur(Pane pane, Acteur acteur) {
        this.pane = pane;
        this.acteur = acteur;
        this.progressBar = new ProgressBar();

        fichierActeur = null;

        if (acteur instanceof Guerrier) {
            try {
                fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/ally/g0.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (acteur instanceof Bandit) {
            try {
                fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/EnnemiesImg/banditf0.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (acteur instanceof Archer) {
            try {
                fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/ally/g1.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        acteur.directionActeurProperty().addListener((obs, oldDirection, newDirection) -> {
            startImageAnimation((int) newDirection);

        });


        this.image = new Image(fichierActeur);
        this.imageView = new ImageView(image);

        //Bar de vie
        progressBar.translateYProperty().bind(acteur.yProperty());
        progressBar.translateXProperty().bind(acteur.xProperty());
        progressBar.setPrefWidth(30);
        progressBar.setPrefHeight(10);

        if (acteur.getPv() > 20) {
            progressBar.progressProperty().bind(acteur.getPvProperty().divide(80.0));
            String barColor = "-fx-accent: red;";
            String trackColor = "-fx-control-inner-background: white;";
            progressBar.setStyle(barColor + trackColor);
        } else {
            progressBar.setStyle("-fx-accent: red;");
        }

        imageView.xProperty().bind(acteur.xProperty().subtract(3));
        imageView.yProperty().bind(acteur.yProperty().add(7));
        progressBar.setId(acteur.getId() + 1);
        imageView.setId(acteur.getId());


        this.pane.getChildren().add(progressBar);
        this.pane.getChildren().add(imageView);


    }




    public void startImageAnimation(int direction) {
        AtomicInteger temp = new AtomicInteger();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            int i = temp.get() % 2;
            String acteurType = "";
            String directionType = "";
            if (acteur instanceof Bandit) {
                acteurType = "EnnemiesImg/bandit";
            } else if (acteur instanceof Guerrier) {
                acteurType = "ally/g";
            }

            switch (direction) {
                case 1:
                    directionType = "d";
                    break;
                case 2:
                    directionType = "g";
                    break;
                case 3:
                    directionType = "b";
                    break;
                case 4:
                    directionType = "f";
                    break;
            }
            if (acteur instanceof Bandit) {
               // System.out.println("b");
                String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/" + acteurType + directionType + i + ".png";
                try {
                    fichierActeur = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (acteur instanceof Guerrier){
                //System.out.println("g");
                String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/" + acteurType  + i + ".png";
                try {
                    fichierActeur = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
            this.image = new Image(fichierActeur);
            this.imageView.setImage(image);
            temp.getAndIncrement();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


}






































/* if(acteur instanceof Bandit){
            imageView.setViewport(new Rectangle2D(0, 0, 48, 48));

                final Sprite animation = new Sprite(
                        imageView,
                        Duration.millis(500),
                        2, 2,
                        0, 87,
                        58, 52
                );
                animation.setCycleCount(Animation.INDEFINITE);
                animation.play();

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
*/
/*
    private void changeImage(int i) throws FileNotFoundException {
        if (acteur instanceof Bandit) {
            //fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/EnnemiesImg/bandit2.png");

        }
        if (acteur instanceof Guerrier) {
            fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/ally/g1.png");
        }
        //mettre à jour l'image de l'ImageView
        //this.image = new Image(fichierActeur);
        //this.imageView.setImage(image);
        //startImageAnimation(i);
    }

 */