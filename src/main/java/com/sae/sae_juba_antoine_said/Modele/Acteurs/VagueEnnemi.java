package com.sae.sae_juba_antoine_said.Modele.Acteurs;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VagueEnnemi {

    Environnement env;
    private IntegerProperty nbVague;
    private IntegerProperty nbVagueBinding;// cette variable pour bindé avec nb de vague dans controlleur

    int temp;

    public VagueEnnemi(Environnement env) {
        this.env = env;
        this.nbVague = new SimpleIntegerProperty(0);
        this.nbVagueBinding = new SimpleIntegerProperty(0);
        temp = 0;
    }

    public void creeVague() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int compteur = 0;

            @Override
            public void run() {
                int type = (int) (Math.random() * 2);
                Ennemi e;
                if (type == 1) {
                    e = new Bandit(52 * 32, 24 * 32, 0, env);
                } else {
                    e = new Dragon(52 * 32, 24 * 32, 0, env);
                }

                // Utiliser Platform.runLater() pour exécuter le code sur le thread d'application JavaFX
                Platform.runLater(() -> env.ajouterActeur(e));

                compteur++;
                if (getNbVague() % 2 == 0) { // créer  10 ennemies
                    if (compteur >= 10) {
                        timer.cancel(); // Arrêter le minuteur
                    }
                } else {
                    if (compteur >= 15) { //créer 15 ennemies
                        timer.cancel(); // Arrêter le minuteur
                    }
                }

            }
        };
        timer.schedule(task, 1000, 2000); // Début après 1 secondes, répétition toutes les 2 secondes
        setNbVagueBinding(1); // ajouter vage
    }

    public IntegerProperty nbVagueProperty() {
        return nbVague;
    }

    public int getNbVague() {
        return nbVague.get();
    }

    public void setNbVague(int value) {
        nbVague.set(value);
    }

    public IntegerProperty nbVagueBindingProperty() {
        return nbVagueBinding;
    }

    public int getNbVagueBinding() {
        return nbVagueBinding.get();
    }

    public void setNbVagueBinding(int value) {
        nbVagueBinding.set(getNbVague() + value);
    }
}




