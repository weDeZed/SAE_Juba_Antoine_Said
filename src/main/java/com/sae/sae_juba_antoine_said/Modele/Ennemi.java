package com.sae.sae_juba_antoine_said.Modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ennemi extends Acteur {

    private int indiceCheminActuel = 0;
    private IntegerProperty dA;


    public Ennemi(int pv, int x, int y, int vitesse, int range, Environnement env) {
        super(pv, x, y, vitesse, range, env);
        dA = new SimpleIntegerProperty(0);
    }

    @Override
    public void seDeplacer() {

    }

    public int directionActeur() {
        return dA.getValue();
    }

    public IntegerProperty directionACteurProperty (){
        return dA;
    }
    @Override
    public abstract Acteur attaquer();

    @Override
    public void agir() {

    }

    public void move() {
        if (indiceCheminActuel < this.ev.getChemin().size() - 1) {
            indiceCheminActuel++;

            if ( this.ev.getChemin().get(indiceCheminActuel).getX()*16 == this.getX() &&
                    this.ev.getChemin().get(indiceCheminActuel).getY()*16 != this.getY()){
               // System.out.println("1");
                dA.equals(1);
            }else {
               // System.out.println("2");
                dA.equals(2);
            }

            this.setX(this.ev.getChemin().get(indiceCheminActuel).getX() * 16);
            this.setY(this.ev.getChemin().get(indiceCheminActuel).getY() * 16);
        }
    }
}
