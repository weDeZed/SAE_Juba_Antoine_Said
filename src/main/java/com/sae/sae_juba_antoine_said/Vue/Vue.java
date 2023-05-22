package com.sae.sae_juba_antoine_said.Vue;


import com.sae.sae_juba_antoine_said.Modele.Environnement;
import com.sae.sae_juba_antoine_said.Modele.MapDeEnvironnement;
import com.sae.sae_juba_antoine_said.Modele.Sommet;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Vue {

    private Environnement env;
    private MapDeEnvironnement mapDeEnvironnement;
    private TilePane tilePane;
    private Image imgTilep;
    private ImageView imgV;

    private BorderPane borderPane;
    private String ch;


    public Vue(Environnement env, TilePane tileP) throws FileNotFoundException {
        this.env = env;
        this.tilePane = tileP;
        iniTerrain();
    }

    public Vue() {

    }

    void iniTerrain() {

        FileInputStream fichierTileSet = null;
        try {
            fichierTileSet = new FileInputStream("/Users/saidkamalshinwari/Downloads/BUT-S1/S2/IHM/SAE_Juba_Antoine_Said/src/main/java/com/sae/sae_juba_antoine_said/Ressources/tuileblock.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imgTilep = new Image(fichierTileSet);
        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                imgV = new ImageView(this.imgTilep);
                afficherMap(imgV, this.env.getMap()[i][j]);

            }
        }
    }






    public void afficherMap(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / 16);
        y = id / ((int) imgTilep.getWidth() / 16);
        x = (x * 16);
        y = (y * 16);
        img.setViewport(new Rectangle2D(x, y, 16, 16));
        this.tilePane.getChildren().add(img);
    }

    public void refreshMap(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / 16);
        y = id / ((int) imgTilep.getHeight() / 16);
        x = (x * 16) - 16;
        y = (y * 16);

        img.setViewport(new Rectangle2D(x, y, 16, 16));
    }


    public TilePane getTilePane() {

        return tilePane;
    }

    public ImageView getImage(int x, int y) {
        return (ImageView) tilePane.getChildren().get(y * 40 + x);
    }

    public ImageView getImgV() {
        return imgV;
    }

    public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }

}
