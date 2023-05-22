package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Environnement {


    private int x, y;

    private int[][] map;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Tour> tours;




    public Environnement(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        //this.map = new MapDeEnvironnement(x,y);
        this.map = new int[x][y];
        this.acteurs = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();




    }

    public void readMap() throws IOException {
        File file = new File("C:\\Users\\jubac\\Desktop\\Programmation\\S2\\TP JAVA\\SAE_Juba_Antoine_Said\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\map1");
        BufferedReader terrain = new BufferedReader(new FileReader(file));
        String ligne;
        String[] tout_ligne;

        try {
            int x = 0;
            while ((ligne = terrain.readLine()) != null) {
                tout_ligne = ligne.split(",");
                for (int y = 0; y < tout_ligne.length; y++) {

                    if (!tout_ligne[y].trim().isEmpty()) {


                        map[y][x] = Integer.parseInt(tout_ligne[y].trim());

                       // System.out.println(" Largeur  : "+x);
                        //System.out.print(" "+map[x][y]);
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


    public void parcourTerrain() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }


    public boolean dansMap(int x, int y) {
        return x >= 0 && x < this.getX() && y >= 0 && y < this.getY();
    }





    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMap() {
        return map;
    }


    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        this.acteurs.add(a);
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public void ajouterTour(Tour t){
        this.tours.add(t);
}
    public boolean dansTerrain(int x, int y) {
        return getMap()[x][y] == 1427;
    }

}





