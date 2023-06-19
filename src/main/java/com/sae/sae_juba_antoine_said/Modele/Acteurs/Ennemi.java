package com.sae.sae_juba_antoine_said.Modele.Acteurs;


import com.sae.sae_juba_antoine_said.Modele.BFS.Sommet;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.crypto.spec.PSource;
import java.util.ArrayList;

public abstract class Ennemi extends Acteur {

    private int indiceCheminActuel = 0;
    //private IntegerProperty directionActeur;



    public Ennemi(int pv, int x, int y, int vitesse, int range, Environnement env) {
        super(pv, x, y, vitesse, range, env);



    }


    @Override
    public abstract Acteur attaquer();

    @Override
    public abstract void agir();

    public void move() {
        if (indiceCheminActuel < this.env.getChemin().size() - 1) {
            int oldX = (this.env.getChemin().get(indiceCheminActuel).getX() * 32);
            int oldY = (this.env.getChemin().get(indiceCheminActuel).getY() * 32);
            indiceCheminActuel++;
            int newX = (this.env.getChemin().get(indiceCheminActuel).getX() * 32);
            int newY = (this.env.getChemin().get(indiceCheminActuel).getY() * 32);
            if (newX > oldX) {
                setDirectionActeur(1); //direction droit
            } else if (newX < oldX) {
                setDirectionActeur(2); //direction gauche
            } else if (newY > oldY) {
                setDirectionActeur(3); //direction bas
            } else if (newY < oldY) {
                setDirectionActeur(4); // direction haut
            }
            this.setX(newX);
            this.setY(newY);
        } else {
            this.env.suppActeur(this);
            env.decrementerVie(10);
        }
    }


}
