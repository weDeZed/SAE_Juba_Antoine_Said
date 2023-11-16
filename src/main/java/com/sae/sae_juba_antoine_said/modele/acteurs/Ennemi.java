package com.sae.sae_juba_antoine_said.modele.acteurs;


public abstract class Ennemi extends Acteur {

    public Ennemi(int pv, int x, int y, int range) {
        super(pv, x, y, range,new DeplacementEnnemi());
    }

    @Override
    public boolean MonAdversaire(Acteur a) {
        if (a instanceof Ally){
            return true;
        }
        return false;
    }



}
