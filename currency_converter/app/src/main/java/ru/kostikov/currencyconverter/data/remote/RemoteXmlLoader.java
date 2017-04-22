package ru.kostikov.currencyconverter.data.remote;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 22.04.2017.
 */

public class RemoteXmlLoader extends AsyncTaskLoader<String> {

    public static final String ARG_URL = "ARG_URL";

    private String mUrl = "";



    public RemoteXmlLoader(Context context, Bundle args) {
        super(context);
        if (args != null)
            mUrl = args.getString(ARG_URL);
    }

    @Override
    public String loadInBackground() {
        return getXMLFromUrl(mUrl);
    }

    private String getXMLFromUrl(String url) {

        BufferedReader br = null;
        try {
            HttpURLConnection conn = (HttpURLConnection)(new URL(url)).openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                line = line.replace('\t', ' ').trim();
                sb.append(line);
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
