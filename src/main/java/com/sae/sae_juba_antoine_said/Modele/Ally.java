package com.sae.sae_juba_antoine_said.Modele;

public abstract class Ally extends Acteur  {

    public Ally(int pv, int x, int y, int vitesse, int range, Environnement ev) {
        super(pv,x,y,vitesse,range,ev);
    }

    public  void agir(){
        this.setX(30);
        this.setY(50);
    }

    public abstract Acteur attaquer();
}
