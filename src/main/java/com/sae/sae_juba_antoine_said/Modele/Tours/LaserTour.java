package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class LaserTour extends TourAProjectile {


    public LaserTour(int x, int y, int range, Environnement environnement) {

        super(x, y, range, environnement);

    }

    @Override
    public Projectile creeProjectile() {
        Projectile pro;
        Acteur act = this.ennemiPlusProche();
        TeteChercheuse t = new TeteChercheuse();
        if (act != null) {

            pro = new Laser(this.getX() + 10, this.getY() - 30, act, env,t);
            //pro.setFaconDAgir(t);
            this.env.ajouterProjectile(pro);
           // System.out.println("Projectile dans Laser Tour: " + pro);
            return pro;
        }
        return null;
    }


}
