package com.sae.sae_juba_antoine_said.Modele;

public abstract class Ennemi extends Acteur{
    public Ennemi(int pv, int x, int y, int vitesse, int range) {
        super(pv, x, y, vitesse, range);
    }

    @Override
    public void seDeplacer() {

    }

    @Override
    public abstract Acteur attaquer();

    @Override
    public void agir() {

    }
}
