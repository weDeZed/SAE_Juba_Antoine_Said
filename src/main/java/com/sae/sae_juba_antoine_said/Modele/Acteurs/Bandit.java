package com.sae.sae_juba_antoine_said.Modele.Acteurs;


import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Bandit extends Ennemi {

    public Bandit(int x, int y, int vitesse, Environnement ev) {

        super(90, x, y, vitesse, 10, ev);
    }


    @Override
    public Acteur attaquer() {
        for (Acteur a : this.env.getActeurs()) {
            if (a instanceof Ally && a.estVivant()) {
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

        if (a.estVivant()) {
            a.decrementationPv(10);
            System.out.println("il n'est pas mort");
        }
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
                dx = distanceX -(distanceX+1);
            }else {
                dx = distanceX - (distanceX-1);
            }

            if (distanceY < 0){
                dy = distanceY - (distanceY+1);
            }else {
                dy = distanceY - (distanceY-1);
            }

            int newposX = this.getX() + (this.getVitesse()*dx);
            int newposY = this.getY() + (this.getVitesse()*dy);
            this.setX(newposX);
            this.setY(newposY);
        }
    }
}

