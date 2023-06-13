package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class TourFoudre extends Tour {
    public TourFoudre(int x, int y, int degats, Environnement environnement) {
        super(x, y, degats,20, environnement);
    }
}
