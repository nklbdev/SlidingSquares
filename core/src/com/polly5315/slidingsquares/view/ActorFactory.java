package com.polly5315.slidingsquares.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.slidingsquares.presentationModel.cells.*;

public class ActorFactory implements IActorFactory {
    @Override
    public Actor createBombActor(IBombCell cell, int x, int y, float secondsPerStep) {
        return null;
    }

    @Override
    public Actor createButtonActor(IButtonCell cell, int x, int y, float secondsPerStep) {
        return null;
    }

    @Override
    public Actor createEmptyCellActor(IEmptyCell cell, int x, int y) {
        return null;
    }

    @Override
    public Actor createPocketActor(IPocketCell cell, int x, int y, float secondsPerStep) {
        return null;
    }

    @Override
    public Actor createSliderActor(ISlider slider, float secondsPerStep) {
        return null;
    }
}
