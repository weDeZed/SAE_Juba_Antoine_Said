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
        for (Acteur acteur : env.getActeurs()) {
                if (acteur == this.ennemiPlusProche()) {
                   // creeProjectile();
                    for (Projectile pro : getEnvironment().getProjectiles()) {
                    System.out.println("Acteur : " + acteur);
                    System.out.println("Ennemi plus proche : " + this.ennemiPlusProche());

                    pro.lancerProjectile(acteur);

                    if (pro.atteintActeur(acteur)){
                        getEnvironment().getProjectiles().remove(pro);
                    }

                }
            }
        }
    }

    public Projectile creeProjectile() {

            Projectile pro = new Projectile(this.getX() + 10, this.getY() - 30, env);
            this.env.ajouterProjectile(pro);
            return pro;

    }

    public void attaquerEnnemisDansRange() {
        for (Acteur acteur : env.getActeurs()) {
            if (acteur == this.ennemiPlusProche()) {
                if (acteur.estVivant() ) {
                    Projectile projectile = new Projectile(this.getX(), this.getY(), env);
                    System.out.println("Projectile : "+projectile);
                    if (projectile.atteintActeur(acteur)==false){
                        projectile.lancerProjectile(acteur);

                    }
                    if (projectile.atteintActeur(acteur)) {
                        break; // Sortir de la boucle si l'ennemi est touché par le projectile
                    }
                }
            }
        }
    }


}


