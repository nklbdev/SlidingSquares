package com.polly5315.slidingsquares.presentationModel.cells;

import com.polly5315.slidingsquares.model.level.FamilyColor;

import java.util.HashSet;
import java.util.Set;

public class PocketCell extends CellBase implements IPocketCell {
    private Set<IListener> _listeners = new HashSet<IListener>();
    private FamilyColor _color;
    private PocketCellState _state;
    private Set<IButtonCell> _buttonCells = new HashSet<IButtonCell>();
    private final IButtonCell.IListener _buttonCellListener = new IButtonCell.IListener() {
        @Override
        public void onPushed(IButtonCell cell) {
            if (_state != PocketCellState.Hidden)
                return;
            boolean allButtonsArePushed = true;
            for (IButtonCell buttonCell : _buttonCells)
                if (!buttonCell.isPushed()) {
                    allButtonsArePushed = false;
                    break;
                }
            if (allButtonsArePushed)
                setState(PocketCellState.Open);
        }
    };

    public PocketCell(FamilyColor color) {
        this(color, PocketCellState.Hidden);
    }

    public PocketCell(FamilyColor color, PocketCellState state) {
        _color = color;
        setState(state);
    }

    @Override
    public void push(ISlider slider) {
        if (_state != PocketCellState.Open)
            return;
        if (slider.getColor() == _color) {
            slider.fix();
            setState(PocketCellState.Closed);
        }
    }

    @Override
    public PocketCellState getState() {
        return _state;
    }

    private void setState(PocketCellState state) {
        if (state == _state)
            return;
        _state = state;
        for (IListener listener : _listeners)
            listener.onStateChanged(this);
    }

    @Override
    public void addButtonToListen(IButtonCell buttonCell) {
        if (buttonCell == null)
            return;
        if (!_buttonCells.add(buttonCell))
            return;
        buttonCell.addListener(_buttonCellListener);
    }

    @Override
    public void addListener(IListener listener) {
        if (listener != null)
            _listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        if (listener != null)
            _listeners.remove(listener);
    }

    @Override
    public FamilyColor getColor() {
        return _color;
    }
}
