package com.sae.sae_juba_antoine_said.Modele.Projectiles;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class autreTypeDeProjectile extends Projectile {
    public autreTypeDeProjectile(int x, int y, int degat, Acteur a, Environnement environnement) {
        super(x, y, degat, a, environnement);
    }

    @Override
    public void lancerProjectile() {

    }
}
