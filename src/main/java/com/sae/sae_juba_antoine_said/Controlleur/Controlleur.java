package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Acteurs.Acteur;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Bandit;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Ennemi;
import com.sae.sae_juba_antoine_said.Modele.Acteurs.Guerrier;
import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.*;
import com.sae.sae_juba_antoine_said.Vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class Controlleur implements Initializable {
    private final  int LARGEUR=32;
    private final int HAUTEUR=32;

    private Environnement environnement;
    @FXML
    private TilePane tilePane;

    @FXML
    private Label nbPiece;
    private VueEnvironnement vueEnvironnementMap;


    private Timeline gameLoop;

    private int temps;
    @FXML
    private Pane pane;

    private Tour troopTours, troopTours1;
    private VueTour vueTour;
    InventairDesTours inventairDesTours;

    Projectile p;
    VueActeur vueActeur2;

    VueProjectile vueProjectile;
    private Acteur guerrier1, guerrier2, guerrier3;

    VueActeur vueAct;
    TourAProjectile tourAProjectile;


    @FXML
    private ToggleButton tourB1, tourB2, tourB3, tourB4;

    private PoserTour dragDropSetup;

    ListChangeListener<Acteur> listenerListeActeurs;
    ListChangeListener<Tour> listenerListeTours;
    ListObsProjectile listnerListeProjectiles;
    @FXML
    ImageView imageForTourB1,imageForTourB2,imageForTourB3,imageForTourB4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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



        /************************** Glisser et Poser les Tours *********************************/
        inventairDesTours = new InventairDesTours(imageForTourB1,imageForTourB2,imageForTourB3,imageForTourB4);
        dragDropSetup = new PoserTour(environnement);
        dragDropSetup.MettreEnPlaceTourDeplacable(tourB1, TroopTour.class, dragDropSetup.envoiImage(0),10*16);
        dragDropSetup.MettreEnPlaceTourDeplacable(tourB2, TourFoudre.class, dragDropSetup.envoiImage(1),10);
        dragDropSetup.MettreEnPlaceTourDeplacable(tourB3, LaserTour.class, dragDropSetup.envoiImage(2),10);
        dragDropSetup.MettreEnPlaceTourDeplacable(tourB4, TourAProjectile.class, dragDropSetup.envoiImage(3),10*16);
        dragDropSetup.MettreEnPlaceZoneDepot(pane);

        //imageForTourB1.setImage(createImageView());
        pane.setOnMousePressed(mouseEvent -> {
            // environnement.ajouterActeur(new Guerrier(1,(int) mouseEvent.getX(),(int ) mouseEvent.getY()));
            //environnement.ajouterTour(new Tour((int) mouseEvent.getX(),(int ) mouseEvent.getY(),10,10,environnement));
            System.out.println("x " + (int) mouseEvent.getX() / LARGEUR + " Y " + (int) mouseEvent.getY() / HAUTEUR + " poid " + environnement.getMap()[(int) mouseEvent.getX() / LARGEUR][(int) mouseEvent.getY() / HAUTEUR]);

        });




        /************************** les listes observablesc *********************************/
        listenerListeActeurs = new ListObsActeur(pane, environnement);
        listenerListeTours = new ListObsTour(pane);
        listnerListeProjectiles = new ListObsProjectile(pane);
        environnement.getActeurs().addListener(listenerListeActeurs);
        environnement.getTours().addListener(listenerListeTours);
        environnement.getProjectiles().addListener(listnerListeProjectiles);


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
                    if (temps%100  == 0) {
                        environnement.ajouterActeur(new Bandit(52, 24, 3, environnement));
                    }
                    if (temps %5==1) {
                        environnement.tour();
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
                    if (temps % 10 == 0) {
                        for (Tour t : environnement.getTours()) {
                            if (t instanceof TroopTour) {
                                // t.attaqueEnnemi();
                            }
                        }


                        for (Tour t : environnement.getTours()){
                            System.out.println("avant instto ");
                            if(t instanceof TourAProjectile){
                                System.out.println("tour project ");
                            }
                            environnement.getProjectiles().removeAll();
                            if(t instanceof TourAProjectile) {
                                if (!t.ennemiPlusProche().isEmpty()){
                                    t.creeProjectile();
                                }

                                if(t.ennemiPlusProche().isEmpty()){
                                    environnement.getProjectiles().removeAll();
                                }
                            }

                        }



                    }
                    if(temps %2==0){
                        try {
                            for (Tour tourAP : environnement.getTours()){
                                if (tourAP instanceof TourAProjectile){
                                    tourAP.attaqueEnnemi();
                                }

                            }



                        }catch(Exception e){

                        }



                    }
                    if (temps == 120) {
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }




    private Image createImageView() {
        System.out.println("dans imges view" );
        Image image;
        ImageView imageView;
        FileInputStream fichierTour = null;
        try {
            fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour3.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        image = new Image(fichierTour);

        return image;
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























