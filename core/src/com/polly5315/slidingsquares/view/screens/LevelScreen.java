package com.polly5315.slidingsquares.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.polly5315.slidingsquares.presentationModel.Direction;
import com.polly5315.slidingsquares.presentationModel.EngineState;
import com.polly5315.slidingsquares.presentationModel.IEngine;
import com.polly5315.slidingsquares.presentationModel.cells.*;
import com.polly5315.slidingsquares.view.actors.*;

public class LevelScreen extends ScreenAdapter {
    private final IEngine _engine;
    //private final BitmapFont _font;
    //private final Batch _batch;
    private final float _secondsPerStep = 0.05f;
    private float _secondsSinceLastStep = 0f;
    private final Stage _stage;
    private final Group _boardGroup = new Group();
    private final Group _sliderGroup = new Group();
    private final Group _cellGroup = new Group();

    private final float _viewportWidth = 240;
    private final float _viewportHeight = 320;

    private final float _levelNumberLabelX = 8;
    private final float _levelNumberLabelY = 280;
    private final float _levelNumberLabelWidth = 108;
    private final float _levelNumberLabelHeight = 32;

    private final float _turnCountLabelX = 124;
    private final float _turnCountLabelY = 280;
    private final float _turnCountLabelWidth = 108;
    private final float _turnCountLabelHeight = 32;

    private final float _backButtonX = 8;
    private final float _backButtonY = 8;
    private final float _backButtonWidth = 108;
    private final float _backButtonHeight = 32;

    private final float _replayButtonX = 124;
    private final float _replayButtonY = 8;
    private final float _replayButtonWidth = 108;
    private final float _replayButtonHeight = 32;

    private final float _boardBaseX = 8;
    private final float _boardBaseY = 48;
    private final float _boardBaseWidth = 224;
    private final float _boardBaseHeight = 224;

    public LevelScreen(IEngine engine) {
        if (engine == null)
            throw new IllegalArgumentException("presentationModel cannot be null");
        _engine = engine;

        final int boardColumnsCount = _engine.getWidth();
        final int boardRowsCount = _engine.getHeight();
        final float boardRatio = (float)boardColumnsCount / boardRowsCount;
        final float boardBaseRatio = _boardBaseWidth / _boardBaseHeight;
        final float boardWidth = boardRatio >= boardBaseRatio ? _boardBaseWidth : boardBaseRatio * boardRatio * _boardBaseWidth;
        final float boardHeight = boardRatio <= boardBaseRatio ? _boardBaseHeight : boardBaseRatio * _boardBaseHeight / boardRatio;
        final float boardX = _boardBaseX + (_boardBaseWidth - boardWidth) / 2;
        final float boardY = _boardBaseY + (_boardBaseHeight - boardHeight) / 2;
        final float cellSize = boardWidth / boardColumnsCount;

        Camera camera = new OrthographicCamera();
        Viewport viewport = new ExtendViewport(_viewportWidth, _viewportHeight, _viewportWidth, _viewportHeight, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        _stage = new Stage(viewport);
        _stage.addActor(_boardGroup);

        _boardGroup.setScale(cellSize);
        _boardGroup.setPosition(boardX, boardY);
        _boardGroup.addActor(_cellGroup);
        _boardGroup.addActor(_sliderGroup);


        TextureRegion orangeSlider = draftAtlas.findRegion("OrangeSlider");
        final TextureRegion pinkSlider = draftAtlas.findRegion("PinkSlider");
        TextureRegion greenSlider = draftAtlas.findRegion("GreenSlider");
        final TextureRegion yellowSlider = draftAtlas.findRegion("YellowSlider");
        final TextureRegion blueSlider = draftAtlas.findRegion("BlueSlider");
        TextureRegion purpleSlider = draftAtlas.findRegion("PurpleSlider");

        TextureRegion orangePocket = draftAtlas.findRegion("OrangePocket");
        final TextureRegion pinkPocket = draftAtlas.findRegion("PinkPocket");
        TextureRegion greenPocket = draftAtlas.findRegion("GreenPocket");
        final TextureRegion yellowPocket = draftAtlas.findRegion("YellowPocket");
        TextureRegion bluePocket = draftAtlas.findRegion("BluePocket");
        TextureRegion purplePocket = draftAtlas.findRegion("PurplePocket");
        final TextureRegion closedPocket = draftAtlas.findRegion("ClosedPocket");
        final TextureRegion hiddenPocket = draftAtlas.findRegion("HiddenPocket");

        TextureRegion orangeButton = draftAtlas.findRegion("OrangeButton");
        final TextureRegion pinkButton = draftAtlas.findRegion("PinkButton");
        TextureRegion greenButton = draftAtlas.findRegion("GreenButton");
        final TextureRegion yellowButton = draftAtlas.findRegion("YellowButton");
        final TextureRegion blueButton = draftAtlas.findRegion("BlueButton");
        TextureRegion purpleButton = draftAtlas.findRegion("PurpleButton");
        final TextureRegion pushedButton = draftAtlas.findRegion("PushedButton");

        final TextureRegion mine = draftAtlas.findRegion("Mine");
        final TextureRegion blast = draftAtlas.findRegion("Blast");
        final TextureRegion emptyCell = draftAtlas.findRegion("EmptyCell");

        //final Button levelNumberButton = new Button(buttonMockRegionDrawable);
        //levelNumberButton.setPosition(_levelNumberLabelX, _levelNumberLabelY);
        //levelNumberButton.setSize(_levelNumberLabelWidth, _levelNumberLabelHeight);
        //_stage.addActor(levelNumberButton);

        //final Button turnCountButton = new Button(buttonMockRegionDrawable);
        //turnCountButton.setPosition(_turnCountLabelX, _turnCountLabelY);
        //turnCountButton.setSize(_turnCountLabelWidth, _turnCountLabelHeight);
        //_stage.addActor(turnCountButton);

        //final Button backButton = new Button(buttonMockRegionDrawable);
        //backButton.setPosition(_backButtonX, _backButtonY);
        //backButton.setSize(_backButtonWidth, _backButtonHeight);
        //_stage.addActor(backButton);

        //final Button replayButton = new Button(buttonMockRegionDrawable);
        //replayButton.setPosition(_replayButtonX, _replayButtonY);
        //replayButton.setSize(_replayButtonWidth, _replayButtonHeight);
        //_stage.addActor(replayButton);

        IEngine.IListener engineListener = new IEngine.IListener() {
            @Override
            public void onStateChanged(IEngine engine) {
                //EngineState engineState = _engine.getState();
                //if (engineState == EngineState.Winned) {
                //    Image image = new Image(emptyCellTexture) {{
                //        setPosition(100, 100);
                //    }};
                //    _stage.addActor(image);
                //} else if (engineState == EngineState.Defeated) {
                //    Image image = new Image(blastedSliderTexture) {{
                //        setPosition(100, 100);
                //    }};
                //    _stage.addActor(image);
                //}
            }

            @Override
            public void onEmptyCellAdded(IEngine engine, IEmptyCell cell, int x, int y) {
                _cellGroup.addActor(new EmptyCellActor(x, y, emptyCell));
            }

            @Override
            public void onButtonCellAdded(IEngine engine, IButtonCell cell, int x, int y) {
                _cellGroup.addActor(new ButtonActor(cell, x, y, pinkButton, pushedButton, _secondsPerStep));
            }

            @Override
            public void onPocketCellAdded(IEngine engine, IPocketCell cell, int x, int y) {
                _cellGroup.addActor(new PocketActor(cell, x, y, pinkPocket, closedPocket, hiddenPocket, _secondsPerStep));
            }

            @Override
            public void onBombCellAdded(IEngine engine, IBombCell cell, int x, int y) {
                _cellGroup.addActor(new BombActor(cell, x, y, mine, blast, _secondsPerStep));
            }

            @Override
            public void onSliderAdded(IEngine engine, ISlider slider, int x, int y) {
                _sliderGroup.addActor(new SliderActor(slider, pinkSlider, null, null, _secondsPerStep));
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
        _stage.getViewport().update(width, height, true);
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
