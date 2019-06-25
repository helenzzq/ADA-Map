package com.example.uoft_map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MapLocationFragment extends android.support.v4.app.Fragment {

    private Loc location;
    public MapLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ToDo 1 需要从Activity 里面获取（Loc）location， 并通过location的info来更改此fragment的default strings
        view = inflater.inflate(R.layout.fragment_map_location, container, false);
        navButtonListener();
        FragSetString(location);
        return view;

    }

    private View view;

    private void FragSetString(Loc location){
        TextView address = (TextView) getView().findViewById(R.id.locAddress);
        TextView name = (TextView) getView().findViewById(R.id.locName);
        TextView abbrv = (TextView) getView().findViewById(R.id.locAbbriviation);

        address.setText(location.getAddress());
        name.setText(location.getFullName());
        abbrv.setText(location.getAbsName());
    }
    private void navButtonListener(){
        //ToDo 2 当用户点击此button时 根据坐标跳转致 Google Map
//        Intent tmp = new Intent(getActivity(), MapsActivity.class);
//        tmp.putExtra("accManager", NULL);
//        FileSaver.saveToFile(getActivity(), boardManager,
//                GameCenterActivity.TEMP_SAVE_FILENAME);
//        startActivity(tmp);
    }

}
