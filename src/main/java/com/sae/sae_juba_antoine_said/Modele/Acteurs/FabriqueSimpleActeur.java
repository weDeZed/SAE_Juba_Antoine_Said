package com.sae.sae_juba_antoine_said.Modele.Acteurs;

public class FabriqueSimpleActeur {

    public Acteur creerActeur(String type) {
        Acteur acteur=null;
        switch(type){
            case "bandit":
                acteur =new Bandit();
                break;
            case "guerrier":
                acteur =new Guerrier();
                break;
            case "dragon":
                acteur =new Dragon();
                break;
        }

        return acteur;
    }
}
