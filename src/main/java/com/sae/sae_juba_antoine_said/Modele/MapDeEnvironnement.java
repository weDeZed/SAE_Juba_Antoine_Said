package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class MapDeEnvironnement {
    private int x;
    private int y;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;

    private int[][] map;


    public MapDeEnvironnement(int x, int y) {
        this.x = x;
        this.y = y;
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        map = new int[x][y];
        //remplirMap();
        this.construit();

    }

    public void remplirMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if ((j >= 23 && j <= 27) && (i < 40)) {
                    map[i][j] = 1427;
                } else if (i >= 40 && i < 43 && (j > 10 && j <= 40)) {
                    map[i][j] = 1427;
                } else {
                    map[i][j] = 688;
                }
            }
        }
    }

    public void construit() {
        int i;
        int j;
        for (i = 0; i < this.x; ++i) {
            for (j = 0; j < this.y; ++j) {
                if (map[i][j] == 1427) {
                    Sommet s = new Sommet(i, j,1427);
                    this.listeAdj.put(s, new HashSet());
                } else {
                    Sommet s = new Sommet(i, j,688);
                    this.listeAdj.put(s, new HashSet());
                }
            }
        }


        for (Sommet key : this.listeAdj.keySet()) {
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

    public boolean dansMap(int x, int y) {
        return x >= 0 && x < this.getX() && y >= 0 && y < this.getY();
    }

    /*ublic Sommet getSommet1(int x, int y) {
        Iterator var4 = this.listeAdj.keySet().iterator();

        Sommet sommet;
        do {
            if (!var4.hasNext()) {
                return null;
            }
            sommet = (Sommet) var4.next();
        } while (sommet.getX() != x || sommet.getY() != y);
        return sommet;
    }
    */
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

    /*public Set<Sommet> adjacents(Sommet s) {
        return (Set) (!this.estDeconnecte(s) ? (Set) this.listeAdj.get(s) : new HashSet());
    }
    */

    public Set<Sommet> adjacents(Sommet s) {
        if (this.estDeconnecte(s)) {
            return new HashSet<>();
        } else {
            Set<Sommet> adjacents = new HashSet<>(this.listeAdj.get(s));
            adjacents.removeIf(adjacent -> adjacent.getPoids() != s.getPoids());
            return adjacents;
        }
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

    public Map<Sommet, Set<Sommet>> getListeAdj() {
        return listeAdj;
    }

    public void setListeAdj(Map<Sommet, Set<Sommet>> listeAdj) {
        this.listeAdj = listeAdj;
    }

    public void parcourirMap() {
        System.out.println("parourire map dans class map ");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    public void parcourirAdjList() {
        for (Sommet key : listeAdj.keySet()) {
            System.out.println("Clé: " + key + ", Valeur: " + listeAdj.get(key));
        }

    }

    public void deconnecte(Sommet s) {
        this.obstacles.add(s);
    }

    public void parcourObs() {
        System.out.println("les sommets obstacle");
        for (Sommet s : obstacles) {
            System.out.print(" " + s + " ");
        }
    }


    public int[][] getMap() {
        return map;
    }
}

