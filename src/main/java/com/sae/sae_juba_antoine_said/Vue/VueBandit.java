package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Bandit;
import com.sae.sae_juba_antoine_said.Modele.Guerrier;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.util.ArrayList;

public class VueBandit {
    private Pane pane;
    private ObservableList<Acteur> guerriers;
    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;
    Circle circle;

    public VueBandit(Pane pane, Acteur g) {
        this.pane = pane;

        FileInputStream fichierGuerrier = null;
        try {
            fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/bandit.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.image = new Image(fichierGuerrier);
        this.imageView = new ImageView(image);
        imageView.xProperty().bind(g.xProperty());
        imageView.yProperty().bind(g.yProperty());
        this.pane.getChildren().add(imageView);

    }
}


