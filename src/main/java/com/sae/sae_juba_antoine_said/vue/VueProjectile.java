package com.sae.sae_juba_antoine_said.vue;

import com.sae.sae_juba_antoine_said.modele.projectiles.Fleche;
import com.sae.sae_juba_antoine_said.modele.projectiles.Foudre;
import com.sae.sae_juba_antoine_said.modele.projectiles.Laser;
import com.sae.sae_juba_antoine_said.modele.projectiles.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class VueProjectile {
    private Pane pane;

    private Image image;
    private ImageView imageView;
    Circle circle;
    Projectile projectile;
    Music music;

    public VueProjectile(Pane pane, Projectile projectile) {
        music =new Music();
        this.pane = pane;
        this.projectile = projectile;
        music.playLaser();

        FileInputStream fichierGuerrier = null;

        if(projectile instanceof Laser){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/projectilleImg/laser.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if(projectile instanceof Fleche){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/projectilleImg/fleche.png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(projectile instanceof Foudre){
            try {
                fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/projectilleImg/foudre.png");
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
       // System.out.println("image id"+ imageView.getId());

    }


}