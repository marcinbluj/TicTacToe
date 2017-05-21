package pl.sda.bluj.marcin.tictactoe.presenter;

import pl.sda.bluj.marcin.tictactoe.base.BasePresenter;
import pl.sda.bluj.marcin.tictactoe.model.Mark;
import pl.sda.bluj.marcin.tictactoe.model.Position;
import pl.sda.bluj.marcin.tictactoe.model.WinCase;
import pl.sda.bluj.marcin.tictactoe.view.MainView;

/**
 * Created by MSI on 20.05.2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private int mRoundCounter = 0;
    private final WinCase[] mWinCases = WinCase.getWinCases();
    private boolean mWinState = false;

    public MainPresenter(MainView view) {
        super(view);
    }

    public void initialize() {
        clearCounters();
        getView().showORound();
    }

    public void onClicked(Position.EnumPosition position) {
        if (!mWinState) {
            mRoundCounter++;
            boolean isORound = mRoundCounter % 2 == 1;
            if (isORound) {
                getView().showMark(Mark.O, position);
                getView().showXRound();
            } else {
                getView().showMark(Mark.X, position);
                getView().showORound();
            }

            for (WinCase winCase : mWinCases) {
                Mark mark = isORound ? Mark.O : Mark.X;
                if (winCase.checkCounterForWin(position, mark)) {
                    Position.EnumPosition[] lineStartEnd = winCase.getLinePositions();
                    Position.EnumPosition start = lineStartEnd[0];
                    Position.EnumPosition end = lineStartEnd[1];
                    getView().showWinLine(start, end);
                    mWinState = true;
                    clearCounters();
                    break;
                }
            }
        }
    }

    public void onNewGameClicked() {
        getView().showNewGameBoard();
        getView().showORound();
        mWinState = false;
        clearCounters();
    }

    private void clearCounters() {
        for (WinCase c : mWinCases) {
            c.clearCounter();
        }
        mRoundCounter = 0;
    }

//    public Mark[] loadGameBoard() { //TODO
//
//    }
//
//    public void saveGameBoard(Mark[] gameBoard) { //TODO
//
//    }
}
