package ru.kostikov.currencyconverter.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.HashMap;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.data.Constants;
import ru.kostikov.currencyconverter.data.CurrencyData;
import ru.kostikov.currencyconverter.data.CurrencyDataResponse;
import ru.kostikov.currencyconverter.data.CurrencyDataSource;
import ru.kostikov.currencyconverter.data.CurrencyXmlParser;
import ru.kostikov.currencyconverter.util.Injector;

/**
 * Created by user on 22.04.2017.
 */

public class LocalCurrencyData implements CurrencyDataSource {

    private HashMap<String, CurrencyData> mCurrencyDataHashMap = new HashMap<>(0);

    private CurrencyDataResponse mCurrencyDataResponse;

    /**
     * Gets Map with convert data for currencies
     *
     * @return map with convert data
     */
    @Override
    public void requestCurrencyDataMap() {
        String loadXml = load();

        if (!loadXml.equals(""))
        {
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(loadXml));

                if (!CurrencyXmlParser.parseConvertDataXml(xpp, this.mCurrencyDataHashMap)){

                }

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        } else {
            XmlPullParser parser = Injector.instance().getAppContext().getResources().getXml(R.xml.currency_default);
            CurrencyXmlParser.parseConvertDataXml(parser, mCurrencyDataHashMap);
        }

        if (this.mCurrencyDataResponse != null ){
            this.mCurrencyDataResponse.currencyDataResponse(this.mCurrencyDataHashMap);
        }
    }

    @Override
    public void setCurrencyDataResponse(CurrencyDataResponse mCurrencyDataResponse) {
        this.mCurrencyDataResponse = mCurrencyDataResponse;
    }

    /**
     * Load data from shared preferences
     * @return String from file
     */
    private String load() {
        SharedPreferences sPref = Injector.instance().getAppContext().getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sPref.getString(Constants.SHARED_PREF_NAME, "");
    }


}
