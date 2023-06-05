package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tour {

 IntegerProperty x,y;
 int degatsParSeconde,range;
  Environnement env;


    public Tour(int x, int y, int degats,int range,Environnement env) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.degatsParSeconde = degats;
        this.range = range;
        this.env=env;
    }

    public void attaqueEnnemi(){


    }
    public ObservableList<Acteur> ennemiPlusProche () {

        ObservableList<Acteur> result = FXCollections.observableArrayList();


        for (int i = 0; i < env.getActeurs().size(); i++) {
            if (env.getActeurs().get(i) instanceof Ennemi) {
                if ((this.getX() - range <= env.getActeurs().get(i).getY() && env.getActeurs().get(i).getY() <= this.getY() + range) &&
                        (this.getX() - range <= env.getActeurs().get(i).getX() && env.getActeurs().get(i).getX() <= this.getY() + range)) {

                    result.add(env.getActeurs().get(i));
                    System.out.println("Acteur : " + env.getActeurs().get(i) + " ajouté ");
                }
            }
        }
        return result;
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
    public void creeProjectile(){

    }
}
