package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

import java.util.Random;

public class DeplacementAlly implements Deplacement{

    @Override
    public void seDeplacer(Environnement env, Acteur acteur) {
        int x = acteur.getX() / 32;
        int y = acteur.getY() / 32;
        boolean peutDeplacer = false;
        Random rand = new Random();
        while (!peutDeplacer) {
            int direction = rand.nextInt(4);
            acteur.setDirectionActeur(direction);
            switch (direction) {
                case 0: // Haut
                    if (dansTerrain(x, y) && env.getMap()[x][y - 1] == acteur.getCHEMIN()) {
                        y = y - 1;
                        acteur.setDirectionActeur(1);

                        peutDeplacer = true;
                    }
                    break;
                case 1: // Droit
                    if (dansTerrain(x, y) && env.getMap()[x + 1][y] == acteur.getCHEMIN()) {
                        x = x + 1;
                        acteur.setDirectionActeur(2);
                        peutDeplacer = true;
                    }
                    break;
                case 2: // Bas
                    if (dansTerrain(x, y) && env.getMap()[x][y + 1] == acteur.getCHEMIN()) {
                        y = y + 1;
                        acteur.setDirectionActeur(3);
                        peutDeplacer = true;
                    }
                    break;
                case 3: // Gauche
                    if (dansTerrain(x, y) && env.getMap()[x - 1][y] == acteur.getCHEMIN()) {
                        x = x - 1;
                        acteur.setDirectionActeur(4);
                        peutDeplacer = true;
                    }
                    break;
            }
        }
        if (peutDeplacer) {
            acteur.setX(x * 32);
            acteur.setY(y * 32);
        }
    }
    public boolean dansTerrain(int x, int y) {
        return y >= 0 && y < 90 && x >= 0 && x < 90;
    }
}
