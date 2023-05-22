package com.sae.sae_juba_antoine_said.Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tour {

 IntegerProperty x,y;
 int degatsParSeconde,range;

    public Tour(int x, int y, int degats,int range) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.degatsParSeconde = degats;
        this.range = range;
    }

    public void attaqueEnnemi(){

    }

    public void ennemiPlusProche (Acteur actProche) {

        if ((this.getX() - range <= actProche.getY() && actProche.getY() <= this.getY() + range) &&
                (this.getX() - range <= actProche.getX() && actProche.getX() <= this.getY()+ range)) {
            //return actProche;
        }

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
