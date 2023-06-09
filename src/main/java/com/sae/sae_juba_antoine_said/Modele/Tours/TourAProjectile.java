package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
import javafx.collections.ObservableList;

public class TourAProjectile extends Tour {


    public TourAProjectile(int x, int y, int degats, int range, Environnement env) {
        super(x, y, degats, range, env);


    }

    @Override
    public void attaqueEnnemi() {
        Acteur act=this.ennemiPlusProche();
                if (act != null)  {
                        Projectile p = creeProjectile();

                       // p.lancerProjectile(act);


                               /*
                           System.out.println("Acteur : " + acteur);
                           System.out.println("Ennemi plus proche : " + this.ennemiPlusProche());

                                */





                }
            }



    public Projectile creeProjectile() {

            Projectile pro = new Projectile(this.getX() + 10, this.getY() - 30, env);
            this.env.ajouterProjectile(pro);
            return pro;

    }

    }





