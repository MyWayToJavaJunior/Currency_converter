package ru.kostikov.currencyconverter.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.presenter.ConverterContract;
import ru.kostikov.currencyconverter.presenter.ConverterPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterFragment extends Fragment implements ConverterContract.View {

    private ConverterContract.Presenter mConverterPresenter;

    public ConverterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConverterFragment.
     */
    public static ConverterFragment newInstance() {
        ConverterFragment fragment = new ConverterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        mConverterPresenter.start();
    }

    @Override
    public void setPresenter(ConverterContract.Presenter presenter) {
        mConverterPresenter = presenter;
    }
}
