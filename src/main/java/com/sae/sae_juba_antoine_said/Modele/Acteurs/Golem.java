package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
public class Golem extends Ennemi {
    public Golem(int x, int y, int vitesse, Environnement env) {
        super(120, x, y, vitesse, 0, env);
    }


    @Override
    public Acteur attaquer() {
        return null;
    }

    @Override
    public void agir() {
        Acteur a = attaquer();
        this.seDeplacer(a);
        this.decrementationPv(1);
    }
}

