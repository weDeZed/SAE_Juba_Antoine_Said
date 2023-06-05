package com.sae.sae_juba_antoine_said.Modele.Environnement;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Bandit;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Guerrier;
import com.sae.sae_juba_antoine_said.Modele.BFS.BFS;
import com.sae.sae_juba_antoine_said.Modele.BFS.Sommet;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
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

    private int x, y;
    private int[][] map;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Tour> tours;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;
    private ObservableList<Projectile> projectiles;
    private ArrayList<Sommet>chemin;

    private IntegerProperty piece;





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
        this.piece=new SimpleIntegerProperty();

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

                       // System.out.println(" Largeur  : "+x);
                        //System.out.print(" "+map[x][y]);
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

    public int getPiece(){
        return this.piece.getValue();
    }

    public IntegerProperty getPieceProperty (){
        return this.piece;
    }
    public void decrementationPiece (int piece){
        this.piece.subtract(piece);
    }

    public void ajoutePiece (int piece){
        this.piece.setValue(this.piece.getValue()+piece);
    }


    public void suppActeur (Acteur acteur){
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

    public void ajouterTour(Tour t){
        this.tours.add(t);
}
    public boolean dansTerrain(int x, int y) {
        return getMap()[x][y] == 1427;
    }

    public void ajouterProjectile(Projectile p) {
        this.projectiles.add(p);
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }




  public void removeProjectile(Projectile p ){
        this.projectiles.remove(p);
  }
    public Sommet getSommet(int x, int y) {
        for (Sommet sommet : this.listeAdj.keySet()) {
            if (sommet.getX() == x && sommet.getY() == y) {
                return sommet;
            }
        }
        return null;
    }



    public ArrayList<Sommet> getChemin() {
        return chemin;
    }

    public void tour(){
        for (int i = 0; i < acteurs.size(); i++){

            if (!acteurs.get(i).estVivant()){
                suppActeur(acteurs.get(i));
            }

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





