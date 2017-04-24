package ru.kostikov.currencyconverter.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

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

    private String mQuantityFrom = "0";
    private String mResultTo  = "";

    private boolean mViewInitFlag = false;


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

        if (!mViewInitFlag){
            mViewInitFlag = true;

            mConverterView.setCurrencyFrom(currencyConverter.getCurrencyFrom());
            mConverterView.setCurrencyTo(currencyConverter.getCurrencyTo());

            recompute();
        }
    }

    @Override
    public void setCurrencyFrom(String charNum) {
        currencyConverter.setCurrencyFrom(charNum);
        mConverterView.setCurrencyFrom(charNum);

        recompute();
    }

    @Override
    public void setCurrencyTo(String charNum) {
        currencyConverter.setCurrencyTo(charNum);
        mConverterView.setCurrencyTo(charNum);

        recompute();
    }

    @Override
    public void flipCurrency() {
        currencyConverter.flip();

        mConverterView.setCurrencyFrom(currencyConverter.getCurrencyFrom());
        mConverterView.setCurrencyTo(currencyConverter.getCurrencyTo());

        recompute();
    }

    @Override
    public void setNumber(int number) {
        if (this.mQuantityFrom.equals("0")) this.mQuantityFrom = "";

        this.mQuantityFrom += String.valueOf(number);

        recompute();
    }

    @Override
    public void clear() {
        this.mQuantityFrom = "";
        setNumber(0);
    }

    @Override
    public void setDot() {
        if (this.mQuantityFrom.equals("0")) this.mQuantityFrom = "";
        if (this.mQuantityFrom.equals("")) return;

        this.mQuantityFrom += ".";
        mConverterView.setQuantityFrom(this.mQuantityFrom);
    }

    private void recompute(){
        this.mResultTo = String.valueOf(currencyConverter.convert(Float.valueOf(this.mQuantityFrom)));

        mConverterView.setQuantityFrom(this.mQuantityFrom);
        mConverterView.setResultTo(this.mResultTo);
    }
}
