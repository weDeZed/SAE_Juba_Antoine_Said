package com.sae.sae_juba_antoine_said.Modele.vague;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Bandit;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Dragon;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.application.Platform;

import static java.lang.Thread.sleep;

public class TypeVagueAleatoire implements TypeVague{
    @Override
    public void ajouteEnnemis(Environnement env) {
        int nbEmmenies=10;
        env.setNbEnemies(nbEmmenies);
        for (int i=0; i<nbEmmenies; i++){
                int type = (int) (Math.random() * 2); //  soit bandit et soit Dragon
                Ennemi e;
                if (type == 1) {
                    e = new Bandit(52 * 32, 24 * 32, 0);
                } else {
                    e = new Dragon(52 * 32, 24 * 32, 0);
                }
                env.ajouterEnnemis(e);
        }
    }
}
