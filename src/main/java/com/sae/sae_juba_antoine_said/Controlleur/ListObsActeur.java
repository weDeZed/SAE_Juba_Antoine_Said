package com.sae.sae_juba_antoine_said.Controlleur;


import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Bandit;
import com.sae.sae_juba_antoine_said.Modele.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Environnement;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class ListObsActeur implements ListChangeListener<Acteur> {

    private Pane panneauDeJeu;
    private Environnement ev;

    public ListObsActeur(Pane panneauDeJeu, Environnement ev) {
        this.panneauDeJeu = panneauDeJeu;
        this.ev = ev;
    }

    private void enleverActeur (Acteur m){
        this.panneauDeJeu.getChildren().remove(this.panneauDeJeu.lookup("#"+ m.getId()));
    }

    @Override
    public void onChanged(Change<? extends Acteur> a) {
        while (a.next()) {
            for (Acteur act : a.getAddedSubList()) {
                VueActeur vueActeur = new VueActeur(panneauDeJeu, act);
            }
            for (Acteur m : a.getRemoved()) {
                if (m instanceof Ennemi){
                    ev.ajoutePiece(10);
                }
                System.out.println("mort");
                enleverActeur(m);
            }
        }
    }
}


