package com.sae.sae_juba_antoine_said.Modele.Acteurs;


import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public abstract class Ennemi extends Acteur {

    private int indiceCheminActuel = 0;
    //private IntegerProperty directionActeur;

    public Ennemi(int pv, int x, int y, int vitesse, int range) {
        super(pv, x, y, vitesse, range,new DeplacementEnnemi());
    }

    @Override
    public boolean MonAdversaire(Acteur a) {
        if (a instanceof Ally){
            return true;
        }
        return false;
    }



}
