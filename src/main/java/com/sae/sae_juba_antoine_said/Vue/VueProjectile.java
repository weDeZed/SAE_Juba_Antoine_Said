package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.util.ArrayList;

public class VueProjectile {
    private Pane pane;
    private ObservableList<Projectile> projectiles;
    private Image image;
    private ImageView imageView;
    private static int x = 0;
    private static int y = 0;



    public VueProjectile(Pane pane, ObservableList<Projectile> projectiles) {
        this.pane = pane;
        this.projectiles = projectiles;

        FileInputStream fichierProjectile = null;

        for (Projectile p : this.projectiles) {
            try {
                fichierProjectile = new FileInputStream("C:\\Users\\jubac\\Desktop\\Programmation\\S2\\TP JAVA\\SAE_Juba_Antoine_Said\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\laser.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (p instanceof Projectile) {
                this.image = new Image(fichierProjectile);
                this.imageView = new ImageView(image);
                imageView.xProperty().bind(p.xProperty());
                imageView.yProperty().bind(p.yProperty());
            }
            this.pane.getChildren().add(imageView);
        }

    }


}
