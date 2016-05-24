package com.polly5315.slidingsquares.presentationModel.cells;

import com.polly5315.slidingsquares.model.level.FamilyColor;
import com.polly5315.slidingsquares.presentationModel.Direction;

import java.util.HashSet;
import java.util.Set;

public class Slider implements ISlider {
    private final ISliderMover _mover;
    private final FamilyColor _color;
    private SliderState _state;
    private final Set<IListener> _listeners = new HashSet<IListener>();
    private int _x, _y;
    private Direction _direction;
    private int _stamina;

    public Slider(FamilyColor color, ISliderMover mover) {
        this(color, SliderState.Idle, mover);
    }

    public Slider(FamilyColor color, SliderState state, ISliderMover mover) {
        if (mover == null)
            throw new IllegalArgumentException("mover cannot be null");
        _color = color;
        _state = state;
        _mover = mover;
    }

    @Override
    public void start(Direction direction) {
        if (_state == SliderState.Idle) {
            setState(SliderState.Sliding);
            _direction = direction;
        }

    }

    @Override
    public void slide() {
        if (_state == SliderState.Sliding && _stamina > 0)
            _mover.moveSlider(this, _x + _direction.x * _stamina, _y + _direction.y * _stamina);
        _stamina = 0;
    }

    @Override
    public void increaseStamina() {
        if (_state == SliderState.Sliding)
            _stamina++;
    }

    @Override
    public void fix() {
        if (_state == SliderState.Sliding)
            setState(SliderState.Fixed);
    }

    @Override
    public void blowUp() {
        if (_state == SliderState.Sliding)
            setState(SliderState.Blasted);
    }

    @Override
    public void stop() {
        if (_state == SliderState.Sliding)
            setState(SliderState.Idle);
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
        if (x == _x && y == _y)
            return;
        _x = x;
        _y = y;
        for (IListener listener : _listeners)
            listener.onPositionChanged(this);
    }

    @Override
    public FamilyColor getColor() {
        return _color;
    }
}
