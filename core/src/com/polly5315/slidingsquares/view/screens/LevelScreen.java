package com.polly5315.slidingsquares.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.polly5315.slidingsquares.presentationModel.Direction;
import com.polly5315.slidingsquares.presentationModel.IEngine;
import com.polly5315.slidingsquares.presentationModel.cells.*;
import com.polly5315.slidingsquares.view.actors.*;

public class LevelScreen extends ScreenAdapter {
    private final IEngine _engine;
    //private final BitmapFont _font;
    //private final Batch _batch;
    private final float _secondsPerStep = 0.1f;
    private float _secondsSinceLastStep = 0f;
    private final Stage _stage = new Stage(new ExtendViewport(640, 480, 800, 480));

    public LevelScreen(IEngine engine) {
        if (engine == null)
            throw new IllegalArgumentException("presentationModel cannot be null");
        _engine = engine;

        final Texture idleBombTexture    = new Texture(Gdx.files.internal("sprites/idle-bomb.png"));
        final Texture detonatedBombTexture = new Texture(Gdx.files.internal("sprites/detonated-bomb.png"));
        final Texture idleButtonTexture = new Texture(Gdx.files.internal("sprites/idle-button.png"));
        final Texture pushedButtonTexture = new Texture(Gdx.files.internal("sprites/pushed-button.png"));
        final Texture idleSliderTexture = new Texture(Gdx.files.internal("sprites/idle-slider.png"));
        final Texture fixedSliderTexture = new Texture(Gdx.files.internal("sprites/fixed-slider.png"));
        final Texture blastedSliderTexture = new Texture(Gdx.files.internal("sprites/blasted-slider.png"));
        final Texture openPocketTexture = new Texture(Gdx.files.internal("sprites/open-pocket.png"));
        final Texture closedPocketTexture = new Texture(Gdx.files.internal("sprites/closed-pocket.png"));
        final Texture emptyCellTexture = new Texture(Gdx.files.internal("sprites/empty-cell.png"));

        IEngine.IListener engineListener = new IEngine.IListener() {
            @Override
            public void onStateChanged(IEngine engine) {

            }

            @Override
            public void onEmptyCellAdded(IEngine engine, IEmptyCell cell, int x, int y) {
                _stage.addActor(new EmptyCellActor(x, y, emptyCellTexture));
            }

            @Override
            public void onButtonCellAdded(IEngine engine, IButtonCell cell, int x, int y) {
                _stage.addActor(new ButtonActor(cell, x, y, idleButtonTexture, pushedButtonTexture));
            }

            @Override
            public void onPocketCellAdded(IEngine engine, IPocketCell cell, int x, int y) {
                _stage.addActor(new PocketActor(cell, x, y, openPocketTexture, closedPocketTexture));
            }

            @Override
            public void onBombCellAdded(IEngine engine, IBombCell cell, int x, int y) {
                _stage.addActor(new BombActor(cell, x, y, idleBombTexture, detonatedBombTexture));
            }

            @Override
            public void onSliderAdded(IEngine engine, ISlider slider, int x, int y) {
                _stage.addActor(new SliderActor(slider, idleSliderTexture, fixedSliderTexture, blastedSliderTexture));
            }
        };
        _engine.addListener(engineListener);

        //FreeTypeFontGenerator generator =
        //        new FreeTypeFontGenerator(Gdx.files.internal("unispace.ttf"));
        //FreeTypeFontGenerator.FreeTypeFontParameter parameter =
        //        new FreeTypeFontGenerator.FreeTypeFontParameter();
        //parameter.size = 20;
        //parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
        //parameter.color = Color.WHITE;
        //_font = generator.generateFont(parameter);
        //generator.dispose();
        //_batch = new SpriteBatch();
        InputProcessor inputProcessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                _engine.startTurn(Direction.Left);
                switch (keycode) {
                    case Input.Keys.LEFT:
                        _engine.startTurn(Direction.Left);
                        break;
                    case Input.Keys.RIGHT:
                        _engine.startTurn(Direction.Right);
                        break;
                    case Input.Keys.UP:
                        _engine.startTurn(Direction.Up);
                        break;
                    case Input.Keys.DOWN:
                        _engine.startTurn(Direction.Down);
                        break;
                    default:
                        return false;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _secondsSinceLastStep += delta;
        while (_secondsSinceLastStep >= _secondsPerStep) {
            _secondsSinceLastStep -= _secondsPerStep;
            _engine.doStep();
        }

        //_batch.begin();
        //_font.draw(_batch, "Press any key for turn", 50, 400);
        //_font.draw(_batch, "(after 3-rd turn app will close)", 50, 300);
        ////_font.draw(_batch, "Steps in turn: " + Integer.toString(_engine.getStepCount()), 50, 200);
        //_font.draw(_batch, "Turns: " + Integer.toString(_engine.getTurnCount()), 50, 100);
        //_batch.end();

        _stage.act(delta);
        _stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
