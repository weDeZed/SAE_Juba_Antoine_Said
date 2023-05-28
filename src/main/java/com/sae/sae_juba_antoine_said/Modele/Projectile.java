package com.sae.sae_juba_antoine_said.Modele;

import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Projectile {

    IntegerProperty x,y;
    int vitesse;
    private TourAProjectile tourAProjectile;

    public Projectile(int x, int y,int v,TourAProjectile tourAProjectile) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = v;
    }

    public Projectile(int x, int y,int v) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = v;
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


    public void deplacerVers(Acteur a) {


        System.out.println("Coordonnées  Depart : (" + getX() + ", " + getY() + ")");
        double startX = this.tourAProjectile.getX();
        double startY =this.tourAProjectile.getY();
        double endX = tourAProjectile.ennemiPlusProche().getX();
        double endY = tourAProjectile.ennemiPlusProche().getY();



        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setFromX(startX);
        transition.setFromY(startY);
        transition.setToX(endX);
        transition.setToY(endY);


        transition.play();

        System.out.println("Nouvelles coordonnées : (" + getX() + ", " + getY() + ")");

    }

    public void seDeplacer(Acteur a) {
        //Acteur a = this.tourAProjectile.ennemiPlusProche();
        int dx, dy;
        int distanceX,distanceY;
        System.out.println("Coordonnées  départ : (" + getX() + ", " + getY() + ")");

        if (a != null){

            distanceX = a.getX() - this.getX();
            distanceY = a.getY() - this.getY();

            if (distanceX < 0){
                dx = distanceX +(distanceX-1);
            }else {
                dx = distanceX - (distanceX-1);
            }

            if (distanceY < 0){
                dy = distanceY +(distanceY-1);
            }else {
                dy = distanceY - (distanceY-1);
            }


                int newposX = this.getX() + (this.getVitesse()*dx);
                int newposY = this.getY() + (this.getVitesse()*dy);
                this.setX(newposX);
                this.setY(newposY);
            }
        System.out.println("Nouvelles coordonnées : (" + getX() + ", " + getY() + ")");

    }

    private double calculerDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
