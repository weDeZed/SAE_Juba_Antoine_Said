package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Fleche extends Projectile{
    public Fleche(int x, int y, Acteur a, Environnement environnement) {
        super(x, y,6, a, environnement);
    }
}
