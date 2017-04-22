package ru.kostikov.currencyconverter.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.data.CurrencyDataRepository;
import ru.kostikov.currencyconverter.presenter.ConverterPresenter;
import ru.kostikov.currencyconverter.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private ConverterPresenter mConverterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConverterFragment converterFragment =
                (ConverterFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
        if (converterFragment == null) {

            // Create the fragment
            converterFragment = ConverterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), converterFragment, R.id.frame_content);

            mConverterPresenter = new ConverterPresenter(
                    new CurrencyDataRepository(getSupportLoaderManager()),
                    converterFragment
            );
        }
    }

}
