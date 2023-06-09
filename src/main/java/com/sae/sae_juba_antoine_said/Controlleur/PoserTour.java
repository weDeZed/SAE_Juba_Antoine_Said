package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.Tour;
import com.sae.sae_juba_antoine_said.Modele.Tours.TroopTour;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class PoserTour {
    private Environnement environnement;
    private int placeDeTour;

    public PoserTour(Environnement environnement) {
        this.environnement = environnement;
        this.placeDeTour = 200;
    }

    public void MettreEnPlaceTourDeplacable(ToggleButton button, Class<? extends Tour> tourtype, Image image) {
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.ANY);
            db.setDragView(image);
            ClipboardContent content = new ClipboardContent();
            content.putString(tourtype.getName());
            db.setContent(content);

        });
    }

    public void MettreEnPlaceZoneDepot(Pane pane) {
        pane.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        });
        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard(); //il prend le nom de class qu'on es en train de glisser
            if (db.hasString()) {
                try {
                    Class<?> tourClass = Class.forName(db.getString());// il prend la class de tour
                    if (placéTourDansBonEndroit((int) event.getX() / 32, (int) event.getY() / 32)) { // si le tour est dans bon endroit
                        Tour tour = (Tour) tourClass.getConstructor(int.class, int.class,int.class, int.class, int.class, Environnement.class).newInstance((int) event.getX(), (int) event.getY(), 800,0, 500, environnement);
                        environnement.ajouterTour(tour);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
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
    public boolean placéTourDansBonEndroit(int x, int y) {
        return environnement.getMap()[x][y + 1] == placeDeTour;
    }

}

