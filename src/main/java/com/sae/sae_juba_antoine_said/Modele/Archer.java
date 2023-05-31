package com.sae.sae_juba_antoine_said.Modele;

public class Archer extends Ally {

    public Archer(int pv, int x, int y,Environnement env) {
        super(pv, x, y, 30, 3,env);

    }
    @Override
    public void seDeplacer() {

    }

    @Override
    public void agir() {

    }

    @Override
    public Archer attaquer() {
        return null;
    }

}
