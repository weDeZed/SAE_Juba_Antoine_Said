package com.sae.sae_juba_antoine_said.Modele;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class ListObsActeur implements ListChangeListener<Acteur> {


    private Pane panneauDeJeu;

    public ListObsActeur(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
    }

    @Override
    public void onChanged(Change<? extends Acteur> a) {

        Image image;
        ImageView imageView;



        while (a.next()) {


            FileInputStream fichierGuerrier = null;
            for (Acteur act : a.getAddedSubList()) {


                try {
                    fichierGuerrier = new FileInputStream("C:\\Users\\jubac\\Desktop\\Programmation\\S2\\TP JAVA\\SAE_Juba_Antoine_Said\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\saidkamal.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("CHANGEMENT");

                if (act instanceof Guerrier) {
                    image = new Image(fichierGuerrier);
                    imageView = new ImageView(image);
                    imageView.xProperty().bind(act.xProperty());
                    imageView.yProperty().bind(act.yProperty());
                    this.panneauDeJeu.getChildren().add(imageView);
                }


            }

/*
            for (Acteur act : a.getAddedSubList()) {
                try {
                    fichierGuerrier = new FileInputStream("C:\\Users\\jubac\\OneDrive\\Documents\\BUT\\S2\\Java\\SAE_Juba_Antoine_Saidd\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\saidkamal.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (act instanceof Acteur) {
                    System.out.println("CHAGEMENT");
                    image = new Image(fichierGuerrier);
                    imageView = new ImageView(image);
                    panneauDeJeu.getChildren().add(imageView);
                    imageView.xProperty().bind(act.xProperty());
                    imageView.yProperty().bind(act.yProperty());

                }
            }
*/
        }
    }
    }


