package com.polly5315.slidingsquares.view.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EmptyCellActor extends Actor {
    private final TextureRegion _texture;

    public EmptyCellActor(int x, int y, TextureRegion emptyCellTexture) {
        setSize(1, 1);
        setPosition(x, y);
        _texture = emptyCellTexture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(_texture, getX(), getY(), getWidth(), getHeight());
    }
}
