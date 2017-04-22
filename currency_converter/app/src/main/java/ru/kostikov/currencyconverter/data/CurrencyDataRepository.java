package ru.kostikov.currencyconverter.data;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;

import java.util.HashMap;

import ru.kostikov.currencyconverter.data.local.LocalCurrencyData;
import ru.kostikov.currencyconverter.data.remote.RemoteCurrencyData;
import ru.kostikov.currencyconverter.util.Injector;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by user on 22.04.2017.
 */

public class CurrencyDataRepository implements CurrencyDataSource, CurrencyDataResponse {

    /**
     *  Default convert data from xml
     */
    private CurrencyDataSource mDefaultCurrencyData;
    /**
     * Convert data from Internet
     * */
    private CurrencyDataSource mRemoteCurrencyData;
    /*
    *  Response after downloading from internet
    * */
    private CurrencyDataResponse mCurrencyDataResponse;

    public CurrencyDataRepository(LoaderManager loaderManager) {

        mDefaultCurrencyData = new LocalCurrencyData();
        mRemoteCurrencyData = new RemoteCurrencyData(loaderManager);
    }

    @Override
    public void setCurrencyDataResponse(CurrencyDataResponse mCurrencyDataResponse) {
        this.mCurrencyDataResponse = mCurrencyDataResponse;
    }

    /**
     * Gets Map with convert data for currencies
     * If internet available, start download new data, then call CurrencyDataResponse interface
     * @return map with convert data
     */
    @Override
    public void requestCurrencyDataMap() {
        HashMap<String, CurrencyData> result = null;

        if (isConnectAvailable()){
            mRemoteCurrencyData.setCurrencyDataResponse(this);
            mRemoteCurrencyData.requestCurrencyDataMap();
        }
        mDefaultCurrencyData.setCurrencyDataResponse(this);
        mDefaultCurrencyData.requestCurrencyDataMap();
    }

    @Override
    public void currencyDataResponse(HashMap<String, CurrencyData> dataMap) {
        if (this.mCurrencyDataResponse != null){
            this.mCurrencyDataResponse.currencyDataResponse(dataMap);
        }

    }

    /**
     * Check internet connection
     * @return true if the internet available
     */
    private boolean isConnectAvailable(){
        boolean result = false;
        ConnectivityManager cm =
                (ConnectivityManager) Injector.instance().getAppContext().getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            result = true;
        }
        return result;
    }
}
