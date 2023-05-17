package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {
    private final int CHEMIN = 1427;
    private int vitesse, range, pv;
    private IntegerProperty x, y;

    private String id;
    public static int compteur = 0;

    public Acteur(int pv, int x, int y, int vitesse, int range) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = vitesse;
        this.range = range;
        this.pv = pv;
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

    /*public void seDeplacer(int destinationX, int destinationY) {
        double dx = (destinationX - getX());
        double dy = (destinationY - getY());
        double distance = (Math.sqrt(dx * dx + dy * dy));
        double directionX = dx / distance;
        double directionY = dy / distance;
        x.setValue(getX() + (directionX * vitesse));
        y.setValue(getY() + (directionY * vitesse));

    }*/

    public abstract void seDeplacer();

    public boolean collisionDroitV(Acteur acteur, Environnement environnement) {
        //System.out.println("X "+acteur.getX()+" Y "+acteur.getY());
        if (environnement.getMap()[acteur.getX() / 16 + 16][acteur.getY() / 16] != CHEMIN) {
            System.out.println("CollisionDroit " + environnement.getMap()[acteur.getX() / 16 + 16][acteur.getY() / 16]);
            return true;
        }
        System.out.println("X " + acteur.getX() + " Y " + acteur.getY());
        System.out.println("pas CollisionDroit " + environnement.getMap()[acteur.getX() / 16 + 16][acteur.getY() / 16]);
        return false;
    }

    public boolean collisionDroitH(Acteur acteur, Environnement environnement) {
        if (environnement.getMap()[acteur.getX() / 16][acteur.getY() /16 + 16] != CHEMIN) {
            System.out.println("collision droith" + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 + 16]);
            return true;
        }
        return false;
    }

    public boolean collisionGauche(Acteur acteur, Environnement environnement) {
        if (environnement.getMap()[acteur.getX() / 16 - 16][getY() / 16] != CHEMIN) {
            //System.out.println("X "+acteur.getX()+" Y "+acteur.getY());
            System.out.println("collision Gauch " + environnement.getMap()[acteur.getX() / 16 - 16][getY() / 16]);
            return true;
        }
        System.out.println("X " + acteur.getX() + " Y " + acteur.getY());
        System.out.println("pas collisionGauche " + environnement.getMap()[acteur.getX() / 16 - 16][getY() / 16]);
        return false;
    }

    public boolean collisionGaucheH(Acteur acteur, Environnement environnement) {
        if (environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16] != CHEMIN) {
            System.out.println("CollisionGaucheH " + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16]);
            return true;
        }
        return false;
    }

    public boolean collisionDevant(Acteur acteur, Environnement environnement) {
        if (environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16] != CHEMIN) {
            //System.out.println("X "+acteur.getX()+" Y "+acteur.getY());
            System.out.println("collision devant " + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16]);
            return true;
        }
        System.out.println("X " + acteur.getX() + " Y " + acteur.getY());
        System.out.println("pas collision devant " + environnement.getMap()[acteur.getX() / 16][acteur.getY() / 16 - 16]);
        return false;
    }


}
