package com.justinwells.listviewhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<String> players;
    Button enter, remove;
    ArrayAdapter <String>  adapter;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        players = new ArrayList<>();
        editText = (EditText) findViewById(R.id.player_name);
        enter = (Button) findViewById(R.id.button);
        remove= (Button) findViewById(R.id.button2);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                players);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (players.get(0) != null)
                    players.remove(0);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
