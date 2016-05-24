package com.polly5315.slidingsquares.presentationModel.cells;

public abstract class CellBase implements ICell {
    private ISlider _slider;

    @Override
    public ISlider getSlider() {
        return _slider;
    }

    @Override
    public void setSlider(ISlider slider) {
        _slider = slider;
    }
}
