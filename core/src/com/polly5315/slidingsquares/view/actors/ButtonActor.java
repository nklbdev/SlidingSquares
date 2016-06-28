package com.polly5315.slidingsquares.view.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.polly5315.slidingsquares.presentationModel.cells.IButtonCell;

public class ButtonActor extends Actor {
    private final IButtonCell _cell;
    private TextureRegion _currentTexture;

    public ButtonActor(IButtonCell cell, int x, int y, TextureRegion idleButtonTexture, final TextureRegion pushedButtonTexture, final float secondsPerStep) {
        setSize(1, 1);
        if (cell == null)
            throw new IllegalArgumentException("cell cannot be null");
        if (idleButtonTexture == null)
            throw new IllegalArgumentException("idleButtonTexture cannot be null");
        if (pushedButtonTexture == null)
            throw new IllegalArgumentException("pushedButtonTexture cannot be null");
        _cell = cell;
        setPosition(x, y);
        _currentTexture = cell.isPushed() ? pushedButtonTexture : idleButtonTexture;
        _cell.addListener(new IButtonCell.IListener() {
            @Override
            public void onPushed(IButtonCell cell) {
                addAction(Actions.delay(secondsPerStep, Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        _currentTexture = pushedButtonTexture;
                    }
                })));
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(_currentTexture, getX(), getY(), getWidth(), getHeight());
    }
}
