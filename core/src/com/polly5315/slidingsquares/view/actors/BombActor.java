package com.polly5315.slidingsquares.view.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.slidingsquares.presentationModel.cells.IBombCell;

public class BombActor extends Actor implements IBombCell.IListener {
    private final IBombCell _cell;

    public BombActor(IBombCell cell) {
        if (cell == null)
            throw new IllegalArgumentException("cell cannot be null");
        _cell = cell;
        _cell.addListener(this);
    }

    @Override
    public void onDetonated(IBombCell cell) {
        //
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1, 1, 1, parentAlpha);
        //batch.draw(region, x, y, width, height);
    }
}
