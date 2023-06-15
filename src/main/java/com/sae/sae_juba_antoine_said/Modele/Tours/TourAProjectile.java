package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
import javafx.collections.ObservableList;

public class TourAProjectile extends Tour {


    public TourAProjectile(int x, int y, int degats, int range, Environnement env) {
        super(x, y, degats, range, env);


    }
/*
    @Override
    public void attaqueEnnemi() {};
    /*



 */

    public Projectile creeProjectile() {
        Projectile pro = null;
        for (int i = 0; i <ennemiPlusProche().size(); i++) {
            pro = new Projectile(this.getX() + 10, this.getY() - 30,ennemiPlusProche().get(i), env);
            this.env.ajouterProjectile(pro);

        }

        return pro;
    }



    }







