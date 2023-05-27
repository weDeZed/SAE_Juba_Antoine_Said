package com.sae.sae_juba_antoine_said.Modele;

public abstract class Ennemi extends Acteur{
    public Ennemi(int pv, int x, int y, int vitesse, int range,Environnement env) {
        super(pv, x, y, vitesse, range,env);
    }

    @Override
    public void seDeplacer(Acteur a) {

    }

    @Override
    public abstract Acteur attaquer();

    @Override
    public void agir() {

    }
}
