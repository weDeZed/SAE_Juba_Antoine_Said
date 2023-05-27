package com.sae.sae_juba_antoine_said.Modele;

public class TroopTour extends Tour {
    public TroopTour(int x, int y, int degats, int range, Environnement environnement) {
        super(x, y, degats, range, environnement);
    }

    public void attaqueEnnemi() {
        int[][] map = environnement.getMap();
        int nbEnnemi = 0;
        for (Acteur a : environnement.getActeurs()) {
            if (a instanceof Ennemi) {
                nbEnnemi++;
            }
        }
        int placement = 0;
        //System.out.println("TOUR getX "+getX() +" Tour GetY "+getY());
        for (int i = 0; i < 1; i++) {
            int x = getX()/16;
            int y = getY()/16;
            if (x > 0 && map[x - 1][y] == 1427) {
                x = x - 1;
                y = 27 + placement;
            } else if (x < map.length - 1 && map[x + 1][y] == 1427) {
                x = x + 1;
                y = 27 + placement;
            } else if (y > 0 && map[x][y - 1] == 1427) {
                y = y - 1;
                x = 56 + placement;
            } else if (y < map[0].length - 1 && map[x][y + 1] == 1427) {
                y = y + 1;
                x = 56 + placement;
            } else {
                System.out.println("case pas trouvé");
                break;
            }

            placement += 2;
            environnement.ajouterActeur(new Guerrier(20, x * 16, y * 16, environnement));
        }


    }
}
