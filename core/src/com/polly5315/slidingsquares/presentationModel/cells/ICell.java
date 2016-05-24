package com.polly5315.slidingsquares.presentationModel.cells;

public interface ICell {
    void push(ISlider slider);
    ISlider getSlider();
    void setSlider(ISlider slider);
}
