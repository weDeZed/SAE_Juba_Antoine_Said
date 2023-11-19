package com.sae.sae_juba_antoine_said.bdd;

public class SessionUtilisateur {
    private static String identifiant;
    private static boolean estConnecte = false;

    public static void connecterUtilisateur(String identifiant) {
        identifiant = identifiant;
        estConnecte = true;
    }

    public static void deconnecterUtilisateur() {
        identifiant = null;
        estConnecte = false;
    }

    public static boolean estConnecte() {
        return estConnecte;
    }

    public static String getIdentifiantUtilisateur() {
        return identifiant;
    }
}
