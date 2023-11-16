package com.sae.sae_juba_antoine_said.modele.projectiles;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

public class Foudre extends ProjectileATeteChercheuse{
    public Foudre(int x, int y, Acteur a, Environnement environnement) {
        super(x, y,9, a, environnement);
    }



}
