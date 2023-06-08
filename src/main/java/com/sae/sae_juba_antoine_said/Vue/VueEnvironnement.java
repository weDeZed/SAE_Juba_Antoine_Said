package com.sae.sae_juba_antoine_said.Vue;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class VueEnvironnement {

    private Environnement env;

    private TilePane tilePane;
    private Image imgTilep;
    private ImageView imgV;

    private BorderPane borderPane;
    private String ch;
    private final int LARGEUR = 32;
    private final int HAUTEUR = 32;


    public VueEnvironnement(Environnement env, TilePane tileP) throws FileNotFoundException {
        this.env = env;
        this.tilePane = tileP;

        iniTerrain();
    }

    public VueEnvironnement() {

    }

    void iniTerrain() {

        FileInputStream fichierTileSet = null;
        try {
            fichierTileSet = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tuileblock.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imgTilep = new Image(fichierTileSet);
        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                imgV = new ImageView(this.imgTilep);
                afficherMap(imgV, this.env.getMap()[j][i]);

            }
        }
    }

    public void afficherMap(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / LARGEUR);
        y = id / ((int) imgTilep.getWidth() / HAUTEUR);
        x = (x * LARGEUR);
        y = (y * HAUTEUR);
        img.setViewport(new Rectangle2D(x, y, LARGEUR, HAUTEUR));
        this.tilePane.getChildren().add(img);
    }

    public void refreshMap(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / LARGEUR);
        y = id / ((int) imgTilep.getHeight() / HAUTEUR);
        x = (x * LARGEUR) - LARGEUR;
        y = (y * HAUTEUR);

        img.setViewport(new Rectangle2D(x, y, LARGEUR, HAUTEUR));
    }


    public TilePane getTilePane() {

        return tilePane;
    }

    public ImageView getImgV() {
        return imgV;
    }

    public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }

}
