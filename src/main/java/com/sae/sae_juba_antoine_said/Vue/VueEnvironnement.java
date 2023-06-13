package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class VueEnvironnement {


    private ProgressBar progressBar;
    private Environnement env;

    private TilePane tilePane;
    private Image imgTilep;
    private ImageView imgV;
    private final int LARGEUR = 32;
    private final int HAUTEUR = 32;
    private Label labelEnvPieces;


    public VueEnvironnement(Environnement env, TilePane tileP, ProgressBar progressBar, Label labelEnvPieces) throws FileNotFoundException {
        this.env = env;
        this.tilePane = tileP;
        this.progressBar = progressBar;
        this.labelEnvPieces = labelEnvPieces;





        labelEnvPieces.textProperty().bind(env.getPieceProperty().asString());
        iniTerrain();
        mettreImagePieceDansBar();


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
        listObsBarDeEnvronnement();
    }

    public void mettreImagePieceDansBar() {
        FileInputStream fichierGuerrier = null;
        try {
            fichierGuerrier = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/piece.png");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Image image = new Image(fichierGuerrier);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50); // Ajustez la hauteur et la largeur comme nécessaire
        imageView.setFitWidth(50);
        labelEnvPieces.setGraphic(imageView);
    }

    public void listObsBarDeEnvronnement() {

        if (env.getVie() > 20) {
            String barColor = "-fx-accent: red;";
            String trackColor = "-fx-control-inner-background: white;";
            progressBar.setStyle(barColor + trackColor);
        } else {
            progressBar.setStyle("-fx-accent: red;");
        }
        env.vieProperty().addListener((obs, old, newv) -> {
            double nb = Double.valueOf(newv.toString()) / 100;
            progressBar.setProgress(nb);
        });
    }



}
