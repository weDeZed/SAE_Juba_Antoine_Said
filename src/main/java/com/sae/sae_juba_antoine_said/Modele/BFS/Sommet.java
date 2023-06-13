package com.sae.sae_juba_antoine_said.Modele.BFS;
public class Sommet {
    private int x;
    private int y;
    private double poids;
    public Sommet(int x, int y, double poids) {
        this.x = x;
        this.y = y;
        this.poids = poids;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public double getPoids() {
        return this.poids;
    }
    public void setPoids(double poids) {
        this.poids = poids;
    }
    @Override
    public String toString(){
        return "Sommet{" +
                "x=" + x +
                ", y=" + y +
                ", poids=" + poids +
                '}';
    }
}
