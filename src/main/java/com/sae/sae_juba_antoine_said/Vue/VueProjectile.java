package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class VueProjectile {
    private Pane pane;

    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;
    Circle circle;
    Projectile projectile;

    public VueProjectile(Pane pane, Projectile projectile) {
        this.pane = pane;
        this.projectile = projectile;

        FileInputStream fichierGuerrier = null;

        if(projectile instanceof Projectile ){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/laser.png");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        this.image = new Image(fichierGuerrier);
        this.imageView = new ImageView(image);
        this.imageView.setId(projectile.getId());
        imageView.xProperty().bind(projectile.xProperty());
        imageView.yProperty().bind(projectile.yProperty());
        this.pane.getChildren().add(imageView);

    }


}