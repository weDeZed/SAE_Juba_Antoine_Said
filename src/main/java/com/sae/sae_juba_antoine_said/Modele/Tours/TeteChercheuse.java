package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;

public class TeteChercheuse implements FaconDAgir {


    public TeteChercheuse() {
    }

    public void setProjectile(Projectile p) {
        this.p = p;
    }

    public void agir() {


        System.out.println("Entree méthode LancerProjectile");

        Acteur a = p.getFocusProjectile();



        double distanceX = a.getX() - p.getX();
        double distanceY = a.getY() - p.getY();

        // Calcule la distance total
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


        double directionX = distanceX / totalDistance;
        double directionY = distanceY / totalDistance;

        // Calculer les nouvelles positions
        double newposX = p.getX() + (p.getVitesse() * directionX);
        double newposY = p.getY() + (p.getVitesse() * directionY);


        p.setX((int) newposX);
        p.setY((int) newposY);
        System.out.println("New x : " + p.getX() );
        System.out.println("New y : " + p.getY() );

        if (p.atteintActeur() == true) {
            a.decrementationPv(p.getDegat());
            System.out.println(p + " : a atteint un acteur ");

        }

        if (a.getPv() <= 0) {
            p.getEnvProjectile().ajoutePiece(10);
            p.getEnvProjectile().suppActeur(a);
        }
        p.getEnvProjectile().getProjectiles().remove(p);

    }




}
