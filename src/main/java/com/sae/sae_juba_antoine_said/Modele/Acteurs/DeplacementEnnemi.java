package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class DeplacementEnnemi implements Deplacement{
    private int indiceCheminActuel = 0;
    @Override
    public void seDeplacer(Acteur acteur) {
        Environnement env=Environnement.getEnvironnementInstance();

        if (indiceCheminActuel < env.getChemin().size() - 1) {
            int oldX = (env.getChemin().get(indiceCheminActuel).getX() * 32);
            int oldY = (env.getChemin().get(indiceCheminActuel).getY() * 32);
            indiceCheminActuel++;
            int newX = (env.getChemin().get(indiceCheminActuel).getX() * 32);
            int newY = (env.getChemin().get(indiceCheminActuel).getY() * 32);
            if (newX > oldX) {
                acteur.setDirectionActeur(1); //direction droit
            } else if (newX < oldX) {
                acteur.setDirectionActeur(2); //direction gauche
            } else if (newY > oldY) {
                acteur.setDirectionActeur(3); //direction bas
            } else if (newY < oldY) {
                acteur.setDirectionActeur(4); // direction haut
            }
            acteur.setX(newX);
            acteur.setY(newY);
        } else {
            env.suppActeur(acteur);
            env.decrementerVie(10);
        }
    }
}
