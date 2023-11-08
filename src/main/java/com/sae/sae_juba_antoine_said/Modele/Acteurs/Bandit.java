package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Bandit extends Ennemi {
    int temp;

    public Bandit() {
        super(100, 52*32, 24*32, 50);
        temp = 0;
    }



    @Override
    public void agir() {
        Acteur a = this.attaquer();
        if (a != null) {
            this.seRaprocher(a);
            if (a.getPv() <= 10) {
                Environnement.getEnvironnementInstance().suppActeur(a);
            } else {
                a.decrementationPv(10);
            }
        } else {
            temp++;
            if (temp % 3 == 0) { //relantir les bandit
                getDeplacement().seDeplacer(this);
            }
        }

    }


}
