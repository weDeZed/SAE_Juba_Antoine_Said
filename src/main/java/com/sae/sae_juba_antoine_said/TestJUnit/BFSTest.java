package com.sae.sae_juba_antoine_said.TestJUnit;

import com.sae.sae_juba_antoine_said.Modele.BFS.BFS;
import com.sae.sae_juba_antoine_said.Modele.BFS.Sommet;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {

    @Test
    void testConstruit() {
        // Créez un nouvel environnement et un nouvel objet BFS
        Environnement env;
        try {
            env = new Environnement(20, 20);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        BFS bfs = new BFS(env);

        // Vérifie que chaque sommet est correctement relié à ses voisins
        for (int i = 0; i < env.getX(); ++i) {
            for (int j = 0; j < env.getY(); ++j) {
                Sommet s = bfs.getSommet(i, j);
                assertNotNull(s, "Aucun sommet créé aux coordonnées (" + i + ", " + j + ")");

                Set<Sommet> adjacents = bfs.adjacents(s);

                // Assurez-vous que chaque sommet a le nombre correct de voisins.
                // Vous devrez adapter ce test en fonction de vos règles spécifiques pour les voisins (par exemple, les bords de la carte pourraient avoir moins de voisins)
                if (bfs.dansMap(i - 1, j) && bfs.dansMap(i + 1, j) && bfs.dansMap(i, j - 1) && bfs.dansMap(i, j + 1)) {
                    assertEquals(4, adjacents.size(), "Le nombre de voisins est incorrect pour le sommet aux coordonnées (" + i + ", " + j + ")");
                } else {
                    assertTrue(adjacents.size() < 4, "Le nombre de voisins est incorrect pour le sommet aux coordonnées (" + i + ", " + j + ")");
                }
            }
        }
    }


}