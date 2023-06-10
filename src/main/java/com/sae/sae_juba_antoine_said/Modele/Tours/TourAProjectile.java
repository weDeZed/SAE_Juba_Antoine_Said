package com.sae.sae_juba_antoine_said.Modele.Tours;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TourAProjectile extends Tour {
    public TourAProjectile(int x, int y, int degats, int prix, int range, Environnement env) {
        super(x, y, degats, 50, range, env);


    }

    private Map<Projectile, Acteur> projectileEnnemiMap = new HashMap<>();

    public void assignerEnnemiAuxProjectiles() {
        List<Acteur> ennemis = this.ennemiPlusProche(); // Supposons que cette méthode renvoie une liste d'ennemis triés par proximité
        List<Projectile> projectiles = getEnvironment().getProjectiles();

        for (int i = 0; i < Math.min(projectiles.size(), ennemis.size()); i++) {
            projectileEnnemiMap.put(projectiles.get(i), ennemis.get(i));
        }
    }

    @Override
    public void attaqueEnnemi() {
        assignerEnnemiAuxProjectiles();

        for (Map.Entry<Projectile, Acteur> entry : projectileEnnemiMap.entrySet()) {
            Projectile pro = entry.getKey();
            Acteur ennemi = entry.getValue();

            if (ennemi.estVivant()) {
                pro.lancerProjectile(ennemi);
            } else {
                getEnvironment().suppActeur(ennemi);
                getEnvironment().getProjectiles().remove(pro);
                projectileEnnemiMap.remove(pro);
            }
        }
    }


    public void creeProjectile() {
        ObservableList<Acteur> a = this.ennemiPlusProche();
        for (int i = 0; i < 1; i++) {
            this.env.ajouterProjectile(new Projectile(this.getX() + 10, this.getY() - 30, env));
        }
    }

}


