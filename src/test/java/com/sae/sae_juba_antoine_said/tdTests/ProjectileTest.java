package com.sae.sae_juba_antoine_said.tdTests;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectileTest {



    private Projectile projectile;
    private Acteur acteur;



    @Test
    public void testLancerProjectile() {

        Environnement env;
        try {
            env = Environnement.getEnvironnementInstance();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        acteur = new Acteur(110, 0, 0, 0, 0, env) {
            @Override
            public Acteur attaquer() {
                return null;
            }

            @Override
            public void agir() {

            }
        }; // Créez l'acteur approprié ici
        projectile = new Projectile(0, 0,0, acteur, env);

        // Définir les positions de l'acteur
        acteur.setX(22);
        acteur.setY(22);

        projectile.lancerProjectile();

        // Vérifier les nouvelles positions
        assertEquals(22, projectile.getX());
        assertEquals(22, projectile.getY());

        // Vérifier que le projectile a atteint l'acteur
        assertTrue(projectile.aFiniTrajectoire());
    }


    @Test
    void testAtteintActeur_CasAtteint() {

        // Création des objets nécessaires pour le test
        Environnement env;
        try {
            env = Environnement.getEnvironnementInstance();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Acteur a = new Acteur(100, 19, 19, 2, 10, env) {
            @Override
            public Acteur attaquer() {
                return null;
            }

            @Override
            public void agir() {}
        };

        // Le projectile sera créé juste à côté de l'acteur
        Projectile p = new Projectile(19, 19,0, a, env);

        // Appeler la méthode à tester
        boolean resultat = p.atteintActeur();

        // Vérifier le résultat
        assertTrue(resultat, "Le projectile atteint l'acteur.");
    }























}