package com.sae.sae_juba_antoine_said.Controlleur;

import com.sae.sae_juba_antoine_said.Modele.Environnement.Environnement;
import com.sae.sae_juba_antoine_said.Modele.Tours.*;
import javafx.beans.binding.Bindings;
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

    public PoserTour(Environnement environnement, Pane pane, ToggleButton tourB1, ToggleButton tourB2, ToggleButton tourB3, ToggleButton tourB4) {
        this.environnement = environnement;
        this.placeDeTour = 200;
        MettreEnPlaceTourDeplacable(tourB1, TroopTour.class, envoiImage(0), 150);
        MettreEnPlaceTourDeplacable(tourB2, FoudreTour.class, envoiImage(1), 20);
        MettreEnPlaceTourDeplacable(tourB3, FlecheTour.class, envoiImage(2), 30);
        MettreEnPlaceTourDeplacable(tourB4, LaserTour.class, envoiImage(3), 50);
        MettreEnPlaceZoneDepot(pane);

    }


    public void MettreEnPlaceTourDeplacable(ToggleButton button, Class<? extends Tour> tourtype, Image image, int prix) {
        // Attache la désactivation du bouton à la condition : environnement.getPieceProperty() < prix
        button.disableProperty().bind(Bindings.lessThan(environnement.getPieceProperty(), prix));

        // Lorsque le bouton est détecté en tant que source de glisser-déposer
        button.setOnDragDetected(event -> {
            // Vérifie si le bouton n'est pas désactivé
            if (!button.isDisable()) {
                Dragboard db = button.startDragAndDrop(TransferMode.ANY);
                db.setDragView(image);
                ClipboardContent content = new ClipboardContent();
                content.putString(tourtype.getName()); // Ajoute le nom de la classe du tour dans le contenu
                db.setContent(content);
            }
        });
    }

    public void MettreEnPlaceZoneDepot(Pane pane) {
        // Lorsque l'objet est survolé par un objet glissé-déposé
        pane.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        });

        // Lorsqu'un objet glissé-déposé est lâché sur cet objet
        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard(); // Récupère le contenu de l'objet glissé-déposé
            if (db.hasString()) {
                try {
                    Class<?> tourClass = Class.forName(db.getString()); // Récupère la classe du tour depuis le contenu
                    if (placéTourDansBonEndroit((int) event.getX() / 32, (int) event.getY() / 32)) { // Vérifie si l'emplacement du tour est correct
                        Tour tour = (Tour) tourClass.getConstructor(int.class, int.class, int.class,int.class, Environnement.class)
                                .newInstance((int) event.getX(), (int) event.getY(), 800, 250, environnement); // Crée une nouvelle instance de la classe de tour
                        environnement.ajouterTour(tour); // Ajoute le tour à l'environnement
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
            fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/tourInventair/tour" + i + ".png"); // Charge l'image du tour
        } catch (Exception e) {
            e.printStackTrace();
        }
        image = new Image(fichierTour); // Crée un objet Image à partir du fichier

        return image;
    }

    public boolean placéTourDansBonEndroit(int x, int y) {
        return environnement.getMap()[x][y + 1] == placeDeTour; // Vérifie si l'emplacement est valide dans la carte
    }
}