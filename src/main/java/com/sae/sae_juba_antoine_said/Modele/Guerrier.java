package com.sae.sae_juba_antoine_said.Modele;

public class Guerrier extends Ally {


    public Guerrier(int pv, int x, int y) {
        super(pv, x, y, 20, 0);
    }

    @Override
    public void seDeplacer() {

    }

    @Override
    public void attaquer() {
        System.out.println("attaque");
    }

}
