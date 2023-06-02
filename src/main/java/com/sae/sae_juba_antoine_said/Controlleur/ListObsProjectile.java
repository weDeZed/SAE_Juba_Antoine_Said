package com.sae.sae_juba_antoine_said.Controlleur;



import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Projectile;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class ListObsProjectile implements ListChangeListener<Projectile> {


    private Pane panneauDeJeu;

    public ListObsProjectile(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Projectile> p) {

        while (p.next()) {
            for (Projectile proj : p.getAddedSubList()) {

                VueProjectile vueProjectile = new VueProjectile(panneauDeJeu, proj);

            }
            for (Projectile proj : p.getRemoved()) {

                enleverProjectile(proj);
            }
        }
    }

    private void enleverProjectile (Projectile p){
        this.panneauDeJeu.getChildren().remove(this.panneauDeJeu.lookup("#"+ p.getId()));
    }
}
