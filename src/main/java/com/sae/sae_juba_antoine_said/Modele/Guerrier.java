package com.sae.sae_juba_antoine_said.Modele;

public class Guerrier extends Ally {


    public Guerrier(int pv, int x, int y,Environnement env) {
        super(pv, x, y, 20, 0,env);
    }

    @Override
    public void seDeplacer() {
        Acteur a = this.attaquer();
        int dx, dy;
        int distanceX,distanceY;

        if (a != null){
            distanceX = a.getX() - this.getX();
            distanceY = a.getY() - this.getY();

            if (distanceX < 0){
                dx = distanceX +(distanceX-1);
            }else {
                dx = distanceX - (distanceX-1);
            }

            if (distanceY < 0){
                dy = distanceY +(distanceY-1);
            }else {
                dy = distanceY - (distanceY-1);
            }
            while (a.estVivant()) {
                int newposX = this.getX() + (this.getVitesse()*dx);
                int newposY = this.getY() + (this.getVitesse()*dy);
                this.setX(newposX);
                this.setY(newposY);
            }
        }else {

            this.setX(this.getX()+this.getVitesse());
        }
    }

    public Acteur attaquer() {
        for (Acteur a :getEnv().getActeurs()) {
            if (a instanceof Ennemi && a.estVivant()) {
                if (this.getY() - this.getRange() <= a.getY() && a.getY() <= this.getY() + this.getRange() &&
                        this.getX() - this.getRange() <= a.getX() && a.getX() <= this.getX() + this.getRange()) {
                    return a;
                }
            }
        }
        return null;
    }
    @Override
    public void agir() {
        //test
        Acteur a = this.attaquer();
        this.seDeplacer();
        if (a.getPv() <= 10){
            a.meurt();
        }else {
            a.decrementationPv(10);
        }
        this.decrementationPv(1);
    }

}
