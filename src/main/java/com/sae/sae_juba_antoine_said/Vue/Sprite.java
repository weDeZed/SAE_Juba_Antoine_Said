package com.sae.sae_juba_antoine_said.Vue;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class Sprite extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    public Sprite(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY, int width, int height) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
    }

    @Override
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}

