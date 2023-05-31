package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.ObservableList;

public class TourAProjectile extends Tour{

    private Projectile projectile;
    Environnement environment;

    public TourAProjectile(int x, int y, int degats, int range,Environnement environment ) {
        super(x, y, degats, range,environment);
        



    }

    @Override
    public void attaqueEnnemi() {

    }

    public void lancerProjectile(){
        ObservableList<Acteur>a = this.ennemiPlusProche();

        for(int i = 0; i<a.size(); i++){
            this.environment.ajouterProjectile(new Projectile(this.getX(),this.getY(),environment));
            environment.getProjectiles().get(i).deplacerVers(a.get(i));
        }
    }





}
