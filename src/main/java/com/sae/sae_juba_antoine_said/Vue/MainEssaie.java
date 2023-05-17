package com.sae.sae_juba_antoine_said.Vue;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainEssaie extends Application {
    private List<Ennemi> ennemis = new ArrayList<>();
    private double baseX = 500, baseY = 200;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        Circle base = new Circle(baseX, baseY, 10, Color.RED);
        root.getChildren().add(base);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (Math.random() < 0.1) {
                    double x = Math.random() * 800;
                    double y = Math.random() * 600;
                    Ennemi ennemi = new Ennemi(x, y, 2, baseX, baseY);
                    ennemis.add(ennemi);
                    Circle circle = new Circle(x, y, 5, Color.BLUE);
                    root.getChildren().add(circle);
                }

                for (Ennemi ennemi : ennemis) {
                    ennemi.update();
                    Circle circle = (Circle) root.getChildren().get(ennemis.indexOf(ennemi) + 1);
                    circle.setCenterX(ennemi.getX());
                    circle.setCenterY(ennemi.getY());
                }
            }
        };
        timer.start();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Ennemi {
    private double x, y;
    private double vitesse;
    private double destinationX, destinationY;

    public Ennemi(double x, double y, double vitesse, double destinationX, double destinationY) {
        this.x = x;
        this.y = y;
        this.vitesse = vitesse;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    public void update() {
        double dx = destinationX - x;
        double dy = destinationY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double directionX = dx / distance;
        double directionY = dy / distance;
        x += directionX * vitesse;
        y += directionY * vitesse;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
