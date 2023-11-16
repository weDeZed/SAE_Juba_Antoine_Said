package com.sae.sae_juba_antoine_said.vue;

import com.sae.sae_juba_antoine_said.modele.tours.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

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

        if (tour instanceof TourASoldat) {
           this.image=envoiImage(0);

        }
        if (tour instanceof TourAFoudre) {
            this.image=envoiImage(1);
        }
        if (tour instanceof TourAFleche) {
            this.image=envoiImage(2);
        }
        if (tour instanceof TourALaser) {
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




