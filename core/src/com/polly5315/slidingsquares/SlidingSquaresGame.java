package com.polly5315.slidingsquares;

import com.badlogic.gdx.Game;
import com.polly5315.slidingsquares.model.level.FamilyColor;
import com.polly5315.slidingsquares.presentationModel.Engine;
import com.polly5315.slidingsquares.presentationModel.IEngine;
import com.polly5315.slidingsquares.presentationModel.cells.PocketCellState;
import com.polly5315.slidingsquares.presentationModel.cells.SliderState;
import com.polly5315.slidingsquares.view.screens.LevelScreen;

public class SlidingSquaresGame extends Game {
	@Override
	public void create () {
		IEngine engine = new Engine(4, 1);
		engine.addEmptyCell(0, 0);
		engine.addEmptyCell(1, 0);
		engine.addEmptyCell(2, 0);
		engine.addPocketCell(3, 0, FamilyColor.Pink, PocketCellState.Open);
		engine.addSlider(0, 0, FamilyColor.Pink, SliderState.Idle);

		setScreen(new LevelScreen(engine));
	}
}
