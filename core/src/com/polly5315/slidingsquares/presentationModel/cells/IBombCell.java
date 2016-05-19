package com.polly5315.slidingsquares.presentationModel.cells;

public interface IBombCell extends ICell {
    interface IListener {
        void onDetonated(IBombCell cell);
    }
    boolean isDetonated();
    void addListener(IListener listener);
    void removeListener(IListener listener);
}
