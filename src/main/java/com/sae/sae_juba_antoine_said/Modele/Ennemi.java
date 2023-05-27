package com.sae.sae_juba_antoine_said.Modele;

import java.util.ArrayList;

public abstract class Ennemi extends Acteur{

    private int indiceCheminActuel = 0;
    private ArrayList<Sommet> chemin;
    public Ennemi(int pv, int x, int y, int vitesse, int range,Environnement env) {
        super(pv, x, y, vitesse, range,env);
        sommet=env.getSommet(28,45);
        bfs=new BFS(env,sommet);
        chemin =bfs.cheminVersSource(env.getSommet(50,0));
    }

    @Override
    public void seDeplacer(Acteur a) {

    }

    @Override
    public abstract Acteur attaquer();

    @Override
    public void agir() {

    }
    public void move() {
        if (indiceCheminActuel < chemin.size() - 1) {
            indiceCheminActuel++;
            this.setX(chemin.get(indiceCheminActuel).getX()*16);
            this.setY(chemin.get(indiceCheminActuel).getY()*16);
        }

    }


}
