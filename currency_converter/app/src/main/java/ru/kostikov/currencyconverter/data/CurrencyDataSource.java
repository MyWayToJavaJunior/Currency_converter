package ru.kostikov.currencyconverter.data;

import java.util.HashMap;

/**
 * Created by user on 22.04.2017.
 */

public interface CurrencyDataSource {

    /**
     * Gets Map with convert data for currencies
     * @return map with convert data
     */
    void requestCurrencyDataMap();

    void setCurrencyDataResponse(CurrencyDataResponse mCurrencyDataResponse);
}
