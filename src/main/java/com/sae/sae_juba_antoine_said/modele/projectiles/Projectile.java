package com.sae.sae_juba_antoine_said.modele.projectiles;

import com.sae.sae_juba_antoine_said.modele.acteurs.Acteur;
import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile {

    IntegerProperty x, y;
    int vitesse;
    Environnement env;
    String id;
    static int compteur = 0;

    Acteur a;

    int disX, disY;
    int degat;


    public Projectile(int x, int y, int degat, Acteur a, Environnement environnement) {

        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = environnement;
        this.vitesse = 32;
        id = "P" + compteur;
        compteur++;
        this.a = a;
        this.degat = degat;
    }

    public abstract void lancerProjectile();


    public boolean atteintActeur() {
        // Les coins du projectile
        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + 38;
        int y2 = getY() + 13;

        // Les coins de l'acteur
        int aX1 = a.getX();
        int aY1 = a.getY();
        int aX2 = a.getX() + 32;
        int aY2 = a.getY() + 32;

        // Vérifie si l'un des coins du projectile est à l'intérieur de la hitbox de l'acteur
        if ((x1 >= aX1 && x1 <= aX2 && y1 >= aY1 && y1 <= aY2) ||  // Coin supérieur gauche
                (x1 >= aX1 && x1 <= aX2 && y2 >= aY1 && y2 <= aY2) ||  // Coin inférieur gauche
                (x2 >= aX1 && x2 <= aX2 && y1 >= aY1 && y1 <= aY2) ||  // Coin supérieur droit
                (x2 >= aX1 && x2 <= aX2 && y2 >= aY1 && y2 <= aY2)) {  // Coin inférieur droit
            // env.removeProjectile(this);
            return true;
        }


        return false;
    }


    public int getDegat() {
        return degat;
    }


    public String getId() {
        return id;
    }


    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }


    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public boolean aFiniTrajectoire() {
        return atteintActeur();
    }

    public Acteur getFocusProjectile() {
        return a;
    }


}