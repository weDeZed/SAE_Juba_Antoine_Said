package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public abstract class TourAProjectile extends Tour {
    private int tour;


    public TourAProjectile(int x, int y, int range, Environnement env) {
        super(x, y, 50, range, env);
        tour = 0;


    }

    public void attaqueEnnemi() {
        if (this.ennemiPlusProche() != null && this.ennemiPlusProche().estVivant()) {
            Projectile p = ((TourAProjectile) this).creeProjectile();
            //this.ajouterProjectile(p);
        }
    }


    public abstract Projectile creeProjectile();

    }







