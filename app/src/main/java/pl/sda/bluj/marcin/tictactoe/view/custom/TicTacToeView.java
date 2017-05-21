package pl.sda.bluj.marcin.tictactoe.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import pl.sda.bluj.marcin.tictactoe.R;
import pl.sda.bluj.marcin.tictactoe.model.Mark;
import pl.sda.bluj.marcin.tictactoe.model.Position;

/**
 * Created by MSI on 19.05.2017.
 */

public class TicTacToeView extends View {
    private int mLineColor;
    private int mMarkColor;
    private int mLineSize;
    private Paint mLinePaint;
    private Paint mMarkPaint;
    private Paint mWinLinePaint;

    private float viewWidth, viewHeight;
    private final Mark[] mGameBoard = new Mark[9];
    private final Float[] mWinLine = new Float[4];

    public interface OnTicTacToeClickListener {
        void onClick(Position.EnumPosition position);
    }

    private OnTicTacToeClickListener mListener;

    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mLinePaint = new Paint();
        mMarkPaint = new Paint();
        mWinLinePaint = new Paint();

        TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.TicTacToeView, 0, 0);

        try {
            mLineColor = styledAttributes.getColor(R.styleable.TicTacToeView_lineColor, Color.BLACK);
            mLineSize = styledAttributes.getDimensionPixelSize(R.styleable.TicTacToeView_lineSize, 5);
            mMarkColor = styledAttributes.getColor(R.styleable.TicTacToeView_markColor, Color.GRAY);
        } finally {
            styledAttributes.recycle();
        }
    }

    public void setMark(Mark mark, Position.EnumPosition position) {
        mGameBoard[position.getPosition()] = mark;
        Log.d("TIC_TAC_TOE", String.format("mark: %s pos: %d", mark.name(), position.getPosition()));
        invalidate();
    }

    public void setWinLine(Position.EnumPosition start, Position.EnumPosition end) {
        float oneThirdWidth = viewWidth / 3;
        float oneSixthWidth = viewWidth / 6;
        float oneThirdHeight = viewHeight / 3;
        float oneSixthHeight = viewHeight / 6;
        mWinLine[0] = oneSixthWidth + (oneThirdWidth * (start.getPosition() % 3));
        mWinLine[1] = oneSixthHeight + (oneThirdHeight * (start.getPosition() / 3));
        mWinLine[2] = oneSixthWidth + (oneThirdWidth * (end.getPosition() % 3));
        mWinLine[3] = oneSixthHeight + (oneThirdHeight * (end.getPosition() / 3));
        invalidate();
    }

    public
    @NonNull
    Mark[] getGameBoard() {
        return mGameBoard;
    }

    public void setGameBoard(Mark[] gameBoard) {
        if (gameBoard.length == 9) {
            System.arraycopy(gameBoard, 0, mGameBoard, 0, gameBoard.length);
        } else {
            for (int i = 0; i < mGameBoard.length; i++) {
                if (gameBoard.length < i + 1) {
                    break;
                } else {
                    mGameBoard[i] = gameBoard[i];
                }
            }
        }
        invalidate();
    }

    public void clearGameBoard() {
        for (int i = 0; i < mGameBoard.length; i++) {
            mGameBoard[i] = null;
            invalidate();
        }
    }

    public void clearWinLine() {
        for (int i = 0; i < mWinLine.length; i++) {
            mWinLine[i] = null;
            invalidate();
        }
    }

    public
    @Nullable
    Mark getField(Position.EnumPosition position) {
        return mGameBoard[position.getPosition()];
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mListener != null && event.getAction() == 0) {
            float x = event.getX();
            float y = event.getY();

            Position.EnumPosition position = Position.getPosition(viewWidth, viewHeight, x, y);
            mListener.onClick(position);

            String cords = String.format("x: %s y: %s pos: %s", x, y, position);
            Log.d("TIC_TAC_TOE", cords);
        }
        return super.onTouchEvent(event);
    }

    public void setOnTicTacToeClickListener(OnTicTacToeClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        viewWidth = this.getMeasuredWidth();
        viewHeight = this.getMeasuredHeight();

        setLinePaintProperties();
        setMarkPaintProperties();
        setWinLinePaintProperties();

        drawField(canvas);
        drawMarksOnGameField(canvas);
        drawWinLine(canvas);
    }

    private void setLinePaintProperties() {
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineSize);
    }

    private void setMarkPaintProperties() {
        mMarkPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mMarkPaint.setAntiAlias(true);
        mMarkPaint.setColor(mMarkColor);
        mMarkPaint.setStrokeWidth(1);
        float size = (viewWidth > viewHeight) ? viewHeight / 4 : viewWidth / 4;
        mMarkPaint.setTextSize(size);
        mMarkPaint.setTextAlign(Paint.Align.CENTER);

    }

    private void setWinLinePaintProperties() {
        mWinLinePaint.setStyle(Paint.Style.STROKE);
        mWinLinePaint.setAntiAlias(true);
        mWinLinePaint.setColor(Color.BLACK);
        mWinLinePaint.setStrokeWidth(mLineSize);
    }

    private void drawField(Canvas canvas) {
        float oneThirdWidth = viewWidth / 3;
        float twoThirdWidth = oneThirdWidth * 2;
        float oneThirdHeight = viewHeight / 3;
        float twoThirdHeight = oneThirdHeight * 2;

        canvas.drawLine(oneThirdWidth, 0, oneThirdWidth, viewHeight, mLinePaint);
        canvas.drawLine(twoThirdWidth, 0, twoThirdWidth, viewHeight, mLinePaint);
        canvas.drawLine(0, oneThirdHeight, viewWidth, oneThirdHeight, mLinePaint);
        canvas.drawLine(0, twoThirdHeight, viewWidth, twoThirdHeight, mLinePaint);
    }

    private void drawMarksOnGameField(Canvas canvas) {
        for (int i = 0; i < mGameBoard.length; i++) {
            if (mGameBoard[i] != null) {
                float oneSixthWidth = viewWidth / 6;
                float oneThirdWidth = viewWidth / 3;
                float x = oneSixthWidth + oneThirdWidth * (i % 3);

                float oneFourthHeight = viewHeight / 4;
                float oneThirdHeight = viewHeight / 3;
                float y = oneFourthHeight + oneThirdHeight * (i / 3);

                switch (mGameBoard[i]) { //height 1/4, 7/12, 11/12  width 1/6, 3/6, 5/6
                    case O:
                        canvas.drawText("O", x, y, mMarkPaint);
                        break;
                    case X:
                        canvas.drawText("X", x, y, mMarkPaint);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void drawWinLine(Canvas canvas) {
        for (Float l : mWinLine) {
            if (l == null) {
                return;
            }
        }
        float startX, startY, endX, endY;
        startX = mWinLine[0];
        startY = mWinLine[1];
        endX = mWinLine[2];
        endY = mWinLine[3];

        canvas.drawLine(startX, startY, endX, endY, mWinLinePaint);
    }
}
