package com.sae.sae_juba_antoine_said.modele.acteurs;

public abstract class Ally extends Acteur {

    public Ally(int pv, int x, int y, int range) {
        super(pv,x,y,range,new DeplacementAlly());

    }

    @Override
    public boolean MonAdversaire(Acteur a) {

        if (a instanceof Ennemi){
            return true;
        }
        return false;
    }

}
