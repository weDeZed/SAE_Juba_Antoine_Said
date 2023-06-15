package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class LaserTour extends TourAProjectile {


    public LaserTour(int x, int y, int degats, int range, Environnement environnement) {

        super(x, y, degats, range, environnement);

    }

    @Override
    public Projectile creeProjectile() {
        //System.out.println("lancer fleche tour");
        Acteur act = this.ennemiPlusProche();
        Projectile pro = new Laser(this.getX() + 10, this.getY() - 30, act, env);
        this.env.ajouterProjectile(pro);
        return pro;

    }

}
