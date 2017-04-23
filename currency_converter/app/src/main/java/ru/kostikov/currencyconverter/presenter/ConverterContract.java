package ru.kostikov.currencyconverter.presenter;

import ru.kostikov.currencyconverter.BasePresenter;
import ru.kostikov.currencyconverter.BaseView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface ConverterContract {

    interface View extends BaseView<Presenter> {
        void setCurrencyFrom(String charNum);
        void setCurrencyTo(String charNum);

        void setQuantityFrom(String quantityFrom);
        void setResultTo(String resultTo);
    }

    interface Presenter extends BasePresenter {

        void setCurrencyFrom(String charNum);
        void setCurrencyTo(String charNum);

        void flipCurrency();

        void setNumber(int number);
        void setDot();
        void clear();
    }
}
