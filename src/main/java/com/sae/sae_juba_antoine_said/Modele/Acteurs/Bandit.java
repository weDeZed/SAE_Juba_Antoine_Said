package com.sae.sae_juba_antoine_said.Modele.Acteurs;


import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Bandit extends Ennemi {

    public Bandit(int x, int y, int vitesse, Environnement ev) {

        super(100, x, y, vitesse, 100, ev);
    }


    @Override
    public Acteur attaquer() {
        for (Acteur a : this.env.getActeurs()) {
            if (a instanceof Ally && a.estVivant()){
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
        Acteur a = this.attaquer();
        if (a!=null){
            this.seDeplacer();
            if (a.getPv()>0) {
                a.decrementationPv(10);
                System.out.println("jusqu'a tué ennemie");
            }
        }else {
            System.out.println("sinon move ");
            move();
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

