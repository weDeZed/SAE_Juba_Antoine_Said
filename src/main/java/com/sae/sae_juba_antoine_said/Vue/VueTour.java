package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Guerrier;
import com.sae.sae_juba_antoine_said.Modele.Tour;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.util.ArrayList;

public class VueTour {


    private Pane pane;
    private Tour tours;
    private Image image;
    private ImageView imageView;


    public VueTour(Pane pane,  Tour tour) {
        this.pane = pane;
        this.tours = tours;

        FileInputStream fichierTour = null;

            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tour.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tour instanceof Tour) {
                this.image = new Image(fichierTour);
                int decalage = ((int) image.getHeight());
                this.imageView = new ImageView(image);
                imageView.setLayoutX(tour.getX());
                imageView.setLayoutY(tour.getY());
                this.pane.getChildren().add(imageView);
                imageView.setLayoutX(tour.getX()+decalage);
            }



    }

}

