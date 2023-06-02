package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Environnement {

    BFS bfs;

    private int x, y;
    private int[][] map;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Tour> tours;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;
    private ObservableList<Projectile> projectiles;
    private ArrayList<Sommet> chemin;


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


    public ArrayList<Sommet> getChemin() {
        return chemin;
    }

    public void tour(){
        for (int i = 0; i < acteurs.size(); i++){
            if (acteurs.get(i) instanceof Ennemi) {
                ((Ennemi) acteurs.get(i)).move();
            }
            //((Bandit) acteurs.get(i)).move();
            if(acteurs.get(i)instanceof Bandit){
                if( acteurs.get(i).attaquer()!=null){
                   acteurs.get(i).agir();
               }
            }
            if(acteurs.get(i) instanceof Guerrier){
                ((Guerrier) acteurs.get(i)).marcherSurChemin();
                if( acteurs.get(i).attaquer()!=null){
                    acteurs.get(i).agir();
                }
            }
        }
    }



}
































/*
public void unTour(){
        int acteurVivant = 0;
        int nbL = 0;
        int nbM = 0;
        // cela ne peut etre un foreach a cause des naissances
        // modification de acteurs.
        //System.out.println("tour " + this.nbTours);
        for (int i = 0; i < acteurs.size(); i++) {
            Acteur a = acteurs.get(i);
            a.agit();
            acteurVivant++;
        }
        for (int i = acteurs.size() - 1; i >= 0; i--) {
            Acteur a = acteurs.get(i);
            if (!a.estVivant()) {
                System.out.println("mort de : " + a);
                lesActeursMorts.add(a);
                acteurs.remove(i);
            }
        }
        this.nbTours.setValue(this.nbTours.getValue() + 1);
        this.acteurVivant.setValue(acteurVivant);

        for (Acteur acteur : acteurs) {
            if (acteur instanceof Mouton) {
                nbM++;
            } else {
                nbL++;
            }
        }
        nbMouton.setValue(nbM);
        nbLoup.setValue(nbL);

    }
 */
