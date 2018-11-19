package com.example.nasser.weather;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CityTemperature extends Fragment {
    TemperatureValues temperatureValues;

    public CityTemperature() {
        temperatureValues = TemperatureValues.getInstance();

    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_temperature, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        DownloadJsonTask downloadJsonTask = new DownloadJsonTask(listView, getActivity());
        downloadJsonTask.execute("https://goo.gl/etSf5m");
        return view;
    }

    class CustomBaseAdapter extends BaseAdapter {
        Activity mContext;
        public CustomBaseAdapter(Activity context){
            if(context == null){
                Log.e("CustomAdapter: ", "Context is null");
            }else{
                this.mContext = context;
            }
        }

        @Override
        public int getCount(){
            return temperatureValues.getTemperature().size();

        }
        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public Temperature getItem(int position){
            return temperatureValues.getTemperature().get(position);
        }

        @Override
        public View getView(int position, View view, ViewGroup container){
            if(view == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getLayoutInflater();
                view = inflater.inflate(R.layout.list_item, null);
            }
            //Create an instance of temperature class to bind it with widgets
            Temperature temperature = new Temperature();
            temperature = getItem(position);
            TextView temperDate = (TextView) view.findViewById(R.id.temperDate);
            TextView temperValue = (TextView) view.findViewById(R.id.temperValue);
            temperDate.setText(convertDateToString(temperature.getDate()));
            temperValue.setText(Double.toString(temperature.getValue()));
            return view;
        }

        public String convertDateToString(Date date){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = df.format(date);
            return dateString;
        }

    }

}
