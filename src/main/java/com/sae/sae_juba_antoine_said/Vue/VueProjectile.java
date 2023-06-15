package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Lanceur;
import com.sae.sae_juba_antoine_said.Modele.Tours.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.net.URL;

public class VueProjectile {
    private Pane pane;

    private Image image;
    private ImageView imageView;
    Music music;
    Circle circle;
    Projectile projectile;

    public VueProjectile(Pane pane, Projectile projectile) {
        music =new Music();
        this.pane = pane;
        this.projectile = projectile;

        FileInputStream fichierGuerrier = null;

        if(projectile instanceof Projectile ){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/projectilleImg/laser.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        music.lancerFlecheSounding();
        this.image = new Image(fichierGuerrier);
        this.imageView = new ImageView(image);
        this.imageView.setId(projectile.getId());
        imageView.xProperty().bind(projectile.xProperty());
        imageView.yProperty().bind(projectile.yProperty());
        this.pane.getChildren().add(imageView);
       // System.out.println("image id"+ imageView.getId());

    }


}