package com.elysium.listview_and_adapters_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mPlayerName;
    Button mAdd;
    Button mRemove;
    ListView mListView;
    List<String> mPlayerList;
    ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerName = (EditText) findViewById(R.id.player_name);
        mAdd = (Button) findViewById(R.id.Add);
        mRemove = (Button) findViewById(R.id.Remove);
        mListView = (ListView) findViewById(R.id.list_view);
        mPlayerList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mPlayerList);

        mListView.setAdapter(mArrayAdapter);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText playerName = (EditText) findViewById(R.id.player_name);
                String playerNameIn = playerName.getText().toString();

                if (playerNameIn.isEmpty()) {
                    playerName.setError("Don't Leave Blank Fields!");
                }else {
                    mPlayerList.add(playerNameIn);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPlayerList.size() > 0) {
                    mPlayerList.remove(mPlayerList.size() - 1);
                    mArrayAdapter.notifyDataSetChanged();
                }else {
                    System.out.println("No Names to Remove");
                }
            }
        });
    }
}
