package com.sae.sae_juba_antoine_said;

import com.sae.sae_juba_antoine_said.Modele.Sprite;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Rectangle2D;

import java.io.FileInputStream;

public class MainGrille extends Application {


	//private static final String IMAGE_URL = "enemy/e4/dir.png";

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Sprite Animation Example");

		final ImageView imageView = new ImageView(envoiImage());
		imageView.setViewport(new Rectangle2D(0, 0, 48, 48));
		final Sprite animation = new Sprite(

				imageView,
				Duration.millis(500),
				2, 2,
				0, 90,
				60, 48
		);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		primaryStage.setScene(new Scene(new Group(imageView), 800, 450));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Image envoiImage(){
		Image image;

		FileInputStream fichierTour = null;
		try {
			fichierTour = new FileInputStream("src/main/java/com/sae/sae_juba_antoine_said/Ressources/enemy/e4/dir.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		image = new Image(fichierTour);

		return image;
	}
}
