package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
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


    public VueTour(Pane pane, Tour tour) {
        this.pane = pane;
        this.tours = tours;

        FileInputStream fichierTour = null;

        if (tour instanceof TroopTour) {
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour0.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (tour instanceof TondreTour){
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour1.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tour instanceof LaserTour){
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour2.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tour instanceof ArcTour){
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour3.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.image = new Image(fichierTour);
        int decalageH = ((int) image.getHeight());
        int decalageL = (int) image.getWidth();
        this.imageView = new ImageView(image);
        imageView.setLayoutX(tour.getX() - decalageL);
        imageView.setLayoutY(tour.getY() - decalageH);
        this.pane.getChildren().add(imageView);


    }

}

