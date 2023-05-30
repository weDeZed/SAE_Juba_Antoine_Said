package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileInputStream;

public class VueActeur {
    private Pane pane;
    private Acteur acteur;
    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;
    Circle circle;

    public VueActeur(Pane pane, Acteur acteur) {
        this.pane = pane;
        this.acteur = acteur;

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
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/bandit.png");
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
        //final ImageView imageView = new ImageView(image);
        this.imageView = new ImageView(image);
        imageView.xProperty().bind(acteur.xProperty());
        imageView.yProperty().bind(acteur.yProperty());
        this.pane.getChildren().add(imageView);


        /*if(acteur instanceof Bandit){
            imageView.setViewport(new Rectangle2D(0, 0, 48, 48));
            final Sprite animation = new Sprite(
                    imageView,
                    Duration.millis(500),
                    2, 2,
                    16, 136,
                    48, 64
            );
            animation.setCycleCount(Animation.INDEFINITE);
            animation.play();
        }
       */

    }


}
