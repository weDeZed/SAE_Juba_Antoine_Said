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


        System.out.println("x "+getX()+" y "+getY());

        for(int i = 0; i<1; i++){

            this.env.ajouterProjectile(new Projectile(this.getX()+10,this.getY()-10,env));
            System.out.println("projectile cree");

            /*
            for(int j = 0; j<env.getProjectiles().size(); j++){
                System.out.println("id acteur" + a.get(i).getId());
                env.getProjectiles().get(j).deplacerVers(a.get(i));
                System.out.println("id proj" + env.getProjectiles().get(i).getId());
                System.out.println("Projectile lancé");
            }
            */
        }

        System.out.println("sort du for");
    }


}
