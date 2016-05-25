package com.polly5315.slidingsquares.view.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.polly5315.slidingsquares.presentationModel.cells.ISlider;

public class SliderActor extends Actor {
    private final ISlider _slider;
    private final Texture _idleTexture;
    private final Texture _fixedTexture;
    private final Texture _blastedTexture;
    private Texture _currentTexture;

    public SliderActor(ISlider slider, Texture idleSliderTexture, Texture fixedSliderTexture, Texture blastedSliderTexture, final float secondsPerStep) {
        _slider = slider;
        _idleTexture = idleSliderTexture;
        _fixedTexture = fixedSliderTexture;
        _blastedTexture = blastedSliderTexture;
        setPosition(slider.getX() * 8, slider.getY() * 8);
        _slider.addListener(new ISlider.IListener() {
            @Override
            public void onStateChanged(ISlider slider) {
                switch (_slider.getState()) {
                    case Idle:
                        addAction(Actions.delay(secondsPerStep, Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                _currentTexture = _idleTexture;
                            }
                        })));
                        break;
                    case Blasted:
                        addAction(Actions.delay(secondsPerStep, Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                _currentTexture = _blastedTexture;
                            }
                        })));
                        break;
                    case Fixed:
                        addAction(Actions.delay(secondsPerStep, Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                _currentTexture = _fixedTexture;
                            }
                        })));
                        break;
                    case Sliding:
                        addAction(Actions.delay(secondsPerStep, Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                _currentTexture = _idleTexture;
                            }
                        })));
                        break;
                    default:
                        throw new IllegalStateException("Unsupported state");
                }
            }

            @Override
            public void onPositionChanged(ISlider slider) {
                addAction(Actions.moveTo(slider.getX() * 8, slider.getY() * 8, secondsPerStep));
            }
        });

        switch (_slider.getState()) {
            case Idle:
                    _currentTexture = _idleTexture;
                break;
            case Blasted:
                    _currentTexture = _blastedTexture;
                break;
            case Fixed:
                    _currentTexture = _fixedTexture;
                break;
            case Sliding:
                    _currentTexture = _idleTexture;
                break;
            default:
                throw new IllegalStateException("Unsupported state");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (_currentTexture != null)
            batch.draw(_currentTexture, getX(), getY());
    }
}
