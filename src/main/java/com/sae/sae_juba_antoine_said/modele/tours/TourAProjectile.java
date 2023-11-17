package com.sae.sae_juba_antoine_said.modele.tours;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import com.sae.sae_juba_antoine_said.modele.projectiles.Projectile;

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






