package com.sae.sae_juba_antoine_said.Modele.Acteurs;


import com.sae.sae_juba_antoine_said.Modele.BFS.Sommet;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class Ennemi extends Acteur {

    private int indiceCheminActuel = 0;
    //private IntegerProperty directionActeur;
    private final int CHEMIN = 230;
    ArrayList<Sommet> cheminActuel;


    public Ennemi(int pv, int x, int y,  int vitesse, int range, Environnement env) {
        super(pv, x, y, vitesse, range, env);
        //directionActeur = new SimpleIntegerProperty(0);
        cheminActuel = new ArrayList<Sommet>();

    }

    @Override
    public void seDeplacer() {

    }



    @Override
    public abstract Acteur attaquer();

    @Override
    public void agir() {

    }

    public void move() {
        if (indiceCheminActuel < this.env.getChemin().size() - 1) {
            // Enregistrer les anciennes coordonnées
            int oldX = getX();
            int oldY = getY();

            indiceCheminActuel++;

            int newX = this.env.getChemin().get(indiceCheminActuel).getX() * 32;
            int newY = this.env.getChemin().get(indiceCheminActuel).getY() * 32;

            if (newX > oldX) {
              setDirectionActeur(1); //direction droit
                //System.out.println("Le soldat se déplace vers la droite.");
            } else if (newX < oldX) {
                setDirectionActeur(2); // direction gauche
                //System.out.println("Le soldat se déplace vers la gauche.");
            } else if (newY > oldY) {
                setDirectionActeur(3); //direction bas
                //System.out.println("Le soldat se déplace vers le bas.");
            } else if (newY < oldY) {
                setDirectionActeur(4); // direction haut
                //System.out.println("Le soldat se déplace vers le haut.");
            }

            setX(newX);
            setY(newY);

        } else {
            this.env.suppActeur(this);
            env.decrementerVie(10);
        }
    }



}
