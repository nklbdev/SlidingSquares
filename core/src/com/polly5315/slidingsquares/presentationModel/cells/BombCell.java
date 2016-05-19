package com.polly5315.slidingsquares.presentationModel.cells;

import java.util.HashSet;
import java.util.Set;

public class BombCell implements IBombCell {
    private Set<IListener> _listeners = new HashSet<IListener>();
    private boolean _isDetonated = false;

    public BombCell(boolean isDetonated) {
        _isDetonated = isDetonated;
    }

    @Override
    public void Push(ISlider slider) {
        _isDetonated = true;
        for (IListener listener : _listeners)
            listener.onDetonated(this);
        slider.BlowUp();
    }

    @Override
    public boolean isDetonated() {
        return _isDetonated;
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
}
