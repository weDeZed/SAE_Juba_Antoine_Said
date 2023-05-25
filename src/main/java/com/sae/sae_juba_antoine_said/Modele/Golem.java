package com.sae.sae_juba_antoine_said.Modele;

public class Golem extends Acteur{
    public Golem(int x, int y, int vitesse,Environnement env) {
        super(120, x, y, vitesse,0,env);
    }

    @Override
    public Acteur attaquer() {
        return null;
    }

    @Override
    public void agir() {
        this.seDeplacer();
        this.decrementationPv(1);
    }

    @Override
    public void seDeplacer() {
        this.setX(this.getX()+1);
    }
}

