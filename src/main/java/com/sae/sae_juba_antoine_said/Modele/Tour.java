package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Tour {

 IntegerProperty x,y;
 int degatsParSeconde,range;
 Environnement environnement;

    public Tour(int x, int y, int degats,int range ,Environnement environnement) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.degatsParSeconde = degats;
        this.range = range;
        this.environnement=environnement;
    }

    public void attaqueEnnemi(){


    }


    public void lancerProjectile (){

    }
    public Acteur ennemiPlusProche (ObservableList<Acteur> act) {
        Acteur acteur = null;
        for (int i = 0; i < act.size(); i++) {
            if ((this.getX() - range <= act.get(i).getY() && act.get(i).getY() <= this.getY() + range) &&
                    (this.getX() - range <= act.get(i).getX() && act.get(i).getX() <= this.getY()+ range)) {
               return act.get(i);
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
