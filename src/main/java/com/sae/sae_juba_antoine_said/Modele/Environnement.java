package com.sae.sae_juba_antoine_said.Modele;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Environnement {

    private int x, y;
    private int[][] map;


    public Environnement(int x, int y) {
        this.x = x;
        this.y = y;
        this.map = new int[x][y];
    }

    public void readMap() throws IOException {
        File file = new File("C:\\Users\\jubac\\OneDrive\\Bureau\\SAE_Juba_Antoine_Said\\SAE_Juba_Antoine_Said\\SAE_Juba_Antoine_Said\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\map1");
        BufferedReader terrain = new BufferedReader(new FileReader(file));
        String ligne;
        String[] tout_ligne;
        try {
            int x = 0;
            while ((ligne = terrain.readLine()) != null) {
                tout_ligne = ligne.split(",");
                for (int y = 0; y < tout_ligne.length; y++) {
                    this.map[x][y] = Integer.parseInt(tout_ligne[y].trim());
                }
                x++;
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                }
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
}

