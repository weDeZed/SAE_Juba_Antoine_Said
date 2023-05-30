package com.sae.sae_juba_antoine_said.Modele;

import java.util.ArrayList;

public abstract class Ally extends Acteur  {
    private int indiceCheminActuel = 0;

    public Ally(int pv, int x, int y, int vitesse, int range,Environnement env) {
        super(pv,x,y,vitesse,range,env);

    }

    public  abstract void agir();
    public abstract Acteur attaquer();
    /*
    public void move() {
        //System.out.println("dans ally "+chemin);
        if (indiceCheminActuel < chemin.size() - 1) {
            indiceCheminActuel++;
            this.setX(chemin.get(indiceCheminActuel).getX()*16);
            this.setY(chemin.get(indiceCheminActuel).getY()*16);
        }
    }

     */

}
