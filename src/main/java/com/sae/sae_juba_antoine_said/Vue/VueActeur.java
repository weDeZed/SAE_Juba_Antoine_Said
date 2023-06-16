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

public class VueActeur {
    private Pane pane;
    private Acteur acteur;
    private Image image;
    private ImageView imageView;
    FileInputStream fichierActeur;
    private ProgressBar progressBarActeur;

    public VueActeur(Pane pane, Acteur acteur) {
        this.pane = pane;
        this.acteur = acteur;
        this.progressBarActeur = new ProgressBar();

        fichierActeur = null;

        if (acteur instanceof Guerrier) {
            try {
                fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/ally/g3.png");
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
        if (acteur instanceof Dragon) {
            try {
                fichierActeur = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/EnnemiesImg/gb0.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        acteur.directionActeurProperty().addListener((obs, oldDirection, newDirection) -> { // Dès qu'il y a un changement dans la direction, il est appliqué ici.
            startImageAnimation((int) newDirection);
        });


        this.image = new Image(fichierActeur);
        this.imageView = new ImageView(image);

        //Bar de vie
        progressBarActeur.translateYProperty().bind(acteur.yProperty());
        progressBarActeur.translateXProperty().bind(acteur.xProperty());
        progressBarActeur.setPrefWidth(30);
        progressBarActeur.setPrefHeight(10);

        if (acteur.getPv() > 20) {
            progressBarActeur.progressProperty().bind(acteur.getPvProperty().divide(80.0));
            String barColor = "-fx-accent: red;";
            String trackColor = "-fx-control-inner-background: white;";
            progressBarActeur.setStyle(barColor + trackColor);
        } else {
            progressBarActeur.setStyle("-fx-accent: red;");
        }

        imageView.xProperty().bind(acteur.xProperty().subtract(3));
        imageView.yProperty().bind(acteur.yProperty().add(7));
        progressBarActeur.setId(acteur.getId() + 1);
        imageView.setId(acteur.getId());


        this.pane.getChildren().add(progressBarActeur);
        this.pane.getChildren().add(imageView);


    }

    // La méthode startImageAnimation lance une animation de l'image de l'acteur en fonction de la direction donnée.
    public void startImageAnimation(int direction) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.17), e -> {
            int i = (int) (Math.random() * 2);
            String acteurType = "";
            String directionType = "";
            if (acteur instanceof Bandit) {
                acteurType = "EnnemiesImg/bandit";
            } else if (acteur instanceof Guerrier) {
                acteurType = "ally/g";
            } else {
                acteurType = "EnnemiesImg/g";
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
            if (direction == 5) {  // pour l'animaiton de bandit et guerrier pour fair le combat
                if (acteur instanceof Bandit) {
                    //System.out.println("dans vue acteur b dir5 " + i);
                    String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/EnnemiesImg/banditb0.png";
                    try {
                        fichierActeur = new FileInputStream(path);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (acteur instanceof Guerrier) {
                    //System.out.println("dans vue acteur g dir5 " + i);
                    String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/ally/g" + i + ".png";
                    try {
                        fichierActeur = new FileInputStream(path);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            } else {
                if (acteur instanceof Bandit) {
                    //System.out.println("b");
                    String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/" + acteurType + directionType + i + ".png";
                    try {
                        fichierActeur = new FileInputStream(path);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (acteur instanceof Guerrier) {
                    //System.out.println("g");
                    String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/" + acteurType + "0.png";
                    try {
                        fichierActeur = new FileInputStream(path);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
            if (acteur instanceof Dragon) {
                String path = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/" + acteurType + directionType + i + ".png";
                try {
                    fichierActeur = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

            this.image = new Image(fichierActeur);
            this.imageView.setImage(image);
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


}






































