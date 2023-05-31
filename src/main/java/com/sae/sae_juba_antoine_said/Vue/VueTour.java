package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.ArrayList;

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
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour0.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (tour instanceof TourFoudre) {
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour1.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tour instanceof LaserTour) {
            try {
                fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour2.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tour instanceof ArcTour) {
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
        imageView.setLayoutX(tour.getX());
        imageView.setLayoutY(tour.getY() - decalageH);
        this.pane.getChildren().add(imageView);


        Circle rangeCircle = new Circle(tour.getX(), tour.getY(), tour.getRange() * 16);
        rangeCircle.setFill(Color.TRANSPARENT); // transparent à l'intérieur
        rangeCircle.setStroke(Color.RED); // bordure rouge
        rangeCircle.setStrokeWidth(2);
        pane.getChildren().add(rangeCircle);
/*

        fléche = new Line(0, 0, 0, -tours.getRange());
        fléche.setStroke(Color.BLACK);
        fléche.setStrokeWidth(2);
        fléche.setEndY(-2 * tours.getRange() * 8);




        //Positionner la flèche au centre de la tour
        fléche.setLayoutX(tours.getX());
        fléche.setLayoutY(tours.getY());
        pane.getChildren().add(fléche);
        initAnimation();

 */
    }

    public Line getFléche() {
        return fléche;
    }

    public void initAnimation() {
        Rotate rotation = new Rotate();
        fléche.getTransforms().add(rotation);

// Créer une timeline pour animer la rotation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(rotation.angleProperty(), 360))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }


}


