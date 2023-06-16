package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Dragon extends Ennemi {
    private int nb;

    public Dragon(int x, int y, int vitesse, Environnement env) {
        super(150, x, y, vitesse, 0, env);
        nb = 0;
    }


    @Override
    public Acteur attaquer() {
        return null;
    }

    @Override
    public void agir() {
        nb++;
        if (nb % 2 == 0) {
            move();
        }
    }
}

