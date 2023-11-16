package com.sae.sae_juba_antoine_said.modele.projectiles;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

public class ProjectileATeteChercheuse extends Projectile {


    public ProjectileATeteChercheuse(int x, int y, int degat, Acteur a, Environnement environnement) {
        super(x, y, degat, a, environnement);
    }


    public void lancerProjectile() {


        double distanceX = a.getX() - this.getX();
        double distanceY = a.getY() - this.getY();


        // Calcule la distance total
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


        double directionX = distanceX / totalDistance;
        double directionY = distanceY / totalDistance;

        // Calculer les nouvelles positions
        double newposX = this.getX() + (this.getVitesse() * directionX);
        double newposY = this.getY() + (this.getVitesse() * directionY);


        this.setX((int) newposX);
        this.setY((int) newposY);



        if (atteintActeur() == true) {

            a.decrementationPv(getDegat());
        }

        if (a.getPv() <= 0) {
            env.ajoutePiece(10);
            env.decrementNbEnemies(1);
            env.suppActeur(a);
        }


        if (!getFocusProjectile().estVivant()) {
            env.getProjectiles().remove(this);
        }


    }




}

