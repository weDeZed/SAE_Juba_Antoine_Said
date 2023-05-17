package com.sae.sae_juba_antoine_said.Modele;

public abstract class Ally extends Acteur  {



    public Ally(int pv, int x, int y, int vitesse, int range) {
        super(pv,x,y,vitesse,range);
    }


    public  void agir(){
        this.setX(30);
        this.setY(50);
    }


    public abstract void attaquer();

}
