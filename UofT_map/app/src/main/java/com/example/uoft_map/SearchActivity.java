package com.example.uoft_map;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    SearchView mySearchView;
    ListView myList;
    private ArrayAdapter<String> adapter;
    ArrayList<String> exampleList = (ArrayList<String>)MapsActivity.LC.getLocsManager().getfullNameList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mySearchView = (SearchView)findViewById(R.id.search);
        myList = findViewById(R.id.listsearch);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,exampleList);
        myList.setAdapter(adapter);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = myList.getItemAtPosition(position).toString();
                Loc foundItem = MapsActivity.LC.getLocsManager().getLocByFullName(name);
                MapsActivity.LC.setCurrentLoaction(foundItem);
                Intent intent = new Intent(SearchActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });



    }
}

