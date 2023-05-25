package com.sae.sae_juba_antoine_said.Modele;

public class TroopTour extends Tour{
    public TroopTour(int x, int y, int degats, int range,Environnement environnement) {
        super(x, y, degats, range,environnement);
    }
    public void attaqueEnnemi(){
        int [][]map= environnement.getMap();
        int nbEnnemi=0;
        for (Acteur a:environnement.getActeurs()) {
            if (a instanceof Ennemi){
                nbEnnemi++;
            }
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("dans boucle troopTour");
            boolean trouveCase = false;
            int x = 56;
            int y = 27;

            while (!trouveCase) {
                if (x > 0 && map[x - 1][y] == 1427) {
                    //System.out.println(" 1v " + map[x - 1][y]);
                    x = x - 1;
                    trouveCase = true;
                } else if (x < map.length - 1 && map[x + 1][y] == 1427) {
                    //System.out.println(" 2v " + map[x + 1][y]);
                    x = x + 1;
                    trouveCase = true;
                } else if (y > 0 && map[x][y - 1] == 1427) {
                    //System.out.println(" 3v " + map[x][y - 1]);
                    y = y - 1;
                    trouveCase = true;
                } else if (y < map[0].length - 1 && map[x][y + 1] == 1427) {
                    //System.out.println(" 4v " + map[x][y + 1]);
                    y = y + 1;
                    trouveCase = true;
                } else {
                    System.out.println("case pas trouvé");
                    break;
                }
            }

            System.out.println("x " + x + " y " + y);
            environnement.ajouterActeur(new Guerrier(20, x*16, y*16,environnement));
        }


    }
}
