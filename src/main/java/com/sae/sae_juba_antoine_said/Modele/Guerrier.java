package com.sae.sae_juba_antoine_said.Modele;

public class Guerrier extends Ally {


    public Guerrier(int pv, int x, int y, Environnement env) {
        super(pv, x, y, 20, 200, env);
    }

    @Override
    public void seDeplacer(Acteur a) {
        //Acteur a = this.attaquer();
        int dx, dy;
        int distanceX, distanceY;

        if (a != null) {
            System.out.println("attrapé ennemie ");
            distanceX = a.getX() - this.getX();
            distanceY = a.getY() - this.getY();

            if (distanceX < 0) {
                dx = distanceX + (distanceX - 1);
            } else {
                dx = distanceX - (distanceX - 1);
            }

            if (distanceY < 0) {
                dy = distanceY + (distanceY - 1);
            } else {
                dy = distanceY - (distanceY - 1);
            }
            while (a.estVivant() && getPv() > 0) {
                System.out.println(" pv " + getPv());
                System.out.println(" bandit est vivant ");
                int newposX = this.getX() + (this.getVitesse() * dx);
                int newposY = this.getY() + (this.getVitesse() * dy);
                this.setX(newposX);
                this.setY(newposY);
            }
        } else {
            this.setX(this.getX() - this.getVitesse());
        }
    }

    public Acteur attaquer() {
        for (Acteur a : getEnv().getActeurs()) {
            if (a instanceof Ennemi && a.estVivant()) {
                //System.out.println("x " + getX() + "  y " + getY());
                if (this.getY() - this.getRange() <= a.getY() && a.getY() <= this.getY() + this.getRange() &&
                        this.getX() - this.getRange() <= a.getX() && a.getX() <= this.getX() + this.getRange()) {
                    System.out.println("un ennemie ");
                    return a;
                }
            }
        }
        System.out.println("pas des ennemie ");
        return null;
    }

    @Override
    public void agir() {
        Acteur a = this.attaquer();
        this.seDeplacer(a);
        if (a.getPv() <= 10) {
            a.meurt();
        } else {
            a.decrementationPv(10);
        }


        this.decrementationPv(1);
    }

}
