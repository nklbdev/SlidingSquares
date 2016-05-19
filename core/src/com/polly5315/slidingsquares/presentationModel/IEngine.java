package com.polly5315.slidingsquares.presentationModel;

import com.polly5315.slidingsquares.model.level.FamilyColor;
import com.polly5315.slidingsquares.presentationModel.cells.*;

import java.util.Collection;

public interface IEngine {
    interface IListener {
        void onStateChanged(IEngine engine);
        void onEmptyCellAdded(IEngine engine, IEmptyCell cell, int x, int y);
        void onButtonCellAdded(IEngine engine, IButtonCell cell, int x, int y);
        void onPocketCellAdded(IEngine engine, IPocketCell cell, int x, int y);
        void onBombCellAdded(IEngine engine, IBombCell cell, int x, int y);
        void onSliderAdded(IEngine engine, ISlider slider, int x, int y);
    }
    void startTurn(Direction direction);
    void doStep();
    EngineState getState();
    void addListener(IListener listener);
    void removeListener(IListener listener);

    int getWidth();
    int getHeight();
    int getTurnCount();

    IEmptyCell addEmptyCell(int x, int y);
    IButtonCell addButtonCell(int x, int y, FamilyColor color, boolean isPushed);
    IPocketCell addPocketCell(int x, int y, FamilyColor color, PocketCellState state);
    IBombCell addBombCell(int x, int y, boolean isDetonated);
    ISlider addSlider(int x, int y, FamilyColor color, SliderState state);

    Collection<IEmptyCell> getEmptyCells();
    Collection<IBombCell> getBombCells();
    Collection<IPocketCell> getPocketCells();
    Collection<IButtonCell> getButtonCells();
    Collection<ISlider> getSliders();
}
