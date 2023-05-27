package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Environnement {

    BFS bfs;

    private int x, y;

    private int[][] map;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Tour> tours;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;




    public Environnement(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        //this.map = new MapDeEnvironnement(x,y);
        this.map = new int[x][y];
        this.acteurs = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        readMap();
        construit();




    }

    public void readMap() throws IOException {
        File file = new File("src/main/java/com/sae/sae_juba_antoine_said/Ressources/map1");
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

    public void construit() {
        int i;
        int j;
        for (i = 0; i < this.x; ++i) {
            for (j = 0; j < this.y; ++j) {

                if (map[i][j] == 1427) {
                    Sommet s = new Sommet(i, j,1427);
                    //System.out.println("dans case 1427 ");
                    this.listeAdj.put(s, new HashSet());

                } else {
                    Sommet s = new Sommet(i, j,688);
                    this.listeAdj.put(s, new HashSet());
                    s.setPoids(688);

                }
                //System.out.print(map[i][j]);
            }

        }  for (Sommet key : this.listeAdj.keySet()) {
            //System.out.println(" key dans coustruit " + key);
        }
        for (i = 0; i < this.x; ++i) {
            for (j = 0; j < this.y; ++j) {
                Sommet s = this.getSommet(i, j);
                if (this.dansMap(i - 1, j)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i - 1, j));
                }
                if (this.dansMap(i + 1, j)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i + 1, j));
                }
                if (this.dansMap(i, j + 1)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i, j + 1));
                }
                if (this.dansMap(i, j - 1)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i, j - 1));
                }

            }
        }

    }

    public Sommet getSommet(int x, int y) {
        for (Sommet sommet : this.listeAdj.keySet()) {
            if (sommet.getX() == x && sommet.getY() == y) {
                return sommet;
            }
        }
        return null;
    }


    public boolean estDeconnecte(Sommet s) {
        return this.obstacles.contains(s);
    }

    public Set<Sommet> adjacents(Sommet s) {
        if (this.estDeconnecte(s)) {
            return new HashSet<>();
        } else {
            Set<Sommet> adjacents = new HashSet<>(this.listeAdj.get(s));//il prends tous les sommets adjacents de sommet s
            adjacents.removeIf(adjacent -> adjacent.getPoids() != s.getPoids());//il suprime tous les sommet que les poids ne sont pas égale
            //System.out.println(" "+adjacents);
            return adjacents;
        }
    }

}





