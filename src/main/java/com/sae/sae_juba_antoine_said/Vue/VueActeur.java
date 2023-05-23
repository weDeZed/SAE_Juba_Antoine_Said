package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.util.ArrayList;

public class VueActeur {
    private Pane pane;
    private ArrayList<Acteur> acteurs;
    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;



    public VueActeur(Pane pane, ArrayList<Acteur> acteurs) {
        this.pane = pane;
        this.acteurs = acteurs;

        FileInputStream fichierActeur = null;

        for (Acteur g : this.acteurs) {

            try {
                if (g instanceof Guerrier) {
                    fichierActeur = new FileInputStream("/home/antoine/Bureau/ihm/SAE_Juba_Antoine_Said-master/src/main/java/com/sae/sae_juba_antoine_said/Ressources/saidkamal.png");
                }
                else if (g instanceof Golem){
                    fichierActeur = new FileInputStream("");
                }

                else if (g instanceof Bandit){
                    fichierActeur = new FileInputStream("/home/antoine/Bureau/ihm/SAE_Juba_Antoine_Said-master/src/main/java/com/sae/sae_juba_antoine_said/Ressources/bandit.png");
                }

                else if (g instanceof Archer){
                    fichierActeur = new FileInputStream("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            this.image = new Image(fichierActeur);
            this.imageView = new ImageView(image);
            imageView.xProperty().bind(g.xProperty());
            imageView.yProperty().bind(g.yProperty());
            ;
            this.pane.getChildren().add(imageView);
        }
    }

}
