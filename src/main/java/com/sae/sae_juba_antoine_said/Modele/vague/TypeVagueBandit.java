package com.sae.sae_juba_antoine_said.Modele.vague;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.FabriqueSimpleActeur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class TypeVagueBandit implements TypeVague{
    @Override
    public void ajouteEnnemis(Environnement environment) {
        environment.setNbEnemies(5);
        for (int i = 0; i < 5; i++) {
            environment.ajouterEnnemis(new FabriqueSimpleActeur().creerActeur("bandit"));
        }
    }
}
