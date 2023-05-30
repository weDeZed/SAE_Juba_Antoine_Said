package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tour;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class PoserTour {
    private Environnement environnement;

    public PoserTour(Environnement environnement) {
        this.environnement = environnement;
    }

    public void setupDraggableTower(ToggleButton button, Class<? extends Tour> tourtype, Image image) {
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.ANY);
            db.setDragView(image);
            ClipboardContent content = new ClipboardContent();
            content.putString(tourtype.getName());
            db.setContent(content);
            event.consume();
        });
    }

    public void setupDropPane(Pane pane) {
        pane.setOnDragOver(event -> {
            if (event.getGestureSource() != pane &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });

        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                try {
                    Class<?> tourClass = Class.forName(db.getString());
                    if (Tour.class.isAssignableFrom(tourClass)) {
                        Tour tour = (Tour) tourClass.getConstructor(int.class, int.class, int.class, int.class, Environnement.class)
                                .newInstance((int) event.getX(), (int) event.getY(), 0, 10, environnement);
                        environnement.ajouterTour(tour);
                        success = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            event.setDropCompleted(success);

            event.consume();
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
}

