package ru.kostikov.currencyconverter.ui.chooser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.data.CurrencyData;
import ru.kostikov.currencyconverter.util.ActivityUtils;
import ru.kostikov.currencyconverter.util.Injector;

/**
 * Created by user on 23.04.2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private final String FLAG_ASSETS_FOLDER = "flags/";

    private List<CurrencyData> mCurrencyDataList;
    private ItemClickListener mItemClickListener;


    public CurrencyAdapter(HashMap<String, CurrencyData> dataMap, ItemClickListener itemClickListener) {
        mCurrencyDataList = new ArrayList<>(dataMap.values());

        mItemClickListener = itemClickListener;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_currency_choose, parent, false);
        return new  CurrencyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        holder.charNum.setText(mCurrencyDataList.get(position).getCharCode());
        holder.fullName.setText(mCurrencyDataList.get(position).getName());
        String flag_assets_img = FLAG_ASSETS_FOLDER + mCurrencyDataList.get(position).getCharCode() + ".png";
        ActivityUtils.loadImageFromAsset(Injector.instance().getAppContext(), holder.flag,flag_assets_img);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null){
                    TextView textView = (TextView)view.findViewById(R.id.char_num_txt);
                    mItemClickListener.itemClick(textView.getText().toString());
                }
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mCurrencyDataList.size();
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder{

        TextView fullName;
        TextView charNum;
        ImageView flag;
        View item;

        CurrencyViewHolder(View itemView) {
            super(itemView);

            item = itemView;
            fullName = (TextView)itemView.findViewById(R.id.full_name_txt);
            charNum  = (TextView)itemView.findViewById(R.id.char_num_txt);
            flag = (ImageView)itemView.findViewById(R.id.currency_flag);
        }
    }

    public interface ItemClickListener{
        void itemClick(String charNum);
    }
}
