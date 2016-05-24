package com.polly5315.slidingsquares.presentationModel.cells;

import com.polly5315.slidingsquares.presentationModel.Direction;

public interface ISlider extends IColored {
    interface IListener {
        void onStateChanged(ISlider slider);
        void onPositionChanged(ISlider slider);
    }
    void start(Direction direction);
    void slide();
    void increaseStamina();

    void fix();
    void blowUp();
    void stop();
    SliderState getState();

    void addListener(IListener listener);
    void removeListener(IListener listener);

    int getX();
    int getY();
    void setPosition(int x, int y);
}
