package com.polly5315.slidingsquares.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.polly5315.slidingsquares.model.Direction;
import com.polly5315.slidingsquares.presentationModel.ILevelPresentationModel;

public class LevelScreen extends ScreenAdapter implements InputProcessor, ILevelPresentationModel.IListener {
    private ILevelPresentationModel _presentationModel;
    private BitmapFont _font;
    private Batch _batch;
    private float _secondsPerStep = 0.1f;
    private float _secondsSinceLastStep = 0f;

    public LevelScreen(ILevelPresentationModel presentationModel) {
        if (presentationModel == null)
            throw new IllegalArgumentException("presentationModel cannot be null");
        _presentationModel = presentationModel;
        _presentationModel.addLevelPresentationModelListener(this);

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("unispace.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
        parameter.color = Color.WHITE;
        _font = generator.generateFont(parameter);
        generator.dispose();
        _batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _secondsSinceLastStep += delta;
        while (_secondsSinceLastStep >= _secondsPerStep) {
            _secondsSinceLastStep -= _secondsPerStep;
            _presentationModel.nextStep();
        }

        _batch.begin();
        _font.draw(_batch, "Press any key for turn", 50, 400);
        _font.draw(_batch, "(after 3-rd turn app will close)", 50, 300);
        _font.draw(_batch, "Steps in turn: " + Integer.toString(_presentationModel.getStepCount()), 50, 200);
        _font.draw(_batch, "Turns: " + Integer.toString(_presentationModel.getTurnCount()), 50, 100);
        _batch.end();
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

    //Input processor methods
    @Override
    public boolean keyDown(int keycode) {
        _presentationModel.startTurn(Direction.Left);
        //switch (keycode) {
        //    case Input.Keys.LEFT:
        //        _presentationModel.startTurn(Direction.Left);
        //        break;
        //    case Input.Keys.RIGHT:
        //        _presentationModel.startTurn(Direction.Right);
        //        break;
        //    case Input.Keys.UP:
        //        _presentationModel.startTurn(Direction.Up);
        //        break;
        //    case Input.Keys.DOWN:
        //        _presentationModel.startTurn(Direction.Down);
        //        break;
        //    default:
        //        return false;
        //}
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

    @Override
    public void onTurnCompleted() {
        //
    }

    @Override
    public void onLevelCompleted() {
        Gdx.app.exit();
    }
}
