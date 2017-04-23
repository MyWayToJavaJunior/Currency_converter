package ru.kostikov.currencyconverter.model;

import java.util.HashMap;

import ru.kostikov.currencyconverter.data.CurrencyData;

/**
 * Created by user on 22.04.2017.
 */

public class CurrencyConverter {

    /**
     *  It's store with currency convert params
     */
    private HashMap<String, CurrencyData> mCurrencyDataStore;
    /**
     *  CharNum of currency that converts
     */
    private String currencyFrom = "RUB";
    /**
     *  CharNum of currency into which converts
     */
    private String currencyTo = "USD";

    /**
     * Sets map with data about currencies
     * @param currencyDataMap
     */
    public void init(HashMap<String, CurrencyData> currencyDataMap){
        this.mCurrencyDataStore = currencyDataMap;
    }

    /**
     * Checks, has current currency in memory
     * @param currencyCarNum carNum of currency
     * @return true - available, false not available
     */
    public boolean isCurrencyAvailable(String currencyCarNum){
        return this.mCurrencyDataStore.containsKey(currencyCarNum);
    }

    /**
     * Sets currency that converts
     * @param currencyFrom carNum of currency
     */
    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    /**
     * Sets currency into which converts
     * @param currencyTo carNum of currency
     */
    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    /**
     * Gets currency that converts
     * @return carNum of currency
     */
    public String getCurrencyFrom() {
        return this.currencyFrom;
    }

    /**
     * Gets currency into which converts
     * @return carNum of currency
     */
    public String getCurrencyTo() {
        return this.currencyTo;
    }

    /**
     * Convert currencies
     * @param quantity Quantity of currency FROM
     * @return Quantity in currency TO
     */
    public float convert(float quantity){
        CurrencyData currencyFromData = this.mCurrencyDataStore.get(this.currencyFrom);
        CurrencyData currencyToData = this.mCurrencyDataStore.get(this.currencyTo);

        float curFromInRub = (currencyFromData.getValue() / currencyFromData.getNominal())*quantity;
        float curToInRub = (currencyToData.getValue() / currencyToData.getNominal());

        return curFromInRub / curToInRub;
    }

    /**
     * Swap currencies
     */
    public void flip(){
        String tmp = this.currencyFrom;
        this.currencyFrom = this.currencyTo;
        this.currencyTo = tmp;

    }
}
