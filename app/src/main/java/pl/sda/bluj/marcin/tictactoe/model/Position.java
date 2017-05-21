package pl.sda.bluj.marcin.tictactoe.model;

import static pl.sda.bluj.marcin.tictactoe.model.Position.EnumPosition.*;

/**
 * Created by MSI on 20.05.2017.
 */

public class Position {
    private Position() {
    }

    public static EnumPosition getPosition(float w, float h, float x, float y) {
        if (x > 0 && x < w / 3 && y > 0 && y < h / 3) {
            return A1;
        } else if (x > w / 3 && x < w / 3 * 2 && y > 0 && y < h / 3) {
            return A2;
        } else if (x > w / 3 * 2 && x < w && y > 0 && y < h / 3) {
            return A3;
        } else if (x > 0 && x < w / 3 && y > h / 3 && y < h / 3 * 2) {
            return B1;
        } else if (x > w / 3 && x < w / 3 * 2 && y > h / 3 && y < h / 3 * 2) {
            return B2;
        } else if (x > w / 3 * 2 && x < w && y > h / 3 && y < h / 3 * 2) {
            return B3;
        } else if (x > 0 && x < w / 3 && y > h / 3 * 2 && y < h) {
            return C1;
        } else if (x > w / 3 && x < w / 3 * 2 && y > h / 3 * 2 && y < h) {
            return C2;
        } else if (x > w / 3 * 2 && x < w && y > h / 3 * 2 && y < h) {
            return C3;
        } else {
            return null;
        }
    }

    public enum EnumPosition {
        A1(0), A2(1), A3(2), B1(3), B2(4), B3(5), C1(6), C2(7), C3(8);

        private int position;

        EnumPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}
