package com.sae.sae_juba_antoine_said.modele.tours;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import com.sae.sae_juba_antoine_said.modele.projectiles.Laser;
import com.sae.sae_juba_antoine_said.modele.projectiles.Projectile;

public class TourALaser extends TourAProjectile {


    public TourALaser(int x, int y, int range, Environnement environnement) {

        super(x, y, range, environnement);

    }

    @Override
    public Projectile creeProjectile() {
        Acteur act = this.ennemiPlusProche();
        Projectile pro = new Laser(this.getX() + 10, this.getY() - 30, act, env);
        this.env.ajouterProjectile(pro);
        return pro;

    }

}
