package com.sae.sae_juba_antoine_said.Modele.Acteurs;


import com.sae.sae_juba_antoine_said.Modele.BFS.Sommet;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class Ennemi extends Acteur {

    private int indiceCheminActuel = 0;
    private IntegerProperty dA;
    private final int CHEMIN = 230;
    ArrayList<Sommet> cheminActuel;


    public Ennemi(int pv, int x, int y,  int vitesse, int range, Environnement env) {
        super(pv, x, y, vitesse, range, env);
        dA = new SimpleIntegerProperty(0);
        cheminActuel = new ArrayList<Sommet>();

    }

    @Override
    public void seDeplacer() {

    }

    public int directionActeur() {
        return dA.getValue();
    }

    public IntegerProperty directionACteurProperty() {
        return dA;
    }

    @Override
    public abstract Acteur attaquer();

    @Override
    public void agir() {

    }

    public void move() {
        if (indiceCheminActuel < this.env.getChemin().size() - 1) {
            indiceCheminActuel++;
            int newX = this.env.getChemin().get(indiceCheminActuel).getX() * 32;
            int newY = this.env.getChemin().get(indiceCheminActuel).getY() * 32;
            setX(newX);
            setY(newY);

        } else {
            this.env.suppActeur(this);
        }
    }
}
