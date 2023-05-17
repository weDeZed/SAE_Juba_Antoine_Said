package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Guerrier;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.util.ArrayList;

public class VueGuerrier {
    private Pane pane;
    private ArrayList<Acteur> guerriers;
    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;
    Circle circle;
    AnimationG animationG;
    public VueGuerrier(Pane pane, ArrayList<Acteur> guerriers) {
        this.pane = pane;
        this.guerriers = guerriers;
        this.animationG=new AnimationG();

        FileInputStream fichierGuerrier = null;
        for (Acteur g : this.guerriers) {
            try {
                fichierGuerrier = new FileInputStream("/Users/saidkamalshinwari/Downloads/BUT-S1/S2/IHM/SAE_Juba_Antoine_Said/src/main/java/com/sae/sae_juba_antoine_said/Ressources/saidkamal.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (g instanceof Guerrier) {
                    this.image = new Image(fichierGuerrier);
                    this.imageView = new ImageView(image);
                    imageView.xProperty().bind(g.xProperty());
                    imageView.yProperty().bind(g.yProperty());
            }
            this.pane.getChildren().add(imageView);

        }

    }
}
