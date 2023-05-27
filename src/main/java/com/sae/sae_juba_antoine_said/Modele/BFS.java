package com.sae.sae_juba_antoine_said.Modele;

import java.util.*;

public class BFS {

    private Environnement map;

    private Sommet source;

    private ArrayList<Sommet> parcours;


    private Map<Sommet, Sommet> predecesseurs;


    public BFS(Environnement map, Sommet source) {
        this.map = map;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Sommet, Sommet>();
        algoBFS();
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
            for (Sommet t : map.adjacents(suivant)) {
                if (!(Marquage.contains(t))) {
                    Marquage.addFirst(t);
                    fifo.addFirst(t);
                    predecesseurs.put(t, suivant);
                }
            }
        }

    }

    public ArrayList<Sommet> cheminVersSource(Sommet cible) {
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

