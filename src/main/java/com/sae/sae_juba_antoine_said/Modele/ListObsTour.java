package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class ListObsTour implements ListChangeListener<Tour> {


    private Pane panneauDeJeu;

    public ListObsTour(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Tour> t) {

        Image image;
        ImageView imageView;


        while (t.next()) {


            FileInputStream fichierGuerrier = null;
            for (Tour tour : t.getAddedSubList()) {


                try {
                    String chemin = "src/main/java/com/sae/sae_juba_antoine_said/Ressources/tour.png";
                    fichierGuerrier = new FileInputStream(chemin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("CHANGEMENT");

                if (tour instanceof Tour) {
                    image = new Image(fichierGuerrier);
                    imageView = new ImageView(image);
                    imageView.xProperty().bind(tour.xProperty());
                    imageView.yProperty().bind(tour.yProperty());

                    this.panneauDeJeu.getChildren().add(imageView);
                }


            }
        }
    }
}