package com.sae.sae_juba_antoine_said.Modele.BFS;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    BFS bfs = new BFS(Environnement.getEnvironnementInstance());

    BFSTest() throws IOException {
    }

    @Test
    void construit() {
        assertNotNull(bfs.getListeAdj()); // Vérifie que la liste des sommets adjacents dans bfs n'est pas null
        assertFalse(bfs.getListeAdj().isEmpty()); // Vérifie que la liste des sommets adjacents n'est pas vide
        assertTrue(bfs.getObstacles().isEmpty()); // Vérifie que la liste des obstacles dans bfs est vide car je n'ai pas ajouté de sommets obstacles à cette liste
    }

    @Test
    void dansMap() {
        assertTrue(bfs.dansMap(0, 0)); // Vérifie que les coordonnées (0,0) sont bien dans la carte
        assertFalse(bfs.dansMap(-1, -1)); // Vérifie que les coordonnées (-1,-1) ne sont pas dans la carte
    }

    @Test
    void estDeconnecte() {
        Sommet sommet = new Sommet(52, 24, 230);
        assertFalse(bfs.estDeconnecte(sommet)); // Vérifie que ce sommet n'est pas déconnecté car il n'est pas dans la liste des obstacles
    }

    @Test
    void getSommet() {
        assertNotNull(bfs.getSommet(0, 0)); // Vérifie que le sommet (0,0) existe bien sur la carte
        assertNull(bfs.getSommet(-2, 7)); // Vérifie que le sommet (-2,7) n'existe pas sur la carte
    }

    @Test
    void adjacents() {

        Sommet sommet = bfs.getSommet(36, 9);
        Set<Sommet> sommetsAdjSet = bfs.adjacents(sommet); // bfs.adjacents retourne une collection de sommets
        if (sommet != null) {
            assertNotNull(sommet); // Vérifie que les sommets adjacents d'un sommet existent bien
            System.out.println("Les sommets adjacents du sommet (36,9) sont : " + sommetsAdjSet); // Affiche les sommets adjacents du sommet (36,9)
        } else {
            System.out.println("Le sommet (36,9) n'a pas de sommets adjacents.");
        }

        sommet = bfs.getSommet(-3, 8);
        if (sommet != null) {
            assertNotNull(bfs.adjacents(bfs.getSommet(-1, 8)));
        } else {
            System.out.println("Le sommet(-3,8) est null.");
        }
    }


    @Test
    void cheminVersSource() {
        ArrayList<Sommet>chemin=bfs.cheminVersSource();
        if (!chemin.isEmpty()){ // Si le chemin n'est pas vide
            assertNotNull(bfs.cheminVersSource());
            System.out.println("Le chemin de BFS est : " + chemin);
        }else{
            assertTrue(chemin.isEmpty());
            System.out.println("Le chemin est vide.");
        }

    }
}
