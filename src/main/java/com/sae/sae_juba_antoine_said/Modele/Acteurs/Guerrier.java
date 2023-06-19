package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

import java.util.Random;

public class Guerrier extends Ally {

    private int startX;
    private int startY;
    int temps;

    public Guerrier(int x, int y, Environnement env) {
        super(100, x, y, 20, 100, env);
        this.startX = x / 32;
        this.startY = y / 32;
        temps = 0;
    }

    public Acteur attaquer() {
        for (Acteur a : this.env.getActeurs()) {
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
        temps++;
        Acteur a = this.attaquer();
        if (a != null) {
            this.seDeplacer(a);
            if (a.getPv() <= 10) {
                env.suppActeur(a);
                env.ajoutePiece(5);
            } else {
                a.decrementationPv(7);
            }
        } else {
            if (temps % 5 == 0) {
                marcherSurChemin();
            }
        }
    }

    public void marcherSurChemin() {
        int x = getX() / 32;
        int y = getY() / 32;
        boolean peutDeplacer = false;
        Random rand = new Random();

        while (!peutDeplacer) {
            int direction = rand.nextInt(4);
            setDirectionActeur(direction);
            switch (direction) {
                case 0: // Haut
                    if (dansTerrain(x, y) && this.env.getMap()[x][y - 1] == getCHEMIN()) {
                        y = y - 1;
                        this.setDirectionActeur(1);

                        peutDeplacer = true;
                    }
                    break;
                case 1: // Droit
                    if (dansTerrain(x, y) && this.env.getMap()[x + 1][y] == getCHEMIN()) {
                        x = x + 1;
                        this.setDirectionActeur(2);
                        peutDeplacer = true;
                    }
                    break;
                case 2: // Bas
                    if (dansTerrain(x, y) && this.env.getMap()[x][y + 1] == getCHEMIN()) {
                        y = y + 1;
                        this.setDirectionActeur(3);
                        peutDeplacer = true;
                    }
                    break;
                case 3: // Gauche
                    if (dansTerrain(x, y) && this.env.getMap()[x - 1][y] == getCHEMIN()) {
                        x = x - 1;
                        this.setDirectionActeur(4);
                        peutDeplacer = true;
                    }
                    break;
            }
        }
        if (peutDeplacer) {
            setX(x * 32);
            setY(y * 32);
        }
    }

    public boolean dansTerrain(int x, int y) {
        return y >= 0 && y < 90 && x >= 0 && x < 90;
    }
}
