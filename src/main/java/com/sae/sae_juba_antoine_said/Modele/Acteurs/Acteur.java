package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public abstract class Acteur {
    private final int CHEMIN = 230;
    private int vitesse, range;
    private IntegerProperty x, y, pv;
    private IntegerProperty directionActeur;
    private int dx, dy;// direction
    private String id;
    public static int compteur = 0;
    Environnement env;


    public Acteur(int pv, int x, int y, int vitesse, int range, Environnement env) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = vitesse;
        this.range = range;
        directionActeur = new SimpleIntegerProperty(0);
        this.pv = new SimpleIntegerProperty(pv);
        this.id = "A" + compteur;
        compteur++;
        this.env = env;
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

    public int getVitesse() {
        return this.vitesse;
    }

    public void meurt() {
        this.pv.equals(0);
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRange() {
        return this.range;
    }


    public void seDeplacer(Acteur a) {
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
            int newposX = this.getX() + (this.getVitesse() * dx);
            int newposY = this.getY() + (this.getVitesse() * dy);

            if (newposX >= 0 && newposX < env.getMap().length && newposY >= 0 && newposY < env.getMap()[0].length && env.getMap()[newposX][newposY] == env.getCHEMIN()) {
                this.setX(newposX);
                this.setY(newposY);
            }

            // Calculez la distance entre le Bandit et l'Acteur
            double distance = Math.sqrt(Math.pow((this.getX() - a.getX()), 2) + Math.pow((this.getY() - a.getY()), 2));
            //System.out.println("distance " + distance);

            double range = 20;
            if (distance <= range) {
                System.out.println("dans deplacement dir 5 ");
                this.setDirectionActeur(5);
            }
        }
    }

    public int getCHEMIN() {
        return CHEMIN;
    }

    public int getDirectionActeur() {
        return directionActeur.get();
    }

    public IntegerProperty directionActeurProperty() {
        return directionActeur;
    }

    public void setDirectionActeur(int directionActeur) {
        this.directionActeur.set(directionActeur);
    }

    public void tirerDirection() {
        Random random = new Random();
        int randomInt = random.nextInt(3);
        dx = randomInt - 1;
        if (dx == 0) {
            randomInt = random.nextInt(2) - 1;
            if (randomInt == 0) {
                dy = -1;
            } else {
                dy = 1;
            }
        } else {
            dy = random.nextInt(3) - 1;
        }
    }

}











































/*
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

 */

























