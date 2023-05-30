package com.sae.sae_juba_antoine_said.Modele;

import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Projectile {

    IntegerProperty x,y;
    int vitesse;
    Environnement env;


    public Projectile(int x, int y,Environnement environnement) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = environnement;
        this.vitesse = 5;
    }
    public void deplacerVers(Acteur a) {

        int dx, dy;
        int distanceX,distanceY;


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
            //double length = Math.sqrt(dx*dx+dy*dy);
            //dx /=length;
            //dy /=length;
                int newposX = this.getX() + (this.getVitesse()*dx);
                int newposY = this.getY() + (this.getVitesse()*dy);



                this.setX(newposX);
                this.setY(newposY);
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
}
