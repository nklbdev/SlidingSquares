package com.polly5315.slidingsquares.presentationModel;

import com.polly5315.slidingsquares.model.level.Level;

public interface ITurnBasedEngineFactory {
    IEngine create(Level level);
}
