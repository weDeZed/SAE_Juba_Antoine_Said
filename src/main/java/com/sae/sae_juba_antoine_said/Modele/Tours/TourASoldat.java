package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Guerrier;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;

public class TourASoldat extends Tour {
    private int chemin;
    private int temp;

    public TourASoldat(int x, int y, int range, Environnement environnement) {
        super(x, y, 150, range, environnement);
        chemin = 230;
        temp = 0;
    }

    public void attaqueEnnemi() {
        int[][] map = this.env.getMap();
        temp++;
        //System.out.println("nbTour "+nbTour);
        if (temp % 12 == 0) {

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
                    break;
                }
                placement += 1 + 1 / 2;
                env.ajouterActeur(new Guerrier(x * 32, y * 32, env));
            }
        }

    }
}
