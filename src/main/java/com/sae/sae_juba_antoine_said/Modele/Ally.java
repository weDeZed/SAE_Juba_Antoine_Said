package com.sae.sae_juba_antoine_said.Modele;

public abstract class Ally extends Acteur  {

    public Ally(int pv, int x, int y, int vitesse, int range,Environnement env) {
        super(pv,x,y,vitesse,range,env);
    }

    public  abstract void agir();




    public abstract Acteur attaquer();
}
