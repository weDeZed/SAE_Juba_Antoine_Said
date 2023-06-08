package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
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
        this.vitesse = 40;
        id="P"+ compteur;
        compteur++;
    }

    public void lancerProjectile(Acteur a) {

        double distanceX = a.getX() - this.getX();
        double distanceY = a.getY() - this.getY();

        // Calcule la distance total
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


        double directionX = distanceX / totalDistance;
        double directionY = distanceY / totalDistance;

        // Calculer les nouvelles positions
        double newposX = this.getX() + (this.getVitesse() * directionX);
        double newposY = this.getY() + (this.getVitesse() * directionY);


        this.setX((int) newposX);
        this.setY((int) newposY);



        if (atteintActeur(a) == true){
          //  System.out.println("PV ACT AVANT " + a.getPv());
            a.decrementationPv(25);
            //System.out.println("C BON");
           // System.out.println("PV ACT APRES "+a.getPv());
        }

    }

    public String getId() {
        return id;
    }

    public boolean atteintActeur(Acteur a) {
        boolean atteint = false;
        if(((getX()+38 >= a.getX()) && getX() <= a.getX()+32) && (getY() >= a.getY()) && (getY()+13 <= a.getY()+32)){
            env.removeProjectile(this);
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
