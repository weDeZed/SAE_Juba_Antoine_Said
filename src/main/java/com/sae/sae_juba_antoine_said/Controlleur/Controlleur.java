package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.*;
import com.sae.sae_juba_antoine_said.Vue.InventairTour;
import com.sae.sae_juba_antoine_said.Vue.VueActeur;
import com.sae.sae_juba_antoine_said.Vue.VueEnvironnement;
import com.sae.sae_juba_antoine_said.Vue.VueTour;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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
    private Timeline gameLoop;

    private int temps;
    @FXML
    private Pane pane;
    private BFS bfs;
    private Tour troopTours;
    private VueTour vueTour;

    @FXML
    GridPane gridpane;
    InventairTour inventairTour;
    @FXML
    private RowConstraints tour1;

    @FXML
    private RowConstraints tour2;

    @FXML
    private RowConstraints tour3;

    @FXML
    private RowConstraints tour4;

    @FXML
    private ToggleButton tourB1, tourB2, tourB3, tourB4;

    ListChangeListener<Acteur> listenerListeActeurs;
    ListChangeListener<Tour> listenerListeTours;


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
        vueTour = new VueTour(pane, troopTours);

        /*Image image1 = new Image("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tour.png"); // Spécifiez le chemin d'accès à l'image 1
        ImageView imageView1 = new ImageView(image1);
        tourB1.setGraphic(imageView1);

         */

        pane.setOnMousePressed(mouseEvent -> {
            if (tourB1.isSelected()) {
                System.out.println("b1");
                TroopTour troopTour = new TroopTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 23, environnement);
                environnement.ajouterTour(troopTour);
                ;
            } else if (tourB2.isSelected()) {
                System.out.println("b2");
                TondreTour tondreTour = new TondreTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 23, environnement);
                environnement.ajouterTour(tondreTour);
                ;
            } else if (tourB3.isSelected()) {
                System.out.println("b3");
                LaserTour laserTour=new LaserTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 23, environnement);
                environnement.ajouterTour(laserTour);;
            } else {
                ArcTour arcTour=new ArcTour((int) mouseEvent.getX(), (int) mouseEvent.getY(), 0, 23, environnement);
                environnement.ajouterTour(arcTour);;
            }
            //System.out.println("x " + (int) mouseEvent.getX() / 16 + " Y " + (int) mouseEvent.getY() / 15 + " poid " + environnement.getMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);
        });


        listenerListeActeurs = new ListObsActeur(pane);
        listenerListeTours = new ListObsTour(pane);
        environnement.getActeurs().addListener(listenerListeActeurs);
        environnement.getTours().addListener(listenerListeTours);

        //ImageView imageView1 = (ImageView) gridpane.getChildren().get(1);

        inventairTour = new InventairTour(gridpane, tourB1, tourB2, tourB3, tourB4);
        //inventairTour.mettreImg();
        //gridpane.getChildren().add(imageView1);
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


        KeyFrame kf = new KeyFrame(


                Duration.seconds(0.17),

                (ev -> {
                    if (temps % 10 == 0) {
                        environnement.ajouterActeur(new Bandit(52, 24, 3, environnement));
                    }
                    if (temps == 10000) {
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
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
                    if (temps % 50 == 0) {
                        for (Tour t : environnement.getTours()) {
                            if (t instanceof TroopTour) {
                                t.attaqueEnnemi();
                            }
                        }

                    }
                    if (temps == 120) {
                        troopTours.attaqueEnnemi();
                    }
                    temps++;
                })
        );


        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void tour1(MouseEvent mouseEvent) {
        //tour1.setFillHeight(false);
        System.out.println("tour1 " + tour1);
        if (tour1.isFillHeight() == false) {
            // tour1.setFillHeight(true);
        }
    }

    @FXML
    public void tour2(MouseEvent mouseEvent) {
        //tour2.setFillHeight(false);
        System.out.println("tour 2 " + tour2);
    }

    @FXML
    public void tour3(MouseEvent mouseEvent) {
        //tour3.setFillHeight(false);
        System.out.println("tour 3 " + tour3);
    }

    @FXML
    public void tour4(MouseEvent mouseEvent) {
        tour4.setFillHeight(false);
        System.out.println("tour 4 " + tour4);
    }
}

















































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