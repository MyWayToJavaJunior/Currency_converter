package ru.kostikov.currencyconverter.ui.chooser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.HashMap;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.data.CurrencyData;
import ru.kostikov.currencyconverter.data.CurrencyDataRepository;
import ru.kostikov.currencyconverter.data.CurrencyDataResponse;
import ru.kostikov.currencyconverter.data.CurrencyDataSource;

public class ChooseActivity extends AppCompatActivity implements CurrencyDataResponse, CurrencyAdapter.ItemClickListener {

    public static final String EXTRA_TAG = "charNum";

    private RecyclerView mReportsRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private CurrencyAdapter mCurrencyAdapter;

    private CurrencyDataSource currencyDataSource;
    private HashMap<String, CurrencyData> mCurrencyData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currencyDataSource = CurrencyDataRepository.getInstance(getSupportLoaderManager());
        currencyDataSource.setCurrencyDataResponse(this);
        currencyDataSource.requestCurrencyDataMap();
    }

    @Override
    public void currencyDataResponse(HashMap<String, CurrencyData> dataMap) {
        this.mCurrencyData = dataMap;

        reportsRecyclerViewInit();
    }

    private void reportsRecyclerViewInit() {

        mReportsRecyclerView = (RecyclerView)findViewById(R.id.currency_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mReportsRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mReportsRecyclerView.setLayoutManager(mLayoutManager);
        mCurrencyAdapter  = new CurrencyAdapter(mCurrencyData, this);

        mReportsRecyclerView.setAdapter(mCurrencyAdapter);
    }

    @Override
    public void itemClick(String charNum) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TAG, charNum);
        setResult(RESULT_OK, intent);
        finish();
    }
}
