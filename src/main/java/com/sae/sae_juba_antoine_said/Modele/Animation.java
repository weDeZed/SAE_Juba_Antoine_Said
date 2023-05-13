package com.sae.sae_juba_antoine_said.Modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Animation {
    private int temps=0;

    public Animation() {
    }

    public void initAnimation(Timeline gameLoop, Circle leCercle) {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);//combien de fois il doit répéter

        Timeline finalGameLoop = gameLoop;
        KeyFrame kf = new KeyFrame(

                Duration.seconds(0.017),
                (ev ->{
                    if(temps==10000){
                        System.out.println("fini");
                        finalGameLoop.stop();
                    }
                    else if (temps%10==0){
                        System.out.println("un tour");
                        leCercle.setLayoutX(leCercle.getLayoutX()+1);
                        leCercle.setLayoutY(leCercle.getLayoutY()+1);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
}
