package com.sae.sae_juba_antoine_said.Modele.vague;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Dragon;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.application.Platform;

public class TypeVagueDragon implements TypeVague{

    @Override

    public void ajouteEnnemis(Environnement env) {
        env.ajouterActeur(new Dragon(52 * 32, 24 * 32, 0));
    }
}
