package com.sae.sae_juba_antoine_said.Modele;

import javafx.collections.ObservableList;

public class TourAProjectile extends Tour{



    public TourAProjectile(int x, int y, int degats, int range,Environnement environment ) {
        super(x, y, degats, range,environment);
        



    }

    @Override
    public void attaqueEnnemi() {

    }

    public void creeProjectile(){
        ObservableList<Acteur>a = this.ennemiPlusProche();

        for(int i = 0; i<1; i++){

            this.env.ajouterProjectile(new Projectile(this.getX()+10,this.getY()-30,env));

        }

    }


}
