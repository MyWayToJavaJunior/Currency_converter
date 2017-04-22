package ru.kostikov.currencyconverter.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.HashMap;

import ru.kostikov.currencyconverter.data.CurrencyData;
import ru.kostikov.currencyconverter.data.CurrencyDataResponse;
import ru.kostikov.currencyconverter.data.CurrencyDataSource;
import ru.kostikov.currencyconverter.model.CurrencyConverter;

/**
 * Created by user on 22.04.2017.
 */

public class ConverterPresenter implements ConverterContract.Presenter, CurrencyDataResponse{

    private final static int TASKS_QUERY = 1;

    /**
     *  Repository for fetching currency data
     */
    private final CurrencyDataSource mCurrencyRepository;

    private final ConverterContract.View mConverterView;

    private CurrencyConverter currencyConverter;


    public ConverterPresenter(@NonNull CurrencyDataSource currencyDataRepository, @NonNull ConverterContract.View converterView) {
        currencyConverter = new CurrencyConverter();

        mCurrencyRepository = currencyDataRepository;
        mConverterView = converterView;

        mConverterView.setPresenter(this);
    }

    @Override
    public void start() {
        mCurrencyRepository.setCurrencyDataResponse(this);
        mCurrencyRepository.requestCurrencyDataMap();

    }

    @Override
    public void currencyDataResponse(HashMap<String, CurrencyData> dataMap) {
        currencyConverter.init(dataMap);
    }
}
