package com.sae.sae_juba_antoine_said.BFS;

import com.sae.sae_juba_antoine_said.Modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grille {
    private int w;
    private int h;
    private Map<Sommet, Set<Sommet>> listeAdj;


    public Grille(int w, int h) {
        this.w = w;
        this.h = h;
        this.listeAdj = new HashMap();
        construit();
    }

    private void construit() {
        int i;
        int j;
        System.out.println("w " + w + " h " + h+" " +new HashSet());
        for (i = 0; i < this.w; ++i) {
            for (j = 0; j < this.h; ++j) {
                this.listeAdj.put(new Sommet(i, j), new HashSet());
            }
        }

        for (i = 0; i < this.w; ++i) {
            for (j = 0; j < this.h; ++j) {
                Sommet s = this.getSommet(i, j);
                if (this.dansGrille(i - 1, j)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i - 1, j));
                }

                if (this.dansGrille(i + 1, j)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i + 1, j));
                }

                if (this.dansGrille(i, j + 1)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i, j + 1));
                }

                if (this.dansGrille(i, j - 1)) {
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
    public Set<Sommet> getAdjacents(Sommet s) {
        if(this.listeAdj.containsKey(s)) {
            return this.listeAdj.get(s);
        } else {
            return new HashSet<Sommet>();
        }
    }


    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < this.w && y >= 0 && y < this.h;
    }

}
