package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
        double distance = calculerDistance(getX(), getY(), a.getX(), a.getY());
        System.out.println("Distance à parcourir : " + distance);

        // Déplacer l'objet jusqu'à la destination
        while (getX() != a.getX() || getY()!= a.getY()) {
            // Calculer le vecteur de déplacement
            double verteurX = a.getX() - getX();
            double verteurY = a.getY() - getY();

            // Calculer le rapport entre la distance à parcourir et la distance totale
            double ratio = distance / calculerDistance(getX(), getY(), a.getX(), a.getY());

            // Mettre à jour les coordonnées de l'objet
           // x += verteurX * ratio;
           // y += verteurY * ratio;



            // Afficher les nouvelles coordonnées de l'objet
            System.out.println("Nouvelles coordonnées : (" + x + ", " + y + ")");
        }

        System.out.println("Objet arrivé à la destination !");
    }

    private double calculerDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
