package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Dragon extends Ennemi {
    private int nb;

    public Dragon() {
        super(150, 52 * 32, 24 * 32, 0);
        nb = 0;
    }


    @Override
    public void agir() {
        nb++;
        if (nb % 4 == 0) {
            getDeplacement().seDeplacer(this);
        }
    }
}

