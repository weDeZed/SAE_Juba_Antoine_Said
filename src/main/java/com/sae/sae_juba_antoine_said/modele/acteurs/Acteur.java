package com.sae.sae_juba_antoine_said.modele.acteurs;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Acteur {
    private final int CHEMIN = 230;
    private int range;
    private IntegerProperty x, y, pv;
    private IntegerProperty directionActeur;
    private String id;
    public static int compteur = 0;
    protected Environnement env;
    private Deplacement deplacement;

    public Acteur(int pv, int x, int y,int range,Deplacement deplacement) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.range = range;
        directionActeur = new SimpleIntegerProperty(0);
        this.pv = new SimpleIntegerProperty(pv);
        this.id = "A" + compteur;
        compteur++;
        this.env = Environnement.getEnvironnementInstance();
        this.deplacement=deplacement;
    }

    public void seRaprocher(Acteur a) {
        int dx, dy;
        int distanceX, distanceY;
        if (a != null) {
            distanceX = a.getX() - this.getX();
            distanceY = a.getY() - this.getY();

            if (distanceX < 0) {
                dx = -1;
            } else if (distanceX > 0) {
                dx = 1;
            } else {
                dx = 0;
            }
            if (distanceY < 0) {
                dy = -1;
            } else if (distanceY > 0) {
                dy = 1;
            } else {
                dy = 0;
            }

            int newposX = this.getX() + dx;
            int newposY = this.getY() + dy;

            this.setX(newposX);
            this.setY(newposY);

            // Calculez la distance entre le Bandit et l'Acteur
            double distance = Math.sqrt(Math.pow((this.getX() - a.getX()), 2) + Math.pow((this.getY() - a.getY()), 2));


            double tolerance = 20;
            if (distance <= tolerance) {
                this.setDirectionActeur(5); // la direction 5 c'est pour animation de combat
            }
        }
    }

    public int getCHEMIN() {
        return CHEMIN;
    }

    public IntegerProperty directionActeurProperty() {
        return directionActeur;
    }

    public void setDirectionActeur(int directionActeur) {
        this.directionActeur.set(directionActeur);
    }

    public abstract boolean MonAdversaire(Acteur a);
    public Acteur attaquer(){
        for (Acteur a : this.env.getActeurs()) {
            if (this.MonAdversaire(a) && a.estVivant()) {
                if (this.getY() - this.getRange() <= a.getY() && a.getY() <= this.getY() + this.getRange() &&
                        this.getX() - this.getRange() <= a.getX() && a.getX() <= this.getX() + this.getRange()) {
                    return a;
                }
            }
        }
        return null;
    }

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

    public Environnement getEnv() {
        return env;
    }

    public boolean estVivant() {
        return this.pv.getValue() > 0;
    }

    public void decrementationPv(int pv) {
        this.pv.setValue(this.getPv() - pv);
    }

    public int getPv() {
        return this.pv.getValue();
    }

    public IntegerProperty getPvProperty() {
        return this.pv;
    }

    public int getRange() {
        return this.range;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

}

