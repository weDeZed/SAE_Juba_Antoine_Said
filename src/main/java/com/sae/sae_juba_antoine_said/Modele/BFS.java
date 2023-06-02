package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class BFS {

    private int map[][];
    private int x, y;
    private Environnement mapEnv;

    private Sommet source,cible;

    private ArrayList<Sommet> parcours;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;

    private Map<Sommet, Sommet> predecesseurs;


    public BFS(Environnement mapEnv) {
        this.x = mapEnv.getX();
        this.y = mapEnv.getY();
        this.mapEnv = mapEnv;
        this.map = mapEnv.getMap();
        parcours = new ArrayList<>();
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        predecesseurs = new HashMap<Sommet, Sommet>();
        construit();
        this.source = getSommet(28, 45);
        this.cible=getSommet(50,0);
        algoBFS();


    }
    public void construit() {
        int i;
        int j;
        for (i = 0; i < this.x; ++i) {
            for (j = 0; j < this.y; ++j) {

                if (map[i][j] == 1427) {
                    Sommet s = new Sommet(i, j, 1427);
                    this.listeAdj.put(s, new HashSet());

                } else {
                    Sommet s = new Sommet(i, j, 688);
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
        return x >= 0 && x < map.length && y >= 0 && y < map[x].length;
    }

    public boolean estDeconnecte(Sommet s) {
        return this.obstacles.contains(s);
    }


    private void algoBFS() {
        LinkedList<Sommet> fifo = new LinkedList<>();
        LinkedList<Sommet> Marquage = new LinkedList<>();
        fifo.add(source);
        Marquage.addFirst(source);
        predecesseurs.put(source, null);
        while (!fifo.isEmpty()) {
            Sommet suivant = fifo.pollLast();

            parcours.add(suivant);
            for (Sommet t : this.adjacents1(suivant)) {
                if (!(Marquage.contains(t))) {
                    Marquage.addFirst(t);
                    fifo.addFirst(t);
                    predecesseurs.put(t, suivant);
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
    public Set<Sommet> adjacents1(Sommet s) {
        if (this.estDeconnecte(s)) {
            return new HashSet<>();
        } else {
            Set<Sommet> adjacents = new HashSet<>(this.listeAdj.get(s));//il prends tous les sommets adjacents de sommet s
            adjacents.removeIf(adjacent -> adjacent.getPoids() != s.getPoids());//il suprime tous les sommet que les poids ne sont pas égale
            return adjacents;

        }


    }
    public ArrayList<Sommet> cheminVersSource() {
        ArrayList<Sommet> chemin = new ArrayList<>();
        Sommet courant = cible;
        while (courant != null) {
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        Collections.reverse(chemin);
        /*for (Sommet s : chemin) {
            System.out.print(" " + s);
        }
        */
        return chemin;
    }
}
