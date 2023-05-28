package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public abstract class  Tour {

 IntegerProperty x,y;
 int degatsParSeconde,range;
 private Environnement env;


    public Tour(int x, int y, int degats,int range) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.degatsParSeconde = degats;
        this.range = range;
    }

    public abstract void attaqueEnnemi();


    public Acteur ennemiPlusProche () {
        Acteur acteur = null;
        for (int i = 0; i < env.getActeurs().size(); i++) {
            if ((this.getX() - range <= env.getActeurs().get(i).getY() && env.getActeurs().get(i).getY() <= this.getY() + range) &&
                    (this.getX() - range <= env.getActeurs().get(i).getX() && env.getActeurs().get(i).getX() <= this.getY()+ range)) {
               return env.getActeurs().get(i);
            }
        }

        return acteur;

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
}
