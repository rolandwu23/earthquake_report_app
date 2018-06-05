package com.example.akm.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listitemview=convertView;
        if(listitemview==null){
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Earthquake currentEarthquake=getItem(position);
        TextView magnitudeView=(TextView)listitemview.findViewById(R.id.magnitude);
        String formattedMagnitude=formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle=(GradientDrawable)magnitudeView.getBackground();
        int magnitudeColor=getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String originialLocation=currentEarthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        if(originialLocation.contains(LOCATION_SEPARATOR)){
            String[] parts=originialLocation.split(LOCATION_SEPARATOR);
            locationOffset=parts[0]+LOCATION_SEPARATOR;
            primaryLocation=parts[1];
        }
        else{
            locationOffset="Near the";
            primaryLocation=originialLocation;
        }

        TextView primaryLocationView=(TextView)listitemview.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView=(TextView)listitemview.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        Date dateobject=new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateView=(TextView)listitemview.findViewById(R.id.date);
        String formattedDate=formatDate(dateobject);
        dateView.setText(formattedDate);

        TextView timeView=(TextView)listitemview.findViewById(R.id.time);
        String formattedTime=formatTime(dateobject);
        timeView.setText(formattedTime);


        return listitemview;
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId=0;
        int magnitudeFloor=(int)Math.floor(magnitude);
        switch (magnitudeFloor){
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;


        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeformat=new DecimalFormat("0.0");
        return magnitudeformat.format(magnitude);
    }

    private String formatDate(Date dateobject){
        SimpleDateFormat dateFormat=new SimpleDateFormat("LLL dd,yyy");
        return dateFormat.format(dateobject);
    }

    private String formatTime(Date dateobject){
        SimpleDateFormat dateFormat=new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateobject);
    }
}
