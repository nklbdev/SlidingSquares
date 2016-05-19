package com.polly5315.slidingsquares.presentationModel;

import com.polly5315.slidingsquares.model.level.FamilyColor;
import com.polly5315.slidingsquares.presentationModel.cells.*;

import java.util.*;

public class Engine implements IEngine {
    private final Set<IListener> _listeners = new HashSet<IListener>();
    private EngineState _state;
    private final int _width;
    private final int _height;
    private final Collection<IEmptyCell> _emptyCells = new ArrayList<IEmptyCell>();
    private final Collection<IBombCell> _bombCells = new ArrayList<IBombCell>();
    private final Collection<IPocketCell> _pocketCells = new ArrayList<IPocketCell>();
    private final Collection<IButtonCell> _buttonCells = new ArrayList<IButtonCell>();
    private final Collection<ISlider> _sliders = new ArrayList<ISlider>();
    private final ICell[][] _cells;
    private int _turnCount = 0;
    private Direction _turnDirection;
    private final ISlider.IListener _sliderListener = new ISlider.IListener() {
        @Override
        public void onStateChanged(ISlider slider) {
            if (_state != EngineState.ProcessingTurn)
                return;
            boolean allSlidersAreFixed = true;
            for (ISlider mySlider : _sliders)
                if (mySlider.getState() != SliderState.Fixed) {
                    allSlidersAreFixed = false;
                    break;
                }
            if (allSlidersAreFixed)
                setState(EngineState.Winned);
        }

        @Override
        public void onPositionChanged(ISlider slider) {

        }
    };

    public Engine(int width, int height) {
        if (width < 0)
            throw new IllegalArgumentException("width cannot be negative");
        if (height < 0)
            throw new IllegalArgumentException("height cannot be negative");

        _state = EngineState.WaitingForTurn;
        _width = width;
        _height = height;

        _cells = new ICell[_width][_height];
    }

    @Override
    public void startTurn(Direction direction) {
        if (_state != EngineState.WaitingForTurn)
            return;
        setState(EngineState.ProcessingTurn);
        _turnDirection = direction;
    }

    @Override
    public void doStep() {
        if (_state != EngineState.ProcessingTurn)
            return;
        for (ISlider slider : _sliders) {
            if (slider.getState() != SliderState.Idle)
                continue;
            ICell targetCell = _cells[slider.getX() + _turnDirection.x][slider.getY() + _turnDirection.y];
            slider.setPosition(slider.getX() + _turnDirection.x, slider.getY() + _turnDirection.y);
            targetCell.Push(slider);
        }
    }

    @Override
    public EngineState getState() {
        return _state;
    }

    private void setState(EngineState state) {
        if (state == _state)
            return;
        _state = state;
        for (IListener listener : _listeners)
            listener.onStateChanged(this);
    }

    @Override
    public void addListener(IListener listener) {
        if (listener != null)
            _listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        if (listener != null)
            _listeners.remove(listener);
    }

    @Override
    public int getWidth() {
        return _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    @Override
    public int getTurnCount() {
        return _turnCount;
    }

    private void validateCellCoordinates(int x, int y) {
        if (x < 0 || x >= _width)
            throw new IndexOutOfBoundsException("x must be between 0 and width");
        if (y < 0 || y >= _width)
            throw new IndexOutOfBoundsException("y must be between 0 and width");
    }

    private void validateNullCell(int x, int y) {
        validateCellCoordinates(x, y);
        if (_cells[x][y] != null)
            throw new IllegalStateException("The cell [" + x + ":" + y + "] is already occupied");
    }

    private void validateVacantCell(int x, int y) {
        validateCellCoordinates(x, y);
        if (_cells[x][y] == null)
            throw new IllegalStateException("The cell [" + x + ":" + y + "] is null");
        for (ISlider slider : _sliders)
            if (slider.getX() == x && slider.getY() == y)
                throw new IllegalStateException("The cell [" + x + ":" + y + "] is already occupied");
    }

    @Override
    public IEmptyCell addEmptyCell(int x, int y) {
        validateNullCell(x, y);

        IEmptyCell cell = new EmptyCell();

        _cells[x][y] = cell;

        for (IListener listener : _listeners)
            listener.onEmptyCellAdded(this, cell, x, y);

        return cell;
    }

    @Override
    public IButtonCell addButtonCell(int x, int y, FamilyColor color, boolean isPushed) {
        validateNullCell(x, y);

        IButtonCell cell = new ButtonCell(color, isPushed);

        for (IPocketCell pocketCell : _pocketCells)
            if (pocketCell.getColor() == color)
                pocketCell.addButtonToListen(cell);

        _cells[x][y] = cell;

        for (IListener listener : _listeners)
            listener.onButtonCellAdded(this, cell, x, y);

        return cell;
    }

    @Override
    public IPocketCell addPocketCell(int x, int y, FamilyColor color, PocketCellState state) {
        validateNullCell(x, y);

        IPocketCell cell = new PocketCell(color, state);

        for (IButtonCell buttonCell : _buttonCells)
            if (buttonCell.getColor() == color)
                cell.addButtonToListen(buttonCell);

        _cells[x][y] = cell;

        for (IListener listener : _listeners)
            listener.onPocketCellAdded(this, cell, x, y);

        return cell;
    }

    @Override
    public IBombCell addBombCell(int x, int y, boolean isDetonated) {
        validateNullCell(x, y);

        IBombCell cell = new BombCell(isDetonated);

        _cells[x][y] = cell;

        for (IListener listener : _listeners)
            listener.onBombCellAdded(this, cell, x, y);

        return cell;
    }

    @Override
    public ISlider addSlider(int x, int y, FamilyColor color, SliderState state) {
        validateVacantCell(x, y);

        ISlider slider = new Slider(color, state);
        slider.setPosition(x, y);

        slider.addListener(_sliderListener);

        _cells[x][y].Push(slider);

        for (IListener listener : _listeners)
            listener.onSliderAdded(this, slider, x, y);

        return slider;
    }
}
