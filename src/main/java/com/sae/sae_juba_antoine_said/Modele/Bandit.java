package com.sae.sae_juba_antoine_said.Modele;

import java.io.IOException;

public class Bandit extends Ennemi {

    private Environnement ev;

    public Bandit(int x, int y, int vitesse, Environnement env) throws IOException {
        super(90, x, y, vitesse, 4, env);
        this.ev = env;
    }


    @Override
    public Acteur attaquer() {
        for (Acteur a : ev.getActeurs()) {
            if (a instanceof Ally && a.estVivant()) {
                if (this.getY() - this.getRange() <= a.getY() && a.getY() <= this.getY() + this.getRange() &&
                        this.getX() - this.getRange() <= a.getX() && a.getX() <= this.getX() + this.getRange()) {
                    System.out.println("bandit attrapé un guerrier");
                    return a;
                }
            }
        }
        return null;
    }



    @Override
    public void seDeplacer(Acteur a) {
        //Acteur a = this.attaquer();
        int dx, dy;
        int distanceX, distanceY;

        if (a != null) {

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

            while (a.estVivant()) {
                System.out.println("Guerrier est vivant");
                System.out.println();
                int newposX = this.getX() + (this.getVitesse() * dx);
                int newposY = this.getY() + (this.getVitesse() * dy);
                this.setX(newposX);
                this.setY(newposY);
            }
            System.out.println("Guerrier est mort");
        } else {

            this.setX(this.getX() + this.getVitesse());
        }
    }
    @Override
    public void agir() {
        //test
        Acteur a = this.attaquer();
        this.seDeplacer(a);

            if (a.getPv() <= 10) {
                System.out.println("bandit a teué G");
                a.meurt();
            } else {
                a.decrementationPv(10);
            }
            this.decrementationPv(1);
    }
}

