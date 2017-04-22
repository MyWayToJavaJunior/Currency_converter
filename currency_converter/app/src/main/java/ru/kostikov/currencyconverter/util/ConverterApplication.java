package ru.kostikov.currencyconverter.util;

import android.app.Application;

import ru.kostikov.currencyconverter.util.Injector;

/**
 * Created by user on 22.04.2017.
 */

public class ConverterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.instance().init(this);
    }
}
