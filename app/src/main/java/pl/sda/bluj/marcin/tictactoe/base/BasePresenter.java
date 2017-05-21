package pl.sda.bluj.marcin.tictactoe.base;

/**
 * Created by MSI on 20.05.2017.
 */

public abstract class BasePresenter<V extends BaseView> {
    private V mView;

    public BasePresenter(V view) {
        if (view == null) {
            throw new IllegalArgumentException("Null view in presenter!");
        }
        this.mView = view;
    }

    protected V getView() {
        return mView;
    }
}
