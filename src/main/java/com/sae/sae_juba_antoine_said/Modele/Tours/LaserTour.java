package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class LaserTour extends Tour {
    public LaserTour(int x, int y, int degats, int range, Environnement environnement) {
        super(x, y, degats,50, range, environnement);
    }
}
