package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class Acteur {
    private final int CHEMIN = 1427;
    private int vitesse, range, pv;
    private IntegerProperty x, y;

    private String id;
    public static int compteur = 0;
    private Environnement env;

    BFS bfs;
    Sommet sommet;


    public Acteur(int pv, int x, int y, int vitesse, int range,Environnement env) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = vitesse;
        this.range = range;
        this.pv = pv;
        this.env=env;
        this.id = "A" + compteur;
        compteur++;
    }

    public abstract Acteur attaquer();

    public abstract void agir();

    public String getId() {
        return id;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }


    public boolean collisionDroit(Acteur acteur, Environnement environnement) {
        //System.out.println("X "+acteur.getX()+" Y "+acteur.getY());
        if (environnement.getMap()[acteur.getX() / 16 + 16][acteur.getY() / 16] != CHEMIN) {
            System.out.println("CollisionDroit " + environnement.getMap()[acteur.getX() / 16 + 16][acteur.getY() / 16]);
            return true;
        }
        System.out.println("X " + acteur.getX() + " Y " + acteur.getY());
        System.out.println("pas CollisionDroit " + environnement.getMap()[acteur.getX() / 16 + 16][acteur.getY() / 16]);
        return false;
    }


    public boolean collisionGauche(Acteur acteur, Environnement environnement) {

        if (environnement.getMap()[acteur.getX() / 16 - 16][getY() / 16] != CHEMIN) {
            //System.out.println("X "+acteur.getX()+" Y "+acteur.getY());
            System.out.println("collision Gauch " + environnement.getMap()[acteur.getX() / 16 - 16][getY() / 16]);
            return true;
        }
        System.out.println("X " + acteur.getX() + " Y " + acteur.getY());
        System.out.println("pas collision Gauche " + environnement.getMap()[acteur.getX() / 16 - 16][getY() / 16]);
        return false;
    }

    public boolean collisionBas(Acteur acteur, Environnement environnement) {
        if (environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 + 16] != CHEMIN) {
            System.out.println("collision bas" + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 + 16]);
            return true;
        }
        return false;
    }


    public boolean collisionHaut(Acteur acteur, Environnement environnement) {
        if (environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16] != CHEMIN) {
            //System.out.println("X "+acteur.getX()+" Y "+acteur.getY());
            System.out.println("collision haut " + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16]);
            return true;
        }
        System.out.println("X " + acteur.getX() + " Y " + acteur.getY());
        System.out.println("pas collision haut " + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16]);
        return false;
    }

    public boolean estVivant(){
        return this.pv >0;
    }

    public void decrementationPv(int pv){
        this.pv -= pv;
    }

    public int getPv(){
        return this.pv;
    }

    public int getVitesse(){
        return this.vitesse;
    }

    public void meurt(){
        this.pv = 0;
    }

    public void setRange(int range){
        this.range = range;
    }

    public int getRange(){
        return this.range;
    }

    public abstract void seDeplacer(Acteur a);

    public Environnement getEnv() {
        return env;
    }

/*
    public void move() {
        if (indiceCheminActuel < chemin.size() - 1) {
            indiceCheminActuel++;
            this.setX(chemin.get(indiceCheminActuel).getX()*16);
            this.setY(chemin.get(indiceCheminActuel).getY()*16);
        }

    }

 */
}
