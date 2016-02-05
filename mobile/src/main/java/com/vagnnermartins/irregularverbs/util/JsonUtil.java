package com.vagnnermartins.irregularverbs.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vagnnermartins on 21/01/16.
 */
public class JsonUtil {

    public static final String TRANSACTIONS = "transactions.json";
    public static final String RATES = "rates.json";

    public static String readJson(Context context, String file) {
        BufferedReader br = null;
        String content = "";
        try {
            String sCurrentLine = "";
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(file)));
            while ((sCurrentLine = br.readLine()) != null) {
                Log.d("", sCurrentLine);
                content += sCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return content;
    }
}
