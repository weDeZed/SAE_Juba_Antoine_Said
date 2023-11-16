package com.sae.sae_juba_antoine_said.modele.projectiles;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

public class Fleche extends ProjectileATeteChercheuse{
    public Fleche(int x, int y, Acteur a, Environnement environnement) {
        super(x, y,6, a, environnement);
    }
}
