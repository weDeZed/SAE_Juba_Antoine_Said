package com.sae.sae_juba_antoine_said.Modele.Projectiles;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class ProjectileATeteChercheuse extends Projectile {


    public ProjectileATeteChercheuse(int x, int y, int degat, Acteur a, Environnement environnement) {
        super(x, y, degat, a, environnement);
    }


    public void lancerProjectile() {

        System.out.println("Les coordonnées du projectile "+ this + "X : " + this.getX() + " Y : " + this.getY() );

        double distanceX = a.getX() - this.getX();
        double distanceY = a.getY() - this.getY();
        System.out.println("Coordonée de l'acteur " + a +  " X : "  + a.getX() + " Y : " + a.getY());


        // Calcule la distance total
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


        double directionX = distanceX / totalDistance;
        double directionY = distanceY / totalDistance;

        // Calculer les nouvelles positions
        double newposX = this.getX() + (this.getVitesse() * directionX);
        double newposY = this.getY() + (this.getVitesse() * directionY);


        this.setX((int) newposX);
        this.setY((int) newposY);

        System.out.println("Les coordonnées du projectile a la fin du code est : "+ this + "X : " + this.getX() + " Y : " + this.getY() );


        if (atteintActeur() == true) {

            a.decrementationPv(getDegat());
        }

        if (a.getPv() <= 0) {
            env.ajoutePiece(10);
            env.suppActeur(a);
        }


        if (!getFocusProjectile().estVivant()) {
            env.getProjectiles().remove(this);
        }


    }




}

