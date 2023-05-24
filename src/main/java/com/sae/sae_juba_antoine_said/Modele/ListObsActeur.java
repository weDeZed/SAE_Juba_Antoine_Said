package com.sae.sae_juba_antoine_said.Modele;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class ListObsActeur implements ListChangeListener<Acteur> {


    private Pane panneauDeJeu;

    public ListObsActeur(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    private void enleverActeur (Acteur m){

        this.panneauDeJeu.getChildren().remove(this.panneauDeJeu.lookup("#"+ m.getId()));
    }

    @Override
    public void onChanged(Change<? extends Acteur> a) {

          while (a.next()) {
              for (Acteur m : a.getRemoved()) {
                  System.out.println("mort");
                  enleverActeur(m);
              }
          }
    }

/*
            for (Acteur act : a.getAddedSubList()) {
                try {
                    fichierGuerrier = new FileInputStream("C:\\Users\\jubac\\OneDrive\\Documents\\BUT\\S2\\Java\\SAE_Juba_Antoine_Saidd\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\saidkamal.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (act instanceof Acteur) {
                    System.out.println("CHAGEMENT");
                    image = new Image(fichierGuerrier);
                    imageView = new ImageView(image);
                    panneauDeJeu.getChildren().add(imageView);
                    imageView.xProperty().bind(act.xProperty());
                    imageView.yProperty().bind(act.yProperty());

                }
            }
*/
}




