package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Lanceur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.*;
import com.sae.sae_juba_antoine_said.Vue.Music;
import com.sae.sae_juba_antoine_said.Vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
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
import java.io.IOException;
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


    private Timeline gameLoop;

    @FXML
    private Pane pane;


    @FXML
    private ProgressBar progressBar;


    @FXML
    private ToggleButton tourB1, tourB2, tourB3, tourB4;
    @FXML
    Label labelPrixT1, labelPrixT2, labelPrixT3, labelPrixT4;
    private PoserTour dragDropSetup;
    private InventairDesTours inventairDesTours;
    private VueEnvironnement vueEnvironnementMap;

    private ListChangeListener<Acteur> listenerListeActeurs;
    private ListChangeListener<Tour> listenerListeTours;
    private ListObsProjectile listnerListeProjectiles;
    private Music music;

    @FXML
    ImageView imageForTourB1, imageForTourB2, imageForTourB3, imageForTourB4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        music = new Music();


        /************************** Environnement *********************************/

        try {
            this.environnement = new Environnement(90, 90);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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


        listenerListeActeurs = new ListObsActeur(pane, environnement);
        environnement.getActeurs().addListener(listenerListeActeurs);

        listenerListeTours = new ListObsTour(pane, environnement);
        environnement.getTours().addListener(listenerListeTours);

        listnerListeProjectiles = new ListObsProjectile(pane);
        environnement.getProjectiles().addListener(listnerListeProjectiles);


        gameLaunche();
        initAnimation();
        gameLoop.play();


    }

    public void gameLaunche() {
        try {
            this.vueEnvironnementMap = new VueEnvironnement(environnement, tilePane, progressBar, labelEnvPieces);
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
                    try {
                        environnement.unTour();
                    } catch (Exception e) {

                    }
                    if (environnement.getVie() <= 0) {
                        gameLoop.stop();
                        //music.stopMusicFond();
                        //music.PlayMusicDefaite();

                    }



                    temps++;
                })


        );
        gameLoop.getKeyFrames().add(kf);
    }

}


































/*
        tourAProjectile = new TourAProjectile(30 * 16, 34 * 16, 10, 200, environnement);
        environnement.ajouterTour(tourAProjectile);
        vueTour = new VueTour(pane, tourAProjectile);


        //guerrier1 = new Guerrier(100, 10 * 16, 30 * 16, environnement);
        //guerrier2 = new Guerrier(100, 40 * 16, 30 * 16, environnement);
        //environnement.ajouterActeur(guerrier1);
        //environnement.ajouterActeur(guerrier2);
        //vueAct=new VueActeur(pane,guerrier1);
        //vueActeur2 = new VueActeur(pane,guerrier2);


        troopTours = new TroopTour(56 * 16, 27 * 16, 20, 10, environnement);
        troopTours1 = new TroopTour(47 * 16, 10 * 16, 20, 10, environnement);
        vueTour = new VueTour(pane, troopTours);
        vueTour = new VueTour(pane, troopTours1);

        environnement.ajouterTour(troopTours);
        environnement.ajouterTour(troopTours1);

 */



/*  for (int i = 0; i <environnement.getChemin().size(); i++){
            //System.out.println("dans chemin");
            Circle circle =new Circle(environnement.getChemin().get(i).getX()*16,environnement.getChemin().get(i).getY()*16,5, Color.RED);
            pane.getChildren().add(circle);
        }

       */



       /* for (int i = 0; i < environnement.getMap().length;i++){
            for (int j = 0; j<environnement.getMap()[i].length;j++){
               if(environnement.getMap()[i][j]==1427){
                   Circle circle=new Circle(i*16,j*16,7,Color.RED);
                   pane.getChildren().add(circle);
               }
            }
        }

        */





/*
pane.setOnMousePressed(mouseEvent -> {
            // environnement.ajouterActeur(new Guerrier(1,(int) mouseEvent.getX(),(int ) mouseEvent.getY()));
            //environnement.ajouterTour(new Tour((int) mouseEvent.getX(),(int ) mouseEvent.getY(),10,10,environnement));
            System.out.println("x " + (int) mouseEvent.getX() / 16 + " Y " + (int) mouseEvent.getY() / 15 + " poid " + environnement.getMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);

        });
 */






/*
for (Acteur a : environnement.getActeurs()) {
                if(!a.collisionDroit(a,environnement)){
                    System.out.println("droit");
                    a.setX(a.getX()+16);
                    a.setY(a.getY());
                    System.out.println("DrX "+a.getX()+" y "+a.getY());
                }
                if ((!a.collisionHaut(a,environnement))){
                    System.out.println("devant");
                    a.setX(a.getX());
                    a.setY(a.getY()-16);
                    System.out.println("DvX "+a.getX()+" y "+a.getY());
                }
                if(!a.collisionGauche(a,environnement)){
                    System.out.println("gauche");
                    a.setX(a.getX()-16);
                    a.setY(a.getY());
                    System.out.println("GX "+a.getX()+" y "+a.getY());
                }
                a.collisionBas(a,environnement);
          }
*/


 /*
        for (int i =0;i<environnement.getMap().length;i++){
            for (int j=0;j<environnement.getMap()[i].length;j++){
                if(environnement.getMap()[i][j]==1427){
                    pane.getChildren().add(new Circle(i * 16, j * 16, 5, Color.RED));
                }
                if(environnement.getMap()[i][j]==688){
                    pane.getChildren().add(new Circle(i * 16, j * 16, 5, Color.BLACK));
                }
            }
        }
        */

/*
        pane.setOnMousePressed(mouseEvent -> {
            System.out.println("x " + (int) mouseEvent.getX() / 16 + " Y " + (int) mouseEvent.getY() / 15 + " poid " + environnement.getMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);

        });

*/




     /*
        dragDropSetup = new PoserTour(environnement);
        dragDropSetup.setupDraggableTower(tourB1, TroopTour.class, dragDropSetup.envoiImage(0));
        dragDropSetup.setupDraggableTower(tourB2, TourFoudre.class, dragDropSetup.envoiImage(1));
        dragDropSetup.setupDraggableTower(tourB3, LaserTour.class, dragDropSetup.envoiImage(2));
        dragDropSetup.setupDraggableTower(tourB4, ArcTour.class, dragDropSetup.envoiImage(3));
        dragDropSetup.setupDropPane(pane);
    */



/*  pane.setOnMousePressed(mouseEvent -> {
            if (tourB1.isSelected()) {
                System.out.println("b1");
                TroopTour troopTour = new TroopTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(troopTour);

           } else if (tourB2.isSelected()) {
                System.out.println("b2");
                TourFoudre tourFoudre = new TourFoudre((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(tourFoudre);
            } else if (tourB3.isSelected()) {
                System.out.println("b3");
                LaserTour laserTour = new LaserTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(laserTour);
            } else {
                ArcTour arcTour = new ArcTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(arcTour);
            }
        });

*/























