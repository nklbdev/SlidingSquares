package com.polly5315.slidingsquares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.polly5315.slidingsquares.model.level.FamilyColor;
import com.polly5315.slidingsquares.presentationModel.Engine;
import com.polly5315.slidingsquares.presentationModel.IEngine;
import com.polly5315.slidingsquares.presentationModel.cells.PocketCellState;
import com.polly5315.slidingsquares.presentationModel.cells.SliderState;
import com.polly5315.slidingsquares.view.screens.LevelScreen;

public class SlidingSquaresGame extends Game {
	@Override
	public void create () {
		IEngine engine = new Engine(5, 6);
        Screen screen = new LevelScreen(engine);

		engine.addBombCell(0, 0, false);
		engine.addEmptyCell(1, 0);
		engine.addEmptyCell(4, 0);

		engine.addEmptyCell(1, 1);
		engine.addEmptyCell(2, 1);
		engine.addEmptyCell(3, 1);
		engine.addEmptyCell(4, 1);

		engine.addEmptyCell(0, 2);
		engine.addEmptyCell(1, 2);
		engine.addEmptyCell(2, 2);
		engine.addEmptyCell(3, 2);
		engine.addEmptyCell(4, 2);

		engine.addEmptyCell(1, 3);
		engine.addEmptyCell(2, 3);
		engine.addEmptyCell(3, 3);
		engine.addEmptyCell(4, 3);

		engine.addEmptyCell(0, 4);
		engine.addEmptyCell(1, 4);
		engine.addEmptyCell(2, 4);
		engine.addEmptyCell(4, 4);

		engine.addBombCell(4, 5, false);

		engine.addPocketCell(3, 4, FamilyColor.Pink, PocketCellState.Hidden);
		engine.addSlider(1, 4, FamilyColor.Pink, SliderState.Idle);
		engine.addButtonCell(2, 0, FamilyColor.Pink, false);

		setScreen(screen);
	}
}
