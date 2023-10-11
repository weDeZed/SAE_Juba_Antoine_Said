package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GuerrierTest {
    Environnement env;

    GuerrierTest() throws IOException {
        this.env = Environnement.getEnvironnementInstance();

    }

    @Test
    void attaquer() {

        ObservableList<Acteur> acteurs = FXCollections.observableArrayList();
        acteurs.add(new Bandit(36, 22, 10, env));
        Guerrier guerrier = new Guerrier(35, 20, env);
        acteurs.add(guerrier);

        env.setActeurs(acteurs); //  j'ai  mis la liste des acteurs dans environnement

        Acteur cible = guerrier.attaquer(); // attaquer return acteur car  nous avons un ennemi à coté de guerrier
        assertNotNull(cible);

        Bandit bandit = new Bandit(78, 81, 15, env);
        Acteur cible2 = bandit.attaquer();

        assertNull(cible2); // ici il returne null


    }

    @Test
    void marcherSurChemin() {
        Guerrier guerrier = new Guerrier(32 * 32, 6 * 32, env); // cette acteur peux déplacer dans chemin
        guerrier.marcherSurChemin();

    }

    @Test
    void dansTerrain() {
        Guerrier guerrier = new Guerrier(0, 0, env);
        boolean inTerrain = guerrier.dansTerrain(0, 0);
        assertEquals(true, inTerrain); // guerrier est dans terrain
        Guerrier guerrier1 =new Guerrier(-15,10,env);
        boolean pasDansTerrain = guerrier1.dansTerrain(-15,10);
        assertEquals(false, pasDansTerrain); // geurrier n'est pas dans terrain
    }
}