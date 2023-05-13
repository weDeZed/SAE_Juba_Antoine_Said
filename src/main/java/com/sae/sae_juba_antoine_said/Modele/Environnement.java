package com.sae.sae_juba_antoine_said.Modele;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Environnement {

    private int x, y;
    private int[][] map;
    private ArrayList<Acteur> acteurs;


    public Environnement(int x, int y, ArrayList<Acteur> a) {
        this.x = x;
        this.y = y;
        this.map = new int[x][y];
        this.acteurs = a;
    }

    public void readMap() throws IOException{
        File file = new File("/Users/saidkamalshinwari/Downloads/BUT-S1/S2/IHM/SAE_Juba_Antoine_Said/src/main/java/com/sae/sae_juba_antoine_said/Ressources/map2");
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

   public void ajouterActeur(Acteur a){
        this.acteurs.add(a);
   }
    public boolean dansTerrain(int x, int y) {
        return (0 <= x && x < this.x && 0 <= y && y < this.y);
    }



}

