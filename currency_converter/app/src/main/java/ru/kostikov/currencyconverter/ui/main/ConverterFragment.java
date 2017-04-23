package ru.kostikov.currencyconverter.ui.main;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.presenter.ConverterContract;
import ru.kostikov.currencyconverter.ui.chooser.ChooseActivity;
import ru.kostikov.currencyconverter.util.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterFragment extends Fragment implements ConverterContract.View, View.OnClickListener {

    private static final String TAG = ConverterFragment.class.getSimpleName();

    private static final int RESULT_ID_CUR_FROM = 1;
    private static final int RESULT_ID_CUR_TO = 2;

    private final String FLAG_ASSETS_FOLDER = "flags/";


    private ConverterContract.Presenter mConverterPresenter;

    Button mOneBtn;
    Button mTwoBtn;
    Button mThreeBtn;
    Button mFourBtn;
    Button mFiveBtn;
    Button mSixBtn;
    Button mSevenBtn;
    Button mEightBtn;
    Button mNineBtn;
    Button mZeroBtn;
    Button mDotBtn;
    Button mClearBtn;

    View mCurrencyFromLayout;
    View mCurrencyToLayout;

    ImageView mCurrencyFromFlag;
    ImageView mCurrencyToFlag;

    TextView mCurrencyFromCharNum;
    TextView mCurrencyToCharNum;

    TextView mCurrencyFromQuantity;
    TextView mCurrencyToResult;

    FloatingActionButton floatingActionButton;

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

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttons_init(view);
        converter_view_init(view);

        mConverterPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(ConverterContract.Presenter presenter) {
        mConverterPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_one:
                Log.d(TAG, "Btn one");
                mConverterPresenter.setNumber(1);
                break;
            case R.id.btn_two:
                Log.d(TAG, "Btn two");
                mConverterPresenter.setNumber(2);
                break;
            case R.id.btn_three:
                mConverterPresenter.setNumber(3);
                break;
            case R.id.btn_four:
                mConverterPresenter.setNumber(4);
                break;
            case R.id.btn_five:
                mConverterPresenter.setNumber(5);
                break;
            case R.id.btn_six:
                mConverterPresenter.setNumber(6);
                break;
            case R.id.btn_seven:
                mConverterPresenter.setNumber(7);
                break;
            case R.id.btn_eight:
                mConverterPresenter.setNumber(8);
                break;
            case R.id.btn_nine:
                mConverterPresenter.setNumber(9);
                break;
            case R.id.btn_zero:
                mConverterPresenter.setNumber(0);
                break;
            case R.id.btn_dot:
                mConverterPresenter.setDot();
                break;
            case R.id.btn_clear:
                mConverterPresenter.clear();
                break;
            default:
                break;
        }
    }

    private void buttons_init(View view) {
        this.mOneBtn = (Button) view.findViewById(R.id.btn_one);
        this.mTwoBtn = (Button) view.findViewById(R.id.btn_two);
        this.mThreeBtn = (Button) view.findViewById(R.id.btn_three);
        this.mFourBtn = (Button) view.findViewById(R.id.btn_four);
        this.mFiveBtn = (Button) view.findViewById(R.id.btn_five);
        this.mSixBtn = (Button) view.findViewById(R.id.btn_six);
        this.mSevenBtn = (Button) view.findViewById(R.id.btn_seven);
        this.mEightBtn = (Button) view.findViewById(R.id.btn_eight);
        this.mNineBtn = (Button) view.findViewById(R.id.btn_nine);
        this.mZeroBtn = (Button) view.findViewById(R.id.btn_zero);
        this.mDotBtn = (Button) view.findViewById(R.id.btn_dot);
        this.mClearBtn = (Button) view.findViewById(R.id.btn_clear);

        this.mOneBtn.setOnClickListener(this);
        this.mTwoBtn.setOnClickListener(this);
        this.mThreeBtn.setOnClickListener(this);
        this.mFourBtn.setOnClickListener(this);
        this.mFiveBtn.setOnClickListener(this);
        this.mSixBtn.setOnClickListener(this);
        this.mSevenBtn.setOnClickListener(this);
        this.mEightBtn.setOnClickListener(this);
        this.mNineBtn.setOnClickListener(this);
        this.mZeroBtn.setOnClickListener(this);
        this.mDotBtn.setOnClickListener(this);
        this.mClearBtn.setOnClickListener(this);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.flip_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConverterPresenter.flipCurrency();
            }
        });
    }

    private void converter_view_init(View view) {

        this.mCurrencyFromLayout = view.findViewById(R.id.currency_from_layout);
        this.mCurrencyFromLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Currency From choose");
                Intent intent = new Intent(getContext(), ChooseActivity.class);

                startActivityForResult(intent, RESULT_ID_CUR_FROM);

            }
        });
        this.mCurrencyToLayout  = view.findViewById(R.id.currency_to_layout);
        this.mCurrencyToLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Currency To choose");

                Intent intent = new Intent(getContext(), ChooseActivity.class);
                startActivityForResult(intent, RESULT_ID_CUR_TO);
            }
        });

        this.mCurrencyFromFlag = (ImageView)view.findViewById(R.id.currency_from_image);
        this.mCurrencyToFlag = (ImageView)view.findViewById(R.id.currency_to_image);

        this.mCurrencyFromCharNum = (TextView)view.findViewById(R.id.currency_from_char_num_txt);
        this.mCurrencyToCharNum = (TextView)view.findViewById(R.id.currency_to_char_num_txt);

        this.mCurrencyFromQuantity = (TextView)view.findViewById(R.id.currency_from_quantity_txt);
        this.mCurrencyToResult = (TextView)view.findViewById(R.id.currency_to_quantity_txt);


    }

    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String charNum = data.getStringExtra(ChooseActivity.EXTRA_TAG);

        if (requestCode == RESULT_ID_CUR_FROM){
            mConverterPresenter.setCurrencyFrom(charNum);
        }else if (requestCode == RESULT_ID_CUR_TO){
            mConverterPresenter.setCurrencyTo(charNum);
        }
    }

    @Override
    public void setCurrencyFrom(String charNum) {
        ActivityUtils.loadImageFromAsset(getContext(), this.mCurrencyFromFlag, FLAG_ASSETS_FOLDER + charNum + ".png");
        mCurrencyFromCharNum.setText(charNum);
    }

    @Override
    public void setCurrencyTo(String charNum) {
        ActivityUtils.loadImageFromAsset(getContext(), this.mCurrencyToFlag, FLAG_ASSETS_FOLDER + charNum + ".png");
        mCurrencyToCharNum.setText(charNum);
    }

    @Override
    public void setQuantityFrom(String quantityFrom) {
        this.mCurrencyFromQuantity.setText(quantityFrom);
    }

    @Override
    public void setResultTo(String resultTo) {
        this.mCurrencyToResult.setText(resultTo);
    }
}
