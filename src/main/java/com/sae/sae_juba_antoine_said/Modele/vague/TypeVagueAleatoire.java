package com.sae.sae_juba_antoine_said.Modele.vague;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.*;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.application.Platform;

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
