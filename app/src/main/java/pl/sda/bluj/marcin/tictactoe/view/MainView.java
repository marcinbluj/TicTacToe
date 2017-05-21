package pl.sda.bluj.marcin.tictactoe.view;

import pl.sda.bluj.marcin.tictactoe.base.BaseView;
import pl.sda.bluj.marcin.tictactoe.model.Mark;
import pl.sda.bluj.marcin.tictactoe.model.Position;

/**
 * Created by MSI on 20.05.2017.
 */

public interface MainView extends BaseView {

    void showMark(Mark mark, Position.EnumPosition position);

    void showORound();

    void showXRound();

    void showWinLine(Position.EnumPosition start, Position.EnumPosition end);

    void showNewGameBoard();
}
