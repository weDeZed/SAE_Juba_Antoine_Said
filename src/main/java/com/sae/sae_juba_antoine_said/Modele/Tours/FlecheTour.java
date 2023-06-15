package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class FlecheTour extends TourAProjectile{


    public FlecheTour(int x, int y, int degats, int range, Environnement env) {
        super(x, y, degats, range, env);
    }

    @Override
    public  Projectile creeProjectile() {
        Acteur act=this.ennemiPlusProche();
        Projectile pro = new Fleche(this.getX() + 10, this.getY() - 30,act, env);
        this.env.ajouterProjectile(pro);
        return pro;

    }



}
