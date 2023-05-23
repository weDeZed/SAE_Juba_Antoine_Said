package com.sae.sae_juba_antoine_said.BFS;

import java.io.IOException;

public class MainBFS3 {
    public static void main(String[] args) throws IOException {
        Sommet source, cible;

        Grille g=new Grille(20,20);
        source=g.getSommet(0,0);
        cible=g.getSommet(10,19);
        BFS bfs=new BFS(g,source);
        bfs.cheminVersSource(cible);
    }
}
