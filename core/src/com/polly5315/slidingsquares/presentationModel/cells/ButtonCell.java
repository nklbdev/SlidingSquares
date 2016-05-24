package com.polly5315.slidingsquares.presentationModel.cells;

import com.polly5315.slidingsquares.model.level.FamilyColor;

import java.util.HashSet;
import java.util.Set;

public class ButtonCell extends CellBase implements IButtonCell {
    private final Set<IListener> _listeners = new HashSet<IListener>();
    private boolean _isPushed = false;
    private final FamilyColor _color;

    public ButtonCell(FamilyColor color, boolean isPushed) {
        _color = color;
        _isPushed = isPushed;
    }

    @Override
    public void push(ISlider slider) {
        if (_isPushed || slider.getColor() != _color) return;

        _isPushed = true;
        for (IListener listener : _listeners)
            listener.onPushed(this);
    }

    @Override
    public boolean isPushed() {
        return _isPushed;
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
