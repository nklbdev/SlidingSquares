package com.polly5315.slidingsquares.presentationModel;

import com.polly5315.slidingsquares.model.Direction;

import java.util.HashSet;
import java.util.Set;

public class LevelPresentationModel implements ILevelPresentationModel {
    private boolean _isCompleted = false;
    private int _turnCount = 0;
    private int _stepCount = 0;
    private boolean _isInTurn = false;
    private int _stepPerTurn = 10;
    private int _maxTurns = 3;
    private Set<IListener> _listeners = new HashSet<IListener>();

    @Override
    public void startTurn(Direction direction) {
        if (_isInTurn || _isCompleted)
            return;
        _isInTurn = true;
    }

    @Override
    public void nextStep() {
        if (!_isInTurn || _isCompleted)
            return;
        _stepCount++;
        if (_stepCount >= _stepPerTurn)
        {
            _isInTurn = false;
            _turnCount++;
            _stepCount = 0;
            for (IListener listener : _listeners)
                listener.onTurnCompleted();
            if (_turnCount >= _maxTurns) {
                _isCompleted = true;
                for (IListener listener : _listeners)
                    listener.onLevelCompleted();
            }
        }
    }

    @Override
    public int getTurnCount() {
        return _turnCount;
    }

    @Override
    public int getStepCount() {
        return _stepCount;
    }

    @Override
    public void addLevelPresentationModelListener(IListener listener) {
        if (listener != null)
            _listeners.add(listener);
    }

    @Override
    public void removeLevelPresentationModelListener(IListener listener) {
        if (listener != null)
            _listeners.remove(listener);
    }
}
