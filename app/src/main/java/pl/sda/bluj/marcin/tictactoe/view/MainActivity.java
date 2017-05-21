package pl.sda.bluj.marcin.tictactoe.view;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.sda.bluj.marcin.tictactoe.R;
import pl.sda.bluj.marcin.tictactoe.model.Mark;
import pl.sda.bluj.marcin.tictactoe.presenter.MainPresenter;
import pl.sda.bluj.marcin.tictactoe.view.custom.TicTacToeView;
import pl.sda.bluj.marcin.tictactoe.model.Position;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.customView)
    TicTacToeView mTicTacToe;
    @BindView(R.id.circle)
    ImageView mCircle;
    @BindView(R.id.cross)
    ImageView mCross;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        setupTicTacToe();

        mPresenter.initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Mark[] gameBoard = mPresenter.loadGameBoard(); //TODO loading game state
//        mTicTacToe.setGameBoard(gameBoard);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Mark[] gameBoard = mTicTacToe.getGameBoard(); //TODO saving game state
//        mPresenter.saveGameBoard(gameBoard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_game_menu_button) {
            mPresenter.onNewGameClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMark(Mark mark, Position.EnumPosition position) {
        mTicTacToe.setMark(mark, position);
    }

    @Override
    public void showORound() {
        mCircle.setBackgroundColor(ResourcesCompat.getColor(getResources(),
                R.color.colorActivePlayer, null));
        mCross.setBackgroundColor(ResourcesCompat.getColor(getResources(),
                R.color.colorInactivePlayer, null));
    }

    @Override
    public void showXRound() {
        mCross.setBackgroundColor(ResourcesCompat.getColor(getResources(),
                R.color.colorActivePlayer, null));
        mCircle.setBackgroundColor(ResourcesCompat.getColor(getResources(),
                R.color.colorInactivePlayer, null));
    }

    @Override
    public void showWinLine(Position.EnumPosition start, Position.EnumPosition end) {
        mTicTacToe.setWinLine(start, end);
    }

    @Override
    public void showNewGameBoard() {
        mTicTacToe.clearGameBoard();
        mTicTacToe.clearWinLine();
    }

    private void setupTicTacToe() {
        mTicTacToe.setOnTicTacToeClickListener(new TicTacToeView.OnTicTacToeClickListener() {
            @Override
            public void onClick(Position.EnumPosition position) {
                Mark field = mTicTacToe.getField(position);
                if (field == null) {
                    mPresenter.onClicked(position);
                }
            }
        });
    }
}
