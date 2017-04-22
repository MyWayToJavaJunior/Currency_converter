package ru.kostikov.currencyconverter.presenter;

import ru.kostikov.currencyconverter.BasePresenter;
import ru.kostikov.currencyconverter.BaseView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface ConverterContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
