package com.sae.sae_juba_antoine_said.Modele.vague;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Dragon;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.FabriqueSimpleActeur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.application.Platform;

public class TypeVagueDragon implements TypeVague{

    @Override
    public void ajouteEnnemis(Environnement env) {
        env.setNbEnemies(5);
        for (int i = 0; i < 5; i++) {
            env.ajouterEnnemis(new FabriqueSimpleActeur().creerActeur("dragon"));
        }
    }
}
