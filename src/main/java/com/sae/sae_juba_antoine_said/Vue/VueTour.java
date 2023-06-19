package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Tours.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.FileInputStream;

public class VueTour {

    private Pane pane;
    private Tour tours;
    private Image image;
    private ImageView imageView;
    Line fléche;


    public VueTour(Pane pane, Tour tour) {
        this.pane = pane;
        this.tours = tours;

        FileInputStream fichierTour = null;

        if (tour instanceof TroopTour) {
           this.image=envoiImage(0);

        }
        if (tour instanceof FoudreTour) {
            this.image=envoiImage(1);
        }
        if (tour instanceof FlecheTour) {
            this.image=envoiImage(2);
        }
        if (tour instanceof LaserTour) {
            this.image=envoiImage(3);
        }
        int decalageH = ((int) image.getHeight())/2;
        int decalageL = (int) image.getWidth()/2;
        this.imageView = new ImageView(image);

        imageView.setLayoutX(tour.getX()-decalageL);
        imageView.setLayoutY(tour.getY()-decalageH);
        this.pane.getChildren().add(imageView);

    }
    public Image envoiImage(int i) {
        Image image;

        FileInputStream fichierTour = null;
        try {
            fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour" + i + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        image = new Image(fichierTour);

        return image;
    }

}




