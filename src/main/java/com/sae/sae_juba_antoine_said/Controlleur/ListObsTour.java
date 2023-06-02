package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Tour;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class ListObsTour implements ListChangeListener<Tour> {


    private Pane panneauDeJeu;
    VueTour vueTour;

    public ListObsTour(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Tour> t) {

        Image image;
        ImageView imageView;

        while (t.next()) {
            for (Tour tour : t.getAddedSubList()) {
                vueTour=new VueTour(panneauDeJeu,tour);
            }
        }
    }

}