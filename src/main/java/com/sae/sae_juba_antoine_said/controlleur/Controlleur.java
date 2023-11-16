package com.sae.sae_juba_antoine_said.controlleur;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import com.sae.sae_juba_antoine_said.vue.Music;
import com.sae.sae_juba_antoine_said.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;


import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class Controlleur implements Initializable {
    @FXML
    private Button lancerButton;
    private final int LARGEUR = 32;
    private final int HAUTEUR = 32;
    private int temps;

    private Environnement environnement;
    @FXML
    private TilePane tilePane;

    @FXML
    private Label labelEnvPieces;
    private VueEnvironnement vueEnvironnementMap;


    private Timeline gameLoop;

    @FXML
    private Pane pane;

    InventairDesTours inventairDesTours;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private ToggleButton tourB1, tourB2, tourB3, tourB4;
    @FXML
    Label labelPrixT1, labelPrixT2, labelPrixT3, labelPrixT4;


    private PoserTour dragDropSetup;

    private ListObsProjectile listnerListeProjectiles;
    private Music music;

    @FXML
    ImageView imageForTourB1, imageForTourB2, imageForTourB3, imageForTourB4;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**************** musicn ***************/
        music = new Music();


        /************************** Environnement *********************************/


        this.environnement = Environnement.getEnvironnementInstance();

        /**************** class vague ***************/

        environnement.getVagueEnnemi().nbVagueProperty().bind(environnement.getVagueEnnemi().nbVagueBindingProperty());


        this.tilePane.setMinSize(environnement.getX() * LARGEUR, environnement.getY() * HAUTEUR);
        this.tilePane.setMaxSize(environnement.getX() * LARGEUR, environnement.getY() * HAUTEUR);
        this.tilePane.setPrefSize(environnement.getX() * LARGEUR, environnement.getY() * HAUTEUR);
        this.pane.setMinSize(environnement.getX() * LARGEUR, environnement.getY() * HAUTEUR);
        this.pane.setMaxSize(environnement.getX() * LARGEUR, environnement.getY() * HAUTEUR);
        this.pane.setPrefSize(environnement.getX() * LARGEUR, environnement.getY() * HAUTEUR);

        /************************** Glisser et Poser les Tours  *********************************/

        inventairDesTours = new InventairDesTours(imageForTourB1, imageForTourB2, imageForTourB3, imageForTourB4);
        dragDropSetup = new PoserTour(environnement, pane, tourB1, tourB2, tourB3, tourB4);


        /************************** les listes observables  *********************************/


        environnement.getActeurs().addListener(new ListObsActeur(pane, environnement));

        environnement.getTours().addListener(new ListObsTour(pane, environnement));

        listnerListeProjectiles = new ListObsProjectile(pane);
        environnement.getProjectiles().addListener(listnerListeProjectiles);


        gameLaunche();
        initAnimation();
        gameLoop.play();


    }

    public void gameLaunche() {
        try {
            this.vueEnvironnementMap = new VueEnvironnement(environnement, tilePane, progressBar, pane, labelEnvPieces);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initAnimation() {

        gameLoop = new Timeline();
        temps = 1;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        AtomicInteger i = new AtomicInteger();


        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.17),
                (ev -> {
                    if (music.verifSon() == false) {
                        music.playMusicFond();
                    }
                    try {
                        environnement.unTour();
                    } catch (Exception e) {

                    }

                    if (environnement.getVie() <= 0) {
                        gameLoop.stop();
                        environnement.mettreEnvInstanceNull();
                        vueEnvironnementMap.afficherGameOverScene();

                    }

                    if (environnement.getVagueEnnemi().getNbVague() == 10) { // après dix vague on gagne le jeu
                        gameLoop.stop();
                        vueEnvironnementMap.afficherGameWinScene();
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}


