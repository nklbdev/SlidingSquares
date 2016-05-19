package com.polly5315.slidingsquares.presentationModel;

import com.polly5315.slidingsquares.presentationModel.cells.ISlider;

public interface IIdleSliderMonitor extends ISlider.IListener {
    int getIdleSlidersCount();
    void addSlider(ISlider slider);
}
