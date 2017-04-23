package ru.kostikov.currencyconverter.ui;

/**
 * Its store with country flags and other attributes
 */

public class CurrencyViewStore {



    private static CurrencyViewStore INSTANCE = null;

    private CurrencyViewStore() {



    }

    public CurrencyViewStore getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new CurrencyViewStore();
        }
        return INSTANCE;
    }

    public static class CurrencyViewData{

    }
}
