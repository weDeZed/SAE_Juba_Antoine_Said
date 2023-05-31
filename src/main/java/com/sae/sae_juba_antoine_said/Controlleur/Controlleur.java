package com.sae.sae_juba_antoine_said.Controlleur;
import com.sae.sae_juba_antoine_said.Modele.*;
import com.sae.sae_juba_antoine_said.Vue.InventairDesTours;
import com.sae.sae_juba_antoine_said.Vue.VueEnvironnement;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import com.sae.sae_juba_antoine_said.Vue.Vue;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueProjectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;




public class Controlleur implements Initializable {

    private Environnement environnement;
    @FXML
    private TilePane tilePane;
    private VueEnvironnement vueEnvironnementMap;


    private Acteur guerrier1, guerrier2, guerrier3;
    VueActeur vueAct;

    private  Timeline gameLoop;

    private int temps;
    @FXML
    private Pane pane;

    private Tour troopTours, troopTours1;
    private VueTour vueTour;
    InventairDesTours inventairDesTours;


    @FXML
    private ToggleButton tourB1, tourB2, tourB3, tourB4;

    private PoserTour dragDropSetup;

    ListChangeListener<Acteur> listenerListeActeurs;
    ListChangeListener<Tour> listenerListeTours;

    ListChangeListener<Projectile> listnerListeProjectiles;

    Sommet source, cible, source2;
    ArrayList<Sommet> chemin, chemin2;
    Projectile p ;
    TourAProjectile tourAProjectile;
    VueProjectile vueProjectile;
    VueTour vueTour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try {
            this.environnement = new Environnement(90, 90);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.tilePane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.pane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);


        troopTours = new TroopTour(56 * 16, 27 * 16, 20, 10, environnement);
        troopTours1 = new TroopTour(47 * 16, 10 * 16, 20, 10, environnement);
        vueTour = new VueTour(pane, troopTours);
        vueTour = new VueTour(pane, troopTours1);

        environnement.ajouterTour(troopTours);
        environnement.ajouterTour(troopTours1);


        pane.setOnMousePressed(mouseEvent -> {
            if (tourB1.isSelected()) {
                System.out.println("b1");
                TroopTour troopTour = new TroopTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(troopTour);
                ;
            } else if (tourB2.isSelected()) {
                System.out.println("b2");
                TourFoudre tourFoudre = new TourFoudre((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(tourFoudre);
                ;
            } else if (tourB3.isSelected()) {
                System.out.println("b3");
                LaserTour laserTour = new LaserTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(laserTour);
                ;
            } else {
                ArcTour arcTour = new ArcTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 10, environnement);
                environnement.ajouterTour(arcTour);
                ;
            }
        });


        inventairDesTours = new InventairDesTours(tourB1, tourB2, tourB3, tourB4);




        listenerListeActeurs = new ListObsActeur(pane);
        listenerListeTours = new ListObsTour(pane);
        listnerListeProjectiles = new ListObsProjectile(pane);


        environnement.getActeurs().addListener(listenerListeActeurs);
        environnement.getTours().addListener(listenerListeTours);
        environnement.getProjectiles().addListener(listnerListeProjectiles);




        pane.setOnMousePressed(mouseEvent -> {



            //System.out.println("Acteur ajouté");
          // environnement.ajouterTour(new TourAProjectile((int) mouseEvent.getX(), (int) mouseEvent.getY(), 10, 10,new Projectile((int) mouseEvent.getX(),(int) mouseEvent.getY(),environnement)));
            System.out.println("x " + (int) mouseEvent.getX() / 16 + " Y " + (int) mouseEvent.getY() / 16 + " poid " + environnement.getMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);
          //  environnement.ajouterProjectile(new Projectile((int) mouseEvent.getX(),(int) mouseEvent.getY(),environnement));
            //environnement.ajouterActeur(new Guerrier(1,(int) mouseEvent.getX()+15*16,(int ) mouseEvent.getY()+15*16));

        });





       // p.seDeplacer(guerrier1);

        gameLaunche();
        initAnimation();

        gameLoop.play();


    }

    public void gameLaunche() {
        try {
            this.vueMap = new Vue(environnement, tilePane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        AtomicInteger i = new AtomicInteger();
        AtomicInteger k = new AtomicInteger();


        KeyFrame kf = new KeyFrame(

                Duration.seconds(0.17),

                (ev -> {
                    if (temps % 10 == 0) {
                        environnement.ajouterActeur(new Bandit(52, 24, 3, environnement));
                    }
                    if (temps == 10000) {
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
                        p.deplacerVers(guerrier1);
                        for (Acteur acteur : environnement.getActeurs()) {
                            if (acteur instanceof Ennemi) {
                                ((Ennemi) acteur).move();
                            }
                            if (acteur instanceof Guerrier) {
                                ((Guerrier) acteur).marcherSurChemin();
                                if (acteur.attaquer() != null) {
                                    acteur.agir();
                                }
                            }
                        }
                    }
                    if (temps % 10 == 0) {
                        for (Tour t : environnement.getTours()) {
                            if (t instanceof TroopTour) {
                                t.attaqueEnnemi();
                            }
                        }

                    }
                    if (temps == 120) {
                        troopTours.attaqueEnnemi();
                        troopTours1.attaqueEnnemi();
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}





























































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