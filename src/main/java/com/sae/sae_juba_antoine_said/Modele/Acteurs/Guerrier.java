package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

import java.util.Random;

public class Guerrier extends Ally {

    private int startX;
    private int startY;
    int temps;

    public Guerrier(int x, int y) {
        super(100, x, y, 20, 50);
        this.startX = x / 32;
        this.startY = y / 32;
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
