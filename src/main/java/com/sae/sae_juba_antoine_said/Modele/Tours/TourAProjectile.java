package com.sae.sae_juba_antoine_said.Modele;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.collections.ObservableList;

public class TourAProjectile extends Tour{



    public TourAProjectile(int x, int y, int degats, int range,Environnement environment ) {
        super(x, y, degats, range,environment);
        



    }

    @Override
    public void attaqueEnnemi() {
        for (Projectile pro: getEnvironment().getProjectiles()) {
            for(int k = 0; k < this.ennemiPlusProche().size(); k++) {
                if (this.ennemiPlusProche().get(k).estVivant()) {
                    pro.lancerProjectile(this.ennemiPlusProche().get(k));

                } else {
                    getEnvironment().suppActeur(this.ennemiPlusProche().get(k));
                    getEnvironment().getProjectiles().remove(pro);
                }

    }

    public void creeProjectile(){
        ObservableList<Acteur>a = this.ennemiPlusProche();

        for(int i = 0; i<1; i++){

            this.env.ajouterProjectile(new Projectile(this.getX()+10,this.getY()-30,env));

        }

    }


}
