package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tour {

 int x,y;
 int degatsParSeconde,range;

    public Tour(int x, int y, int degats,int range) {
        this.x = x;
        this.y = y;
        this.degatsParSeconde = degats;
        this.range = range;
    }

    public void attaqueEnnemi(){

    }

    public void ennemiPlusProche (Acteur actProche) {

        if ((this.x - range <= actProche.getY() && actProche.getY() <= this.y + range) &&
                (this.x - range <= actProche.getX() && actProche.getX() <= this.x + range)) {
            //return actProche;
        }

    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
