package com.sae.sae_juba_antoine_said.Modele.Environnement;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.*;
import com.sae.sae_juba_antoine_said.Modele.BFS.BFS;
import com.sae.sae_juba_antoine_said.Modele.BFS.Sommet;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
import com.sae.sae_juba_antoine_said.Modele.Tours.TourAProjectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.TroopTour;
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
    private ObservableList<Tour> tours;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;
    private ObservableList<Projectile> projectiles;
    private ArrayList<Sommet> chemin;
    private IntegerProperty piece;
    private int nbTour;
    private final int CHEMIN = 230;
    private final int TERRAIN = 164;

    private VagueEnnemi vagueEnnemi;
    private int indexActeur;


    public Environnement(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.map = new int[x][y];
        this.acteurs = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        readMap();
        bfs = new BFS(this);
        chemin = bfs.cheminVersSource();
        this.piece = new SimpleIntegerProperty(170);
        this.vie = new SimpleIntegerProperty(100);
        this.nbTour = 0;
        vagueEnnemi = new VagueEnnemi(this);
        indexActeur = 0;

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

    public int getCHEMIN() {
        return CHEMIN;
    }

    public void unTour() {
        int nbEnnemie = 0;
        for (Acteur acteur : getActeurs()) {
            if (acteur instanceof Ennemi) {
                nbEnnemie++; // verifier les ennemis
            }
        }


        if (nbEnnemie <= 0 && nbTour % 10 == 0) { // Il crée une vague lorsqu'il n'y a plus d'ennemis sur le terrain
            vagueEnnemi.creeVague();
        }

        /********les acteurs agire ********/
        for (Acteur acteur : getActeurs()) {
            acteur.agir();
        }


        // Les tours attaquent tous les 5 tours
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

}















































/*
    public void ennemieAtteintSommetCible() {
        for (Acteur ac : this.getActeurs()) {
            if (ac.estVivant() && ac instanceof Ennemi) {
                if (ac.getX() == this.getBfs().getCible().getX() * 32 && ac.getY() == this.getBfs().getCible().getY() * 32) {
                    //System.out.println("Vie env avant: " + this.getVie());
                    this.decrementerVie(10);
                    //System.out.println("Vie env apres: " + this.getVie());
                }
                //  System.out.println("Apres if ");
            }
        }
    }

     */

