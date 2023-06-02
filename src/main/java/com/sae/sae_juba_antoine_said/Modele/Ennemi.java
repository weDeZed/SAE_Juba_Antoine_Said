package com.sae.sae_juba_antoine_said.Modele;



public abstract class Ennemi extends Acteur {

    private int indiceCheminActuel = 0;


    public Ennemi(int pv, int x, int y, int vitesse, int range, Environnement env) {
        super(pv, x, y, vitesse, range, env);
    }

    @Override
    public  abstract void seDeplacer();
    public abstract void agir();

    @Override
    public abstract Acteur attaquer();

    public void move() {
        if (indiceCheminActuel < this.ev.getChemin().size() - 1) {
            indiceCheminActuel++;
            //System.out.println("x "+this.ev.getChemin().get(indiceCheminActuel).getX()+"y "+this.ev.getChemin().get(indiceCheminActuel).getY());
            this.setX(this.ev.getChemin().get(indiceCheminActuel).getX() * 16);
            this.setY(this.ev.getChemin().get(indiceCheminActuel).getY() * 16);
        }
    }


}
