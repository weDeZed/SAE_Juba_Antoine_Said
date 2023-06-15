package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Guerrier;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

import javax.crypto.spec.PSource;

public class TroopTour extends Tour {
    private int chemin;
    private  int temps;

    public TroopTour(int x, int y, int degats,int range, Environnement environnement) {
        super(x, y, degats,150,range, environnement);
        chemin = 230;
        temps=0;
    }

    public void attaqueEnnemi() {
        temps++;
        int[][] map = this.env.getMap();
        int nbEnnemi = 0;
        for (Acteur a : env.getActeurs()) {
            if (a instanceof Ennemi) {
                nbEnnemi++;
            }
        }
        if(temps%20==0) {
            System.out.println("temps "+temps);
            int placement = 0;
            for (int i = 0; i < 1; i++) {
                int x = getX() / 32;
                int y = getY() / 32;
                if (x > 1 && map[x - 2][y] == chemin) {
                    x = x - 2;
                    y = y + placement;
                } else if (x < map.length - 2 && map[x + 2][y] == chemin) {
                    x = x + 2;
                    y = y + placement;
                } else if (y > 1 && map[x][y - 2] == chemin) {
                    y = y - 2;
                    x = x + placement;
                } else if (y < map[0].length - 2 && map[x][y + 2] == chemin) {
                    y = y + 2;
                    x = x + placement;
                } else {
                    System.out.println("case pas trouvé");
                    break;
                }
                placement += 1 + 1 / 2;
                env.ajouterActeur(new Guerrier(x * 32, y * 32, env));
            }
        }


    }
}
