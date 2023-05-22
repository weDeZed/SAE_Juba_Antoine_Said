package com.sae.sae_juba_antoine_said.BFS;
import java.util.*;

public class BFS {

    private Grille g;

    private Sommet source;

    private ArrayList<Sommet> parcours;

    private Map<Sommet, Sommet> predecesseurs;

    public BFS(Grille g, Sommet source) {
        this.g = g;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Sommet, Sommet>();
        algoBFS();
    }

    private void algoBFS() {
        LinkedList<Sommet> fifo = new LinkedList<>();
        LinkedList<Sommet> Marquage = new LinkedList<>();
        fifo.add(source); // Ajouter le sommet source à la file d'attente
        Marquage.addFirst(source); // Marquer le sommet source
        predecesseurs.put(source, null); // Le prédécesseur du sommet source est null
        while (!fifo.isEmpty()) {
            Sommet suivant = fifo.pollLast();
            parcours.add(suivant);
            for (Sommet t : g.getAdjacents(suivant)) {
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
        System.out.println("le chemin ver le sommet cible");
        for (Sommet s : chemin) {
            System.out.println(s);
        }

        return chemin;
    }
}

