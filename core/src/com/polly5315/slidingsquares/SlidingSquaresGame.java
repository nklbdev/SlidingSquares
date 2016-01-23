package com.polly5315.slidingsquares;

import com.badlogic.gdx.Game;
import com.polly5315.slidingsquares.presentationModel.LevelPresentationModel;
import com.polly5315.slidingsquares.view.screens.LevelScreen;

public class SlidingSquaresGame extends Game {
	@Override
	public void create () {
		setScreen(new LevelScreen(new LevelPresentationModel()));
	}
}
