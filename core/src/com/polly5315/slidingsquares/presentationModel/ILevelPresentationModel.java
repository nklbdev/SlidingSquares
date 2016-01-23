package com.polly5315.slidingsquares.presentationModel;

import com.polly5315.slidingsquares.model.Direction;

public interface ILevelPresentationModel {
    interface IListener {
        void onTurnCompleted();
        void onLevelCompleted();
    }
    void startTurn(Direction direction);
    void nextStep();
    int getTurnCount();
    int getStepCount();
    void addLevelPresentationModelListener(IListener listener);
    void removeLevelPresentationModelListener(IListener listener);
}
