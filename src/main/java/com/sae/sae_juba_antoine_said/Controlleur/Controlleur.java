package com.sae.sae_juba_antoine_said.Controlleur;
import com.sae.sae_juba_antoine_said.Modele.*;
import com.sae.sae_juba_antoine_said.Vue.Vue;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueProjectile;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
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
    private Vue vueMap;


    private Acteur guerrier1, guerrier2, guerrier3;
    VueActeur vueAct;

    private  Timeline gameLoop;

    private int temps;
    @FXML
    private Pane pane;
    private BFS bfs;

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


       p = new Projectile(44,23,environnement);
       environnement.ajouterProjectile(p);
       vueProjectile = new VueProjectile(pane,p);
        System.out.println("Liste obs taille : " + environnement.getProjectiles().get(0).getId());


        guerrier1 = new Guerrier(1, 15*16 , 12*16 );
        environnement.ajouterActeur(guerrier1);
        vueAct=new VueActeur(pane,guerrier1);



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
        temps = 1;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        AtomicInteger i = new AtomicInteger();
        AtomicInteger k = new AtomicInteger();


        KeyFrame kf = new KeyFrame(

                Duration.seconds(0.17),

                (ev -> {
                    if (temps == 10000) {
                        gameLoop.stop();
                    } else if (temps % 1 == 0) {


                     //   System.out.println("Coord projectile avant = " + p.getX()+" " + p.getY());
                       p.deplacerVers(guerrier1);
                        //tourAProjectile.lancerProjectile();
                    //    System.out.println("Coord projectile apres= " + p.getX()+" " + p.getY());



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