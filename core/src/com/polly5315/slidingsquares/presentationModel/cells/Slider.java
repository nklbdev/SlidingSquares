package com.polly5315.slidingsquares.presentationModel.cells;

import com.polly5315.slidingsquares.model.level.FamilyColor;

import java.util.HashSet;
import java.util.Set;

public class Slider implements ISlider {
    private final FamilyColor _color;
    private SliderState _state;
    private final Set<IListener> _listeners = new HashSet<IListener>();
    private int _x, _y;

    public Slider(FamilyColor color) {
        this(color, SliderState.Idle);
    }

    public Slider(FamilyColor color, SliderState state) {
        _color = color;
        _state = state;
    }

    @Override
    public void Fix() {

    }

    @Override
    public void BlowUp() {

    }

    @Override
    public void Stop() {

    }

    @Override
    public SliderState getState() {
        return _state;
    }

    private void setState(SliderState state) {
        if (state == _state)
            return;
        _state = state;
        for (IListener listener : _listeners)
            listener.onStateChanged(this);
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
    public int getX() {
        return _x;
    }

    @Override
    public int getY() {
        return _y;
    }

    @Override
    public void setPosition(int x, int y) {
        _x = x;
        _y = y;
    }

    @Override
    public FamilyColor getColor() {
        return _color;
    }
}
