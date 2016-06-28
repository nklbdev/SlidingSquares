package com.polly5315.slidingsquares.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.slidingsquares.presentationModel.cells.*;

public interface IActorFactory {
    Actor createBombActor(IBombCell cell, int x, int y, float secondsPerStep);
    Actor createButtonActor(IButtonCell cell, int x, int y, float secondsPerStep);
    Actor createEmptyCellActor(IEmptyCell cell, int x, int y);
    Actor createPocketActor(IPocketCell cell, int x, int y, float secondsPerStep);
    Actor createSliderActor(ISlider slider, float secondsPerStep);
}
