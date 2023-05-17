package com.sae.sae_juba_antoine_said.Vue;




import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimationG {
    private int temps=0;
    private Timeline gameLoop;
    public AnimationG() {
        this.gameLoop=new Timeline();
    }

    public void initAnimation(ImageView imageView) {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);//combien de fois il doit répéter

        Timeline finalGameLoop = gameLoop;
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev ->{
                    if(temps==1000){
                        System.out.println("fini");
                        finalGameLoop.stop();
                    }
                    else if (temps%10==0){
                        imageView.setLayoutX(imageView.getLayoutX()-2);
                        imageView.setLayoutY(imageView.getLayoutY()-19);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
}
