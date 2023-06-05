package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class Guerrier extends Ally {
    private int direction;
    private int startX;
    private int startY;


    public Guerrier(int pv, int x, int y, Environnement env) {
        super(pv, x, y, 20, 10, env);
        direction = 1;
        this.startX = x / 32;
        this.startY = y / 32;
    }

    @Override
    public void seDeplacer() {
        Acteur a = this.attaquer();
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
                int newposX = this.getX() + (this.getVitesse() * dx);
                int newposY = this.getY() + (this.getVitesse() * dy);
                this.setX(newposX);
                this.setY(newposY);

        }
    }


    public Acteur attaquer() {
        for (Acteur a : this.env.getActeurs()) {
            if (a instanceof Ennemi && a.estVivant()) {
                if (this.getY() - this.getRange() <= a.getY() && a.getY() <= this.getY() + this.getRange() &&
                        this.getX() - this.getRange() <= a.getX() && a.getX() <= this.getX() + this.getRange()) {
                    System.out.println("un ennemie ");
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public void agir() {
        Acteur a = this.attaquer();
        this.seDeplacer();
        if (a.getPv() <= 10) {
            a.meurt();
        } else {
            a.decrementationPv(10);
        }
        this.decrementationPv(1);
    }


    public void marcherSurChemin() {
        int x = getX() / 32;
        int y = getY() / 32;
        boolean peutDeplacer = false;

        if (direction == 1) { // marcher droit/en bas
            if (x < 90 && x < startX + getRange() && this.env.getMap()[x + 1][y] == getCHEMIN()) {
                x = x + 1;
                peutDeplacer = true;
            } else if (y < 90 && y < startY + getRange() && this.env.getMap()[x][y + 1] == getCHEMIN()) {
                y = y + 1;
                peutDeplacer = true;
            } else {
                direction = -1; // change la direction
            }
        } else if (direction == -1) { // déplacer gauche / haut
            if (x > 0 && x > startX - getRange() && this.env.getMap()[x - 1][y] == getCHEMIN()) {
                x = x - 1;
                peutDeplacer = true;
            } else if (y > 0 && y > startY - getRange() && this.env.getMap()[x][y - 1] == getCHEMIN()) {
                y = y - 1;
                peutDeplacer = true;
            } else {
                direction = 1; //cnage la direction
            }
        }

        if (peutDeplacer) {
            setX(x * 32);
            setY(y * 32);
        }
    }
}
