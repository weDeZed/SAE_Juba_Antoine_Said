package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Archer;
import com.sae.sae_juba_antoine_said.Modele.Bandit;
import com.sae.sae_juba_antoine_said.Modele.Guerrier;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class VueActeur {
    private Pane pane;
    private  Acteur acteur;
    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;
    Circle circle;

    public VueActeur(Pane pane, Acteur acteur) {
        this.pane = pane;
        this.acteur = acteur;

        FileInputStream fichierGuerrier = null;

        if(acteur instanceof Guerrier ){
            try {

                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/saidkamal.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (acteur instanceof Bandit){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/bandit.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (acteur instanceof Archer){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/saidkamal.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Archer ");

        }
        this.image = new Image(fichierGuerrier);
        this.imageView = new ImageView(image);
        System.out.println(" Height" + image.getHeight()+" Width "+ image.getWidth());
        imageView.xProperty().bind(acteur.xProperty());
        imageView.yProperty().bind(acteur.yProperty());
        this.pane.getChildren().add(imageView);

    }


}