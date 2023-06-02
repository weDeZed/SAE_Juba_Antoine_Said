package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
        this.vitesse = 10;
        id="P"+ compteur;
        compteur++;
    }
    public void lancerProjectile(Acteur a) {

        int dx, dy;
        int distanceX,distanceY;


            distanceX = a.getX() - this.getX();
            distanceY = a.getY() - this.getY();

            if (distanceX < 0){
                dx = distanceX -(distanceX+1);
            }else {
                dx = distanceX - (distanceX-1);
            }

            if (distanceY < 0){
                dy = distanceY -(distanceY+1);
            }else {
                dy = distanceY - (distanceY-1);
            }

                int newposX = this.getX() + (this.getVitesse()*dx);
                int newposY = this.getY() + (this.getVitesse()*dy);



                this.setX(newposX);
                this.setY(newposY);

        System.out.println("projectile bouge");

        System.out.println("PV guerrier : " + a.getPv());
               if (atteintActeur(a) == true){
                   a.decrementationPv(10);
               }
        System.out.println("PV guerrier : " + a.getPv());

            }

    public String getId() {
        return id;
    }

    public boolean atteintActeur(Acteur a) {
        boolean atteint = false;

        if(((getX()+38 >= a.getX()) && getX() <= a.getX()+32) && (getY() >= a.getY()) && (getY()+13 <= a.getY()+32)){
            env.removeProjectile(this);
            System.out.println("Acteur atteint");
            atteint= true;
        }
        return  atteint;
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
