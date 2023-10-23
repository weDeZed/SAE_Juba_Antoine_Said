package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Bandit extends Ennemi {
    int temp;

    public Bandit(int x, int y, int vitesse, Environnement ev) {
        super(100, x, y, vitesse, 50, ev);
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
