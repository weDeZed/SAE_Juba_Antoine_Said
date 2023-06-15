package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Modele.Tours.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.FileInputStream;

public class VueTour {

    private Pane pane;
    private Tour tours;
    private Image image;
    private ImageView imageView;
    Line fléche;


    public VueTour(Pane pane, Tour tour) {
        this.pane = pane;
        this.tours = tours;

        FileInputStream fichierTour = null;

        if (tour instanceof TroopTour) {
           this.image=envoiImage(0);

        }
        if (tour instanceof TourFoudre) {
            this.image=envoiImage(1);
        }
        if (tour instanceof LaserTour) {
            this.image=envoiImage(2);
        }
        if (tour instanceof TourAProjectile) {
            this.image=envoiImage(3);
        }
        int decalageH = ((int) image.getHeight())/2;
        int decalageL = (int) image.getWidth()/2;
        this.imageView = new ImageView(image);

        imageView.setLayoutX(tour.getX()-decalageL);
        imageView.setLayoutY(tour.getY()-decalageH);
        this.pane.getChildren().add(imageView);

    }
    public Image envoiImage(int i) {
        Image image;

        FileInputStream fichierTour = null;
        try {
            fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour" + i + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        image = new Image(fichierTour);

        return image;
    }

}

















/*    Circle rangeCircle = new Circle(tour.getX(), tour.getY(), tour.getRange() * 16);
        rangeCircle.setFill(Color.TRANSPARENT); // transparent à l'intérieur
        rangeCircle.setStroke(Color.RED); // bordure rouge
        rangeCircle.setStrokeWidth(2);
        pane.getChildren().add(rangeCircle);


        fléche = new Line(0, 0, 0, -tours.getRange());
        fléche.setStroke(Color.BLACK);
        fléche.setStrokeWidth(2);
        fléche.setEndY(-2 * tours.getRange() * 8);




        //Positionner la flèche au centre de la tour
        fléche.setLayoutX(tours.getX());
        fléche.setLayoutY(tours.getY());
        pane.getChildren().add(fléche);
        initAnimation();

 */


/*

    public Line getFléche() {
        return fléche;
    }

    public void initAnimation() {
        Rotate rotation = new Rotate();
        fléche.getTransforms().add(rotation);

// Créer une timeline pour animer la rotation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(rotation.angleProperty(), 360))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

     */
/*
package com.sae.sae_juba_antoine_said;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


import javax.sound.sampled.*;
import java.io.File;

import java.io.IOException;
import java.net.URL;

public class Lanceur extends Application {
    private static Clip clipFond;      // Variable pour la musique de fond
    private static Clip clipVictoire;  // Variable pour la musique de victoire
    private static Clip clipDefaite;   // Variable pour la musique de défaite

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Chargement du fichier FXML pour la scène du menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vueSae.fxml"));
        // Création de la scène avec la taille spécifiée
        Scene scene = new Scene(fxmlLoader.load(), 1400, 800);
        // Configuration de la fenêtre principale
        primaryStage.setResizable(false);
        primaryStage.setTitle("Zombie Survival : La Dernière Lueur d'Espoir");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Récupération de l'URL de l'image du fond du son
        URL urlImageVaiL = Lanceur.class.getResource("fire_laser.wav");
        // Obtention du chemin de l'URL en tant que chaîne de caractères
        String s = urlImageVaiL.getPath();
        // Lecture du fichier audio de fond
        PlayMusicFond(s);
        // Lancement de l'application
        launch();
    }

    public static void PlayMusicFond(String location) {
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipFond = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipFond.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de fond
        clipFond.start();
    }

    public static boolean verifSon() {
        // Vérification si le clip de musique de fond est en cours de lecture
        if (!clipFond.isRunning()) {
            return false;
        }
        return true;
    }

    public static void stopMusicFond() {
        // Arrêt et fermeture du clip de musique de fond s'il est en cours de lecture
        if (clipFond != null && clipFond.isRunning()) {
            clipFond.stop();
            clipFond.close();
        }
    }

    public static void PlayMusicVictoire(String location) {
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipVictoire = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipVictoire.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de victoire
        clipVictoire.start();
    }

    public static void stopMusicVictoire() {
        // Arrêt et fermeture du clip de musique de victoire s'il est en cours de lecture
        if (clipVictoire != null && clipVictoire.isRunning()) {
            clipVictoire.stop();
            clipVictoire.close();
        }
    }

    public static void PlayMusicDefaite(String location) {
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipDefaite = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipDefaite.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de défaite
        clipDefaite.start();
    }

    public static void stopMusicDefaite() {
        // Arrêt et fermeture du clip de musique de défaite s'il est en cours de lecture
        if (clipDefaite != null && clipDefaite.isRunning()) {
            clipDefaite.stop();
            clipDefaite.close();
        }
    }
}

 */