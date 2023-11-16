package com.sae.sae_juba_antoine_said.modele.projectiles;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;

public class ProjectileDirect extends Projectile {

    private double directionX, directionY;

    public ProjectileDirect(int x, int y, int degat, Acteur cible, Environnement environnement) {
        super(x, y, degat, cible, environnement);

        // Calculer la direction initiale du projectile vers la cible.
        calculerDirection(cible);
    }

    private void calculerDirection(Acteur cible) {
        double distanceX = cible.getX() - this.getX();
        double distanceY = cible.getY() - this.getY();
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        this.directionX = distanceX / totalDistance;
        this.directionY = distanceY / totalDistance;
    }

    @Override
    public void lancerProjectile() {
        int nouvellePosX = (int) (this.getX() + this.getVitesse() * this.directionX);
        int nouvellePosY = (int) (this.getY() + this.getVitesse() * this.directionY);


        this.setX(nouvellePosX);
        this.setY(nouvellePosY);

    }

    private boolean estHorsDeLaCarte() {

        return this.getX() < 0 || this.getX() >= env.getX() ||
                this.getY() < 0 || this.getY() >= env.getY();
    }
}
