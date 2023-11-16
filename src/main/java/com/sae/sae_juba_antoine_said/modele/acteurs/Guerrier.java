package com.sae.sae_juba_antoine_said.modele.acteurs;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

public class Guerrier extends Ally {


    int temps;

    public Guerrier() {
        super(100, 0, 0,  50);

        temps = 0;
    }


    @Override
    public void agir() {
        Environnement env=Environnement.getEnvironnementInstance();
        temps++;
        Acteur a = this.attaquer();
        if (a != null) {
            this.seRaprocher(a);
            if (a.getPv() <= 10) {
                env.suppActeur(a);
                env.ajoutePiece(5);
                env.decrementNbEnemies(1);
            } else {
                a.decrementationPv(7);
            }
        } else {
            if (temps % 5 == 0) {
                getDeplacement().seDeplacer(this);
            }
        }
    }
}
