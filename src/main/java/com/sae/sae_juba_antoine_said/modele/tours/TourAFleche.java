package com.sae.sae_juba_antoine_said.modele.tours;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import com.sae.sae_juba_antoine_said.modele.projectiles.Fleche;
import com.sae.sae_juba_antoine_said.modele.projectiles.Projectile;

public class TourAFleche extends TourAProjectile{


    public TourAFleche(int x, int y, int range, Environnement env) {
        super(x, y, range, env);
    }

    @Override
    public Projectile creeProjectile() {
        Acteur act=this.ennemiPlusProche();
        Projectile pro = new Fleche(this.getX() + 10, this.getY() - 30,act, env);
        this.env.ajouterProjectile(pro);
        return pro;

    }



}
