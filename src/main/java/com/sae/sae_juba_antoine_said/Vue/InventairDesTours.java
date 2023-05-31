package com.sae.sae_juba_antoine_said.Vue;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;

public class InventairDesTours {
    private ToggleButton tourB1, tourB2, tourB3, tourB4;
    GridPane gridPane;

    public InventairDesTours(ToggleButton tourB1, ToggleButton tourB2, ToggleButton tourB3, ToggleButton tourB4) {
        this.tourB1 = tourB1;
        this.tourB2 = tourB2;
        this.tourB3 = tourB3;
        this.tourB4 = tourB4;
        mettreImg();

    }


    public void mettreImg() {
        tourB1.setGraphic(createImageView(0));
        tourB2.setGraphic(createImageView(1));
        tourB3.setGraphic(createImageView(2));
        tourB4.setGraphic(createImageView(3));

    }


    private ImageView createImageView(int i) {
        Image image;
        ImageView imageView;
        FileInputStream fichierTour = null;
        try {
            fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour" + i + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        image = new Image(fichierTour);
        imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }


}
