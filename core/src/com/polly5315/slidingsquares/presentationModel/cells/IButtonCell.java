package com.polly5315.slidingsquares.presentationModel.cells;

public interface IButtonCell extends IColoredCell {
    interface IListener {
        void onPushed(IButtonCell cell);
    }
    boolean isPushed();
    void addListener(IListener listener);
    void removeListener(IListener listener);
}
