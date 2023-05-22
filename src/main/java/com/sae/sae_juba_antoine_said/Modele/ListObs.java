package com.sae.sae_juba_antoine_said.Modele;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class ListObs implements ListChangeListener<Acteur> {


    private Pane panneauDeJeu;

    public ListObs(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Acteur> a) {
        FileInputStream fichierTour = null;
        Image image;
        ImageView imageView;

        try {
            fichierTour = new FileInputStream("C:\\Users\\jubac\\OneDrive\\Documents\\BUT\\S2\\Java\\SAE_Juba_Antoine_Saidd\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\saidkamal.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (a.next()) {

            for (Acteur act : a.getAddedSubList()) {
                if (act instanceof Acteur) {
                    image = new Image(fichierTour);
                    int decalage = ((int) image.getHeight());
                    imageView = new ImageView(image);
                    panneauDeJeu.getChildren().add(imageView);
                    imageView.setLayoutX(act.getX() + decalage);

                }
            }

        }
    }
    }


