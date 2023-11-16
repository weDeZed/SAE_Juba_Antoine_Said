package com.sae.sae_juba_antoine_said.modele.vague;

import com.sae.sae_juba_antoine_said.modele.acteurs.FabriqueSimpleActeur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

public class TypeVagueDragon implements TypeVague{

    @Override
    public void ajouteEnnemis(Environnement env) {
        env.setNbEnemies(5);
        for (int i = 0; i < 5; i++) {
            env.ajouterEnnemis(new FabriqueSimpleActeur().creerActeur("dragon"));
        }
    }
}
