package com.sae.sae_juba_antoine_said.modele.environnement;

import com.sae.sae_juba_antoine_said.modele.acteurs.*;
import com.sae.sae_juba_antoine_said.modele.bfs.BFS;
import com.sae.sae_juba_antoine_said.modele.bfs.Sommet;
import com.sae.sae_juba_antoine_said.modele.projectiles.Projectile;
import com.sae.sae_juba_antoine_said.modele.tours.Tour;
import com.sae.sae_juba_antoine_said.modele.vague.CompositeVague;
import com.sae.sae_juba_antoine_said.modele.vague.VagueEnnemi;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Environnement {

    BFS bfs;

    private IntegerProperty vie;
    private int x, y;
    private int[][] map;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Acteur> acteursEnnemies;
    private ObservableList<Tour> tours;
    private IntegerProperty nbEnemies;
    private ObservableList<Projectile> projectiles;
    private ArrayList<Sommet> chemin;
    private IntegerProperty piece;
    private int nbTour;
    private final int CHEMIN = 230;
    private final int TERRAIN = 164;

    private VagueEnnemi vagueEnnemi;
    private int indexActeur;
    private static Environnement getInstance = null;
    static int count = 0;

    private Environnement() {

        try {
            this.x = 90;
            this.y = 90;
            this.map = new int[x][y];
            this.acteurs = FXCollections.observableArrayList();
            this.tours = FXCollections.observableArrayList();
            this.projectiles = FXCollections.observableArrayList();
            this.acteursEnnemies = FXCollections.observableArrayList();

            nbEnemies = new SimpleIntegerProperty();

            readMap();
            bfs = new BFS(this);
            chemin = bfs.cheminVersSource();
            this.piece = new SimpleIntegerProperty(170);
            this.vie = new SimpleIntegerProperty(100);
            this.nbTour = 0;
            vagueEnnemi = new VagueEnnemi(this, new CompositeVague());
            indexActeur = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Environnement getEnvironnementInstance() {
        if (getInstance == null) {
            getInstance = new Environnement();
        }
        return getInstance;
    }

    public void readMap() throws IOException {
        File file = new File("src/main/java/com/sae/sae_juba_antoine_said/Ressources/map1");
        BufferedReader terrain = new BufferedReader(new FileReader(file));
        String ligne;
        String[] tout_ligne;

        try {
            int x = 0;
            while ((ligne = terrain.readLine()) != null) {
                tout_ligne = ligne.split(",");
                for (int y = 0; y < tout_ligne.length; y++) {
                    if (!tout_ligne[y].trim().isEmpty()) {
                        map[y][x] = Integer.parseInt(tout_ligne[y].trim());

                    }
                }
                x++;
            }

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        try {
            terrain.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void unTour() {


        if (getNbEnemies() == 0) { // Il crée une vague lorsqu'il n'y a plus d'ennemis sur le terrain
            vagueEnnemi.creeVague();
        }

        if (nbTour % 10 == 0) {
            if (acteursEnnemies.size() != 0) {
                ajouterActeur(acteursEnnemies.get(0));
                acteursEnnemies.remove(0);
            }
        }


        /********les acteurs agir ********/
        for (Acteur acteur : getActeurs()) {
            acteur.agir();
        }


        // Les tours attaquent tous les 7 tours
        if (nbTour % 7 == 0) {
            for (Tour t : getTours()) {
                t.attaqueEnnemi();
            }
        }

        // Lancer les projectiles
        for (Projectile p : getProjectiles()) {
            p.lancerProjectile();
        }

        // Supprimer les projectiles qui ont terminé leur trajectoire
        projectiles.removeIf(Projectile::aFiniTrajectoire);
        nbTour++;
    }

    public VagueEnnemi getVagueEnnemi() {
        return vagueEnnemi;
    }

    public void setActeurs(ObservableList<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMap() {
        return map;
    }

    public int getPiece() {
        return this.piece.getValue();
    }

    public IntegerProperty getPieceProperty() {
        return this.piece;
    }

    public void decrementationPiece(int piece) {
        this.piece.subtract(piece);
    }

    public void ajoutePiece(int piece) {
        this.piece.setValue(this.piece.getValue() + piece);
    }


    public void suppActeur(Acteur acteur) {
        acteurs.remove(acteur);
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        this.acteurs.add(a);
    }

    public void ajouterEnnemis(Acteur a) {
        acteursEnnemies.add(a);
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public void ajouterTour(Tour t) {
        this.tours.add(t);
    }


    public void ajouterProjectile(Projectile p) {
        this.projectiles.add(p);
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }


    public void removeProjectile(Projectile p) {
        this.projectiles.remove(p);
    }

    public int getVie() {
        return vie.get();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie.set(vie);
    }

    public BFS getBfs() {
        return bfs;
    }

    public void decrementerVie(double v) {
        this.vie.setValue(this.getVie() - v);
    }

    public ArrayList<Sommet> getChemin() {
        return chemin;
    }

    public void ajouterPieces(int piece) {
        this.piece.set(getPiece() + piece);
    }

    public void setPiece(int piece) {
        this.piece.set(getPiece() - piece);
    }

    public static Environnement getGetInstance() {
        return getInstance;
    }

    public static void mettreEnvInstanceNull() {
        Environnement.getInstance = null;
    }

    public int getNbEnemies() {
        return nbEnemies.get();
    }

    public IntegerProperty nbEnemiesProperty() {
        return nbEnemies;
    }

    public void setNbEnemies(int nbEnemies) {
        this.nbEnemies.set(nbEnemies);
    }

    public void decrementNbEnemies(int n) {
        setNbEnemies(getNbEnemies() - n);
    }
}
