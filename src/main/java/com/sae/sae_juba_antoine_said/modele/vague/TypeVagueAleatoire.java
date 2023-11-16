package com.sae.sae_juba_antoine_said.modele.vague;

import com.sae.sae_juba_antoine_said.modele.acteurs.*;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

import static java.lang.Thread.sleep;

public class TypeVagueAleatoire implements TypeVague{
    @Override
    public void ajouteEnnemis(Environnement env) {
        FabriqueSimpleActeur fabriqueSimpleActeur = new FabriqueSimpleActeur();
        int nbEmmenies=5;
        env.setNbEnemies(nbEmmenies);
        for (int i=0; i<nbEmmenies; i++){
                int type = (int) (Math.random() * 2); //  soit bandit et soit Dragon
                Acteur e;
                if (type == 1) {
                    e = fabriqueSimpleActeur.creerActeur("bandit");
                } else {
                    e = fabriqueSimpleActeur.creerActeur("dragon");
                }
                env.ajouterEnnemis(e);
        }
    }
}
