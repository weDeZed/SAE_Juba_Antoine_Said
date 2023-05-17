package com.sae.sae_juba_antoine_said.Modele;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Environnement {

    private int x, y;
    private int[][] map;
    private ArrayList<Acteur> acteurs;


    public Environnement(int x, int y) {
        this.x = x;
        this.y = y;
        this.map = new int[x][y];
        this.acteurs = new ArrayList<>();
    }

    public void readMap() throws IOException {
        File file = new File("/Users/saidkamalshinwari/Downloads/BUT-S1/S2/IHM/GithubProject/src/main/java/com/sae/sae_juba_antoine_said/Ressources/map1");
        BufferedReader terrain = new BufferedReader(new FileReader(file));
        String ligne;
        String[] tout_ligne;

        try {
            int x = 0;
            while ((ligne = terrain.readLine()) != null) {
                tout_ligne = ligne.split(",");
                for (int y = 0; y < tout_ligne.length; y++) {
                    if (!tout_ligne[y].trim().isEmpty()) {
                        this.map[x][y] = Integer.parseInt(tout_ligne[y].trim());
                    }
                }
                x++;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        try {
            terrain.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int[][] getMap() {
        return map;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public ArrayList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        this.acteurs.add(a);
    }

    public boolean dansTerrain(int x, int y) {
        return getMap()[x][y]==1427;
    }

    public void suivereLeChemin(){
        List<Point> chemin = new ArrayList<>();
        chemin.add(new Point(44*16, -32));

        chemin.add(new Point(23*16, -32));


        int[] indices = new int[getActeurs().size()];
        for (int i = 0; i < getActeurs().size(); i++) {
            Point positionActuelle = new Point(acteurs.get(i).getX(), acteurs.get(i).getY());
            int indiceActuel = indices[i];
            Point pointDestination = chemin.get(indiceActuel);
            double dx = pointDestination.getX() - positionActuelle.getX();
            double dy = pointDestination.getY() - positionActuelle.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance <= 0) {
                indices[i] = (indiceActuel + 1) % chemin.size();
            } else {
                double directionX = dx / distance;
                double directionY = dy / distance;
                double nouveauX = positionActuelle.getX() + directionX * 3;
                double nouveauY = positionActuelle.getY() + directionY * 3;
                acteurs.get(i).setX((int)nouveauX);
                acteurs.get(i).setY((int)nouveauY);
            }
        }

    }
}

