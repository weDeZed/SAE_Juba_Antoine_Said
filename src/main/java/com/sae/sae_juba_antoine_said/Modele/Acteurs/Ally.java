package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public abstract class Ally extends Acteur {

    public Ally(int pv, int x, int y, int vitesse, int range, Environnement env) {
        super(pv,x,y,vitesse,range,env,new DeplacementAlly());

    }

    @Override
    public boolean MonAdversaire(Acteur a) {

        if (a instanceof Ennemi){
            return true;
        }
        return false;
    }

}
