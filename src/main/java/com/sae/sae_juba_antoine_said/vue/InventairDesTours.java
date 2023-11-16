package com.sae.sae_juba_antoine_said.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;

public class InventairDesTours {
    private ImageView imageViewtourB1, imageViewtourB2, imageViewtourB3, imageViewtourB4;
    GridPane gridPane;

    public InventairDesTours( ImageView imageViewTour1,ImageView imageViewTour2,ImageView imageViewTour3,ImageView imageViewTour4) {
        this.imageViewtourB1=imageViewTour1;
        this.imageViewtourB2 = imageViewTour2;
        this.imageViewtourB3 = imageViewTour3;
        this.imageViewtourB4 = imageViewTour4;
        mettreImg();

    }


    public void mettreImg() {
        imageViewtourB1.setImage(createImageView(0));
        imageViewtourB2.setImage(createImageView(1));
        imageViewtourB3.setImage(createImageView(2));
        imageViewtourB4.setImage(createImageView(3));


    }


    private Image createImageView(int i) {
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