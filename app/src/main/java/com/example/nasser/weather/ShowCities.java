package com.example.nasser.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;





/* TODO: need to know more about the objecs of the json response in order to complete the fragment
    we need:
        A class which containts the temp from the json reponse
        JSON serialzer and deserialzer
        Use shared prefernces
        Make another fragment to fetch data from shared prefernces
        Another fragment which containts the temp for the next 5 days for each city
 */




public class ShowCities extends Fragment {

    public ShowCities() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_cities, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        return view;
    }



}
