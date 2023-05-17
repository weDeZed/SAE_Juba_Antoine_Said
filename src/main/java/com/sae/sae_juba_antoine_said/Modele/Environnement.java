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
    private ArrayList<Tour> tours;


    public Environnement(int x, int y) {
        this.x = x;
        this.y = y;
        this.map = new int[x][y];
        this.acteurs = new ArrayList<>();
        this.tours = new ArrayList<>();
    }

    public void readMap() throws IOException {
        File file = new File("C:\\Users\\jubac\\OneDrive\\Documents\\BUT\\S2\\Java\\SAE_Juba_Antoine_Saidd\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\map1");
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

    public void ajouterTour(Tour t){
        this.tours.add(t);
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public boolean dansTerrain(int x, int y) {
        return getMap()[x][y]==1427;
    }


}

