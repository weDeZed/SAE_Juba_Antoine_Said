package com.sae.sae_juba_antoine_said.Controlleur;
import com.sae.sae_juba_antoine_said.Modele.*;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueEnvironnement;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private VueEnvironnement vueEnvironnementMap;


    private Acteur bandit, guerrier2, guerrier3;

    private VueActeur vueActeur;
    private  Timeline gameLoop;

    private int temps;
    @FXML
    private Pane pane;
    private BFS bfs;
    private  Tour troopTours;
    private VueTour vueTour;


    ListChangeListener<Acteur> listenerListeActeurs;
    ListChangeListener<Tour> listenerListeTours;

    Sommet source, cible, source2;
    ArrayList<Sommet> chemin, chemin2;


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


       /* for (int i = 0; i < environnement.getMap().length;i++){
            for (int j = 0; j<environnement.getMap()[i].length;j++){
               if(environnement.getMap()[i][j]==1427){
                   Circle circle=new Circle(i*16,j*16,7,Color.RED);
                   pane.getChildren().add(circle);
               }
            }
        }

        */


        try {
            bandit = new Bandit(42*16, 2 * 16,  3,environnement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        guerrier2 = new Guerrier(1, 28 * 16, 45 * 16,environnement);

        environnement.ajouterActeur(bandit);
        //environnement.ajouterActeur(guerrier2);
        //vueActeur =new VueActeur(pane,guerrier1);
        vueActeur =new VueActeur(pane,bandit);

        troopTours =new TroopTour(56*16,27*16,20,10,environnement);
        vueTour =new VueTour(pane,troopTours);




        BFS bfs;
        source = environnement.getSommet(28,45 );
        cible = environnement.getSommet(50, 0);
        Circle circle = new Circle(50 * 16, 0 * 16, 10, Color.BLACK);
        pane.getChildren().add(circle);
        bfs = new BFS(environnement, source);
        this.chemin = bfs.cheminVersSource(cible);


        for (Sommet s : chemin) {
            pane.getChildren().add(new Circle(s.getX() * 16, s.getY() * 16, 5, Color.RED));
        }

        listenerListeActeurs = new ListObsActeur(pane);
        listenerListeTours = new ListObsTour(pane);
        environnement.getActeurs().addListener(listenerListeActeurs);
        environnement.getTours().addListener(listenerListeTours);


        pane.setOnMousePressed(mouseEvent -> {
          // environnement.ajouterActeur(new Guerrier(1,(int) mouseEvent.getX(),(int ) mouseEvent.getY()));
            //environnement.ajouterTour(new Tour((int) mouseEvent.getX(),(int ) mouseEvent.getY(),10,10,environnement));
            System.out.println("x " + (int)mouseEvent.getX() / 16 + " Y " +  (int)mouseEvent.getY() / 15 + " poid " + environnement.getMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);

        });





        gameLaunche();
        initAnimation();

        gameLoop.play();


    }

    public void gameLaunche() {
        try {
            this.vueEnvironnementMap = new VueEnvironnement(environnement, tilePane);
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
                    } else if (temps % 2 == 0) {
                        Sommet sommet = chemin.get(i.getAndIncrement());
                        bandit.setX(sommet.getX() * 16-8);
                        bandit.setY(sommet.getY() * 16-8);
                       if (sommet.getY() == cible.getY() && sommet.getX() == cible.getX()) {
                            gameLoop.stop();
                        }



                    }
                    if(temps==1){
                        troopTours.attaqueEnnemi();
                    }
                    if(temps%20==0){
                        for (Acteur a:environnement.getActeurs()) {
                            if (a instanceof Guerrier){
                                a.agir();
                            }
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