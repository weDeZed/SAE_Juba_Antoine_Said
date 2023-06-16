package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Tour {

    IntegerProperty x, y;
    int degatsParSeconde, range;
    Environnement env;
    private  int prix;


    public Tour(int x, int y, int prix, int range, Environnement env) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.range = range;
        this.env = env;
        this.prix=prix;
    }

    public abstract void attaqueEnnemi();


    // Méthode pour trouver l'ennemi le plus proche dans le rayon d'attaque de la tour
    public Acteur ennemiPlusProche() {

        Acteur ennemiLePlusProche = null;
        double distanceMin = Double.MAX_VALUE;

        for (int i = 0; i < env.getActeurs().size(); i++) {
            if (env.getActeurs().get(i) instanceof Ennemi) {
                double dx = this.getX() - env.getActeurs().get(i).getX();
                double dy = this.getY() - env.getActeurs().get(i).getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                // Si l'ennemi est dans le rayon d'attaque et plus proche que l'ennemi précédent, il devient l'ennemi le plus proche
                if (distance <= range && distance < distanceMin) {
                    ennemiLePlusProche = env.getActeurs().get(i);
                    distanceMin = distance;
                }
            }
        }
        return ennemiLePlusProche;
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

    public int getDegatsParSeconde() {
        return degatsParSeconde;
    }

    public void setDegatsParSeconde(int degatsParSeconde) {
        this.degatsParSeconde = degatsParSeconde;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
    public Environnement getEnvironment() {
        return env;
    }

}