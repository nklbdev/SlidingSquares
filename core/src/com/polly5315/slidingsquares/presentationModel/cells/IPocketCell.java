package com.polly5315.slidingsquares.presentationModel.cells;

public interface IPocketCell extends IColoredCell {
    interface IListener {
        void onStateChanged(IPocketCell cell);
    }
    PocketCellState getState();
    void addButtonToListen(IButtonCell buttonCell);
    void addListener(IListener listener);
    void removeListener(IListener listener);
}
