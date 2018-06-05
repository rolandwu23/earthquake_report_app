package com.example.akm.earthquake;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        mUrl=url;

    }


    protected void onStartLoading(){forceLoad();}
    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl==null){
            return null;
        }

        List<Earthquake>earthquakes=QueryUtils.fetchEarthquakeData(mUrl);
        return  earthquakes;
    }
}
