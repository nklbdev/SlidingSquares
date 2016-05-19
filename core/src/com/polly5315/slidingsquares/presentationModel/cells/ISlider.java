package com.polly5315.slidingsquares.presentationModel.cells;

public interface ISlider extends IColored {
    interface IListener {
        void onStateChanged(ISlider slider);
        void onPositionChanged(ISlider slider);
    }
    //получить состояние
    //поймать
    void Fix();
    //взорвать
    void BlowUp();
    //остановить
    void Stop();
    SliderState getState();

    void addListener(IListener listener);
    void removeListener(IListener listener);

    int getX();
    int getY();
    void setPosition(int x, int y);
}
