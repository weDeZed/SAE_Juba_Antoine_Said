package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Laser extends ProjectileATeteChercheuse {


    public Laser(int x, int y, Acteur a, Environnement environnement) {
        super(x, y,15, a, environnement);
    }
}
