package com.sae.sae_juba_antoine_said.Modele;

import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Projectile {

    IntegerProperty x,y;
    int vitesse;
    Environnement env;
    String id;
    static int compteur = 0;



    public Projectile(int x, int y,Environnement environnement) {

        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = environnement;
        this.vitesse = 5;
        id="P"+ compteur;
        compteur++;
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

                int newposX = this.getX() + (this.getVitesse()*dx);
                int newposY = this.getY() + (this.getVitesse()*dy);



                this.setX(newposX);
                this.setY(newposY);

               atteintActeur(a);
            }

    public String getId() {
        return id;
    }

    public void atteintActeur(Acteur a) {

                //System.out.println(" p X " + getX() +" p Y " + "" +getY());
                //System.out.println("a X " + a.getX() +"" + " a Y" +a.getY());
               // System.out.println("Projectile x : " + getX() + " Projectile y : " + getY());
               // System.out.println("Acteur x : " + a.getX() + " Acteur x + 32 : " + (a.getX()+32));



                //System.out.println("Acteur Y : " + a.getY() + " Acteur Y + 32 : " + (a.getY()+32));

                if ((getX() >= a.getX()) && getX() <= a.getX()+32) {
                   // System.out.println("Position correct en X ");
                }
                if((getY() >= a.getY()) && (getY() <= a.getY()+32)){
                   // System.out.println("Position correct en Y ");
                }
                        if(((getX()+38 >= a.getX()) && getX() <= a.getX()+32) && (getY() >= a.getY()) && (getY()+13 <= a.getY()+32)){
                            env.removeProjectile(this);
                            System.out.println("Projectile removed");
                            System.out.println("ID Proj : " + getId());
                        }


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
