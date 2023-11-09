package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.*;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsTour implements ListChangeListener<Tour> {


    private Pane panneauDeJeu;
    private VueTour vueTour;
    private Environnement environnement;
    int troopTourPrix = 150, tourFoudrePrix = 20, laserTourPrix = 30, tourAprojectilePrix = 50;
    public ListObsTour(Pane panneauDeJeu, Environnement environnement) {
        this.panneauDeJeu = panneauDeJeu;
        this.environnement = environnement;
    }

    @Override
    public void onChanged(Change<? extends Tour> t) {


        while (t.next()) {
            for (Tour tour : t.getAddedSubList()) {
                vueTour = new VueTour(panneauDeJeu, tour);
                enleveLesPieces(tour);
            }
        }
    }

    public void enleveLesPieces(Tour tour) {
        if (tour instanceof TourASoldat) {
            environnement.setPiece(troopTourPrix);
        }
        if (tour instanceof TourAFoudre) {
            environnement.setPiece(tourFoudrePrix);
        }
        if (tour instanceof TourAFleche) {
            environnement.setPiece(laserTourPrix);
        }
        if (tour instanceof TourALaser) {
            environnement.setPiece(tourAprojectilePrix);
        }
    }

}