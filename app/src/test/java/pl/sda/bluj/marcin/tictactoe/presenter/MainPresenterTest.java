package pl.sda.bluj.marcin.tictactoe.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.sda.bluj.marcin.tictactoe.model.Mark;
import pl.sda.bluj.marcin.tictactoe.model.Position;
import pl.sda.bluj.marcin.tictactoe.view.MainView;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by MSI on 21.05.2017.
 */
public class MainPresenterTest {
    @Mock
    MainView mMainView;

    MainPresenter mMainPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainPresenter = new MainPresenter(mMainView);
    }

    @Test
    public void testInitializationShowsORound() {
        mMainPresenter.initialize();
        verify(mMainView).showORound();
    }

    @Test
    public void testOnClickedWorksProperly_ForA1Position() {
        mMainPresenter.onClicked(Position.EnumPosition.A1);
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.A1);
        verify(mMainView).showXRound();
        verify(mMainView, never()).showWinLine(any(Position.EnumPosition.class),
                any(Position.EnumPosition.class));
    }

    @Test
    public void testOnClickedWorksProperly_FewMovements() {
        mMainPresenter.onClicked(Position.EnumPosition.A1);
        mMainPresenter.onClicked(Position.EnumPosition.A2);
        mMainPresenter.onClicked(Position.EnumPosition.A3);
        mMainPresenter.onClicked(Position.EnumPosition.B1);

        verify(mMainView, times(2)).showXRound();
        verify(mMainView, times(2)).showORound();
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.A1);
        verify(mMainView).showMark(Mark.X, Position.EnumPosition.A2);
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.A3);
        verify(mMainView).showMark(Mark.X, Position.EnumPosition.B1);
        verify(mMainView, never()).showWinLine(any(Position.EnumPosition.class),
                any(Position.EnumPosition.class));
    }

    @Test
    public void testOnClickedWorksProperly_SimpleWinCase() {
        mMainPresenter.onClicked(Position.EnumPosition.A1);
        mMainPresenter.onClicked(Position.EnumPosition.B1);
        mMainPresenter.onClicked(Position.EnumPosition.A2);
        mMainPresenter.onClicked(Position.EnumPosition.B2);
        mMainPresenter.onClicked(Position.EnumPosition.A3);
        verify(mMainView).showWinLine(Position.EnumPosition.A1, Position.EnumPosition.A3);
    }

    @Test
    public void testOnClickedWorksProperly_ComplexWinCase() {
        mMainPresenter.onClicked(Position.EnumPosition.A1);
        mMainPresenter.onClicked(Position.EnumPosition.A2);
        mMainPresenter.onClicked(Position.EnumPosition.A3);
        mMainPresenter.onClicked(Position.EnumPosition.B1);
        mMainPresenter.onClicked(Position.EnumPosition.B2);
        mMainPresenter.onClicked(Position.EnumPosition.B3);
        mMainPresenter.onClicked(Position.EnumPosition.C2);
        mMainPresenter.onClicked(Position.EnumPosition.C1);
        mMainPresenter.onClicked(Position.EnumPosition.C3);
        verify(mMainView).showWinLine(Position.EnumPosition.A1, Position.EnumPosition.C3);
    }

    @Test
    public void testOnClickedWorksProperly_DrawCase() {
        mMainPresenter.onClicked(Position.EnumPosition.A1);
        mMainPresenter.onClicked(Position.EnumPosition.A3);
        mMainPresenter.onClicked(Position.EnumPosition.A2);
        mMainPresenter.onClicked(Position.EnumPosition.B1);
        mMainPresenter.onClicked(Position.EnumPosition.B3);
        mMainPresenter.onClicked(Position.EnumPosition.B2);
        mMainPresenter.onClicked(Position.EnumPosition.C1);
        mMainPresenter.onClicked(Position.EnumPosition.C2);
        mMainPresenter.onClicked(Position.EnumPosition.C3);

        verify(mMainView).showMark(Mark.O, Position.EnumPosition.A1);
        verify(mMainView).showMark(Mark.X, Position.EnumPosition.A3);
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.A2);
        verify(mMainView).showMark(Mark.X, Position.EnumPosition.B1);
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.B3);
        verify(mMainView).showMark(Mark.X, Position.EnumPosition.B2);
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.C1);
        verify(mMainView).showMark(Mark.X, Position.EnumPosition.C2);
        verify(mMainView).showMark(Mark.O, Position.EnumPosition.C3);
        verify(mMainView, never()).showWinLine(any(Position.EnumPosition.class),
                any(Position.EnumPosition.class));
    }

    @Test
    public void testNewGameShowsNewGameBoardAndORound() {
        mMainPresenter.onNewGameClicked();
        verify(mMainView).showNewGameBoard();
        verify(mMainView).showORound();
    }
}