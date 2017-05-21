package pl.sda.bluj.marcin.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.A1;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.A2;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.A3;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.B1;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.B2;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.B3;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.C1;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.C2;
import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.C3;


/**
 * Created by MSI on 20.05.2017.
 */

public class WinCase {
    private final List<Position.EnumPosition> mWinCase = new ArrayList<>(3);
    private int mOCounter = 0;
    private int mXCounter = 0;

    private WinCase(Position.EnumPosition p1, Position.EnumPosition p2, Position.EnumPosition p3) {
        mWinCase.add(p1);
        mWinCase.add(p2);
        mWinCase.add(p3);
    }

    public static WinCase[] getWinCases() {
        return WinCaseHolder.WIN_CASES;
    }

    public boolean checkCounterForWin(Position.EnumPosition position, Mark mark) {
        if (mWinCase.contains(position)) {
            if (mark == Mark.O) {
                mOCounter++;
            } else if (mark == Mark.X) {
                mXCounter++;
            }
        }
        return mOCounter >= 3 || mXCounter >= 3;
    }

    public Position.EnumPosition[] getLinePositions() {
        Position.EnumPosition startPosition = mWinCase.get(0);
        Position.EnumPosition endPosition = mWinCase.get(2);
        return new Position.EnumPosition[]{startPosition, endPosition};
    }

    public void clearCounter() {
        mOCounter = 0;
        mXCounter = 0;
    }

    private static class WinCaseHolder {
        private static final WinCase[] WIN_CASES = new WinCase[]{
                new WinCase(A1, A2, A3),
                new WinCase(B1, B2, B3),
                new WinCase(C1, C2, C3),
                new WinCase(A1, B1, C1),
                new WinCase(A2, B2, C2),
                new WinCase(A3, B3, C3),
                new WinCase(A1, B2, C3),
                new WinCase(A3, B2, C1)};
    }
}
