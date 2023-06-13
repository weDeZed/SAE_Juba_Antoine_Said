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
    private final int CHEMIN = 230;
    ArrayList<Sommet> cheminActuel;


    public Ennemi(int pv, int x, int y, int vitesse, int range, Environnement env) {
        super(pv, x, y, vitesse, range, env);
        //directionActeur = new SimpleIntegerProperty(0);
        cheminActuel = new ArrayList<Sommet>();

    }




    @Override
    public abstract Acteur attaquer();

    @Override
    public  abstract void agir();

    public void move() {
        if (indiceCheminActuel < this.env.getChemin().size() - 1) {

            int oldX = (this.env.getChemin().get(indiceCheminActuel).getX() * 32);
            int oldY = (this.env.getChemin().get(indiceCheminActuel).getY() * 32);
            indiceCheminActuel++;
            int newX = (this.env.getChemin().get(indiceCheminActuel).getX() * 32);
            int newY = (this.env.getChemin().get(indiceCheminActuel).getY() * 32);

            if (newX > oldX) {
                setDirectionActeur(1); //direction droit
                //System.out.println(" vers droit ");
            } else if (newX < oldX) {
                //System.out.println(" vers gauche ");
                setDirectionActeur(2); //direction gauche
            } else if (newY > oldY) {
                //System.out.println(" vers bas ");
                setDirectionActeur(3); //direction bas
            } else if (newY < oldY) {
                //System.out.println(" vers haut ");
                setDirectionActeur(4); // direction haut
            }
            //System.out.println(" final après new X " + newX + " newY " + newY);
            this.setX(newX);
            this.setY(newY);
        } else {
            this.env.suppActeur(this);
            env.decrementerVie(50);
            env.ajouterPieces(10);
        }
    }


}
