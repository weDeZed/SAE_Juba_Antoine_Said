package com.sae.sae_juba_antoine_said.Controlleur;



import  com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import com.sae.sae_juba_antoine_said.Vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsProjectile implements ListChangeListener<Projectile> {


    private Pane panneauDeJeu;

    public ListObsProjectile(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Projectile> p) {
        System.out.println("dans class dans obs projectile");

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
