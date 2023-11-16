package com.sae.sae_juba_antoine_said.modele.projectiles;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

public class autreTypeDeProjectile extends Projectile {
    public autreTypeDeProjectile(int x, int y, int degat, Acteur a, Environnement environnement) {
        super(x, y, degat, a, environnement);
    }

    @Override
    public void lancerProjectile() {

    }
}
