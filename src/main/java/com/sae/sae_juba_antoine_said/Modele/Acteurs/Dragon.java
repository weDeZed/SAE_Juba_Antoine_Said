package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
public class Dragon extends Ennemi {
    public Dragon(int x, int y, int vitesse, Environnement env) {
        super(100, x, y, vitesse, 0, env);
    }


    @Override
    public Acteur attaquer() {
        return null;
    }

    @Override
    public void agir() {
        move();
    }
}

