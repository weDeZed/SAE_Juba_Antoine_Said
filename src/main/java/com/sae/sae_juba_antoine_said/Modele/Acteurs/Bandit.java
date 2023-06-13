package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Bandit extends Ennemi {

    public Bandit(int x, int y, int vitesse, Environnement ev) {
        super(50, x, y, vitesse, 50, ev);
    }

    @Override
    public Acteur attaquer() {
        for (Acteur a : this.env.getActeurs()) {
            if (a instanceof Ally && a.estVivant()) {
                if (this.getY() - this.getRange() <= a.getY() && a.getY() <= this.getY() + this.getRange() &&
                        this.getX() - this.getRange() <= a.getX() && a.getX() <= this.getX() + this.getRange()) {
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public void agir() {
        Acteur a = this.attaquer();
        if (a != null) {
            this.seDeplacer(a);
            if (a.getPv() > 0) {
                a.decrementationPv(8);
            } else {
                a.meurt();
                env.suppActeur(a);
            }
        } else {
            move();
        }

    }



}
