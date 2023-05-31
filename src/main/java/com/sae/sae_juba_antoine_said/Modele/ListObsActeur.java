package com.sae.sae_juba_antoine_said.Modele;


import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class ListObsActeur implements ListChangeListener<Acteur> {


    private Pane panneauDeJeu;

    public ListObsActeur(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Acteur> a) {
        while (a.next()) {
            for (Acteur act : a.getAddedSubList()) {
                //System.out.println("changement dans list acteur");
                VueActeur VueActeur = new VueActeur(panneauDeJeu, act);
            }
        }
    }
}

