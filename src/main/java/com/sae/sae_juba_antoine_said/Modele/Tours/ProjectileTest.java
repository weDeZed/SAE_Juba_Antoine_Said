package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;



public class ProjectileTest {


        @Test
        void testLancerProjectile() {

            // Création des objets nécessaires pour le test
            Environnement env;
            try {
                env = new Environnement(20, 20);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Acteur a = new Acteur(100, 20, 20, 2, 10, env) {
                @Override
                public Acteur attaquer() {
                    return null;
                }

                @Override
                public void agir() {}
            };

            Projectile p = new Projectile(0, 0, a, env);

            // Appeler la méthode à tester
            p.lancerProjectile();

            // Calcul des valeurs attendues
            double distanceX = a.getX() - p.getX();
            double distanceY = a.getY() - p.getY();
            double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            double expectedX = p.getX() + (p.getVitesse() * (distanceX / totalDistance));
            double expectedY = p.getY() + (p.getVitesse() * (distanceY / totalDistance));

            // Assertion de l'égalité des valeurs attendues et réelles avec une précision de 0.0001
            assertEquals((int) expectedX, p.getX(), "X position is incorrect");
            assertEquals((int) expectedY, p.getY(), "Y position is incorrect");
        }
}