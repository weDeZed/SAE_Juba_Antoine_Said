package com.sae.sae_juba_antoine_said.Controlleur;
import com.sae.sae_juba_antoine_said.Modele.*;
import com.sae.sae_juba_antoine_said.Vue.Vue;
import com.sae.sae_juba_antoine_said.Vue.VueBandit;
import com.sae.sae_juba_antoine_said.Vue.VueGuerrier;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
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

    private Acteur guerrier1;
    private Acteur bandit1;

    private VueGuerrier vueGuerrier;
    private VueBandit vueBandit;

    private  Timeline gameLoop;

    private int temps;

    @FXML
    private Pane pane;

    ListChangeListener<Acteur> listenerListeActeurs;
    ListChangeListener<Tour> listenerListeTours;

    Sommet source, cible;
    ArrayList<Sommet> chemin;


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


        guerrier1 = new Guerrier(50 * 16, 4*16,environnement);

        bandit1 = new Bandit(42,20*16,2,environnement);

        environnement.ajouterActeur(guerrier1);
        environnement.ajouterActeur(bandit1);

        vueGuerrier = new VueGuerrier(pane, guerrier1);
        vueBandit = new VueBandit(pane,bandit1);

        BFS bfs;
        source = environnement.getSommet(28,45 );

        System.out.println("source sommet " + source);

        cible = environnement.getSommet(50, 0);

        System.out.println("sommet cible poid " + cible.getPoids());

        bfs = new BFS(environnement, source);

        this.chemin = bfs.cheminVersSource(cible);

        System.out.println("longeur chemin " + chemin);

        listenerListeActeurs = new ListObsActeur(pane);
        listenerListeTours = new ListObsTour(pane);


        environnement.getActeurs().addListener(listenerListeActeurs);
        environnement.getTours().addListener(listenerListeTours);


        pane.setOnMousePressed(mouseEvent -> {
            environnement.ajouterTour(new Tour((int) mouseEvent.getX(),(int ) mouseEvent.getY(),10,10));
            System.out.println("x " + (int) mouseEvent.getX() / 16 + " Y " + (int) mouseEvent.getY() / 15 + " poid " + environnement.getMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);
        });


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
                    if (temps == 10000) {
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
                        Sommet sommet = chemin.get(i.getAndIncrement());

                        if (bandit1.attaquer() != null){
                            bandit1.agir();
                        }else {
                            bandit1.setX(sommet.getX() * 16);
                            bandit1.setY(sommet.getY() * 16);
                        }

                        //System.out.println(sommet);
                       if (sommet.getY() == cible.getY() && sommet.getX() == cible.getX()) {
                            gameLoop.stop();
                        }

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