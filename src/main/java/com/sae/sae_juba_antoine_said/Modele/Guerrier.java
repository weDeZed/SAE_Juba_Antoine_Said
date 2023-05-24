package com.sae.sae_juba_antoine_said.Modele;

public class Guerrier extends Ally {


    public Guerrier( int x, int y,Environnement ev) {
        super(70, x, y, 20, 0,ev);
    }

    @Override
    public void seDeplacer() {

    }

    @Override
    public Acteur attaquer() {
      return null;
    }

}
