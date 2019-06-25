package com.example.uoft_map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

public class SearchActivity extends AppCompatActivity {
    SearchView mySearchView;
    ListView myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mySearchView = findViewById(R.id.namesearch);
        myList = findViewById(R.id.listsearch);
    }
}
