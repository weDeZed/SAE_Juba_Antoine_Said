package com.sae.sae_juba_antoine_said.Modele;

public class Golem extends Ennemi{
    public Golem(int x, int y, int vitesse,Environnement env) {
        super(120, x, y, vitesse,0,env);
    }



    @Override
    public Acteur attaquer() {
        return null;
    }

    @Override
    public void agir() {
        Acteur a=attaquer();
        this.seDeplacer(a);
        this.decrementationPv(1);
    }

    public void seDeplacer(Acteur a) {
        this.setX(this.getX()+1);
    }
}

