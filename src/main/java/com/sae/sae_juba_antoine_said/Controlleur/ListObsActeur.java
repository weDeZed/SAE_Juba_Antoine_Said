package com.sae.sae_juba_antoine_said.Controlleur;


import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ListObsActeur implements ListChangeListener<Acteur> {

    private Pane panneauDeJeu;
    private Environnement ev;

    public ListObsActeur(Pane panneauDeJeu, Environnement ev) {
        this.panneauDeJeu = panneauDeJeu;
        this.ev = ev;
    }

    private void enleverActeur (Acteur m){
        Node sprite=panneauDeJeu.lookup("#"+m.getId());
        this.panneauDeJeu.getChildren().remove(sprite);
       // System.out.println(" enlever " +this.panneauDeJeu.lookup("#"+ m.getId()));
        this.panneauDeJeu.getChildren().remove(this.panneauDeJeu.lookup("#"+ m.getId()+1));
    }

    @Override
    public void onChanged(Change<? extends Acteur> a) {
        //System.out.println("chagement dans acteur ");
        while (a.next()) {
            for (Acteur act : a.getAddedSubList()) {
                VueActeur vueActeur = new VueActeur(panneauDeJeu, act);
            }
            for (Acteur m : a.getRemoved()) {
                //System.out.println(" dans removed");
                if (m instanceof Ennemi){
                    ev.ajoutePiece(10);
                }
            //    System.out.println("mort acteur");
                enleverActeur(m);
            }
        }
    }
}


