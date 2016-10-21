package com.colinbradley.listview_listadapter_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> mPlayerListAdapter;
    ListView mListView;
    ArrayList<String> mPlayers;
    Button mAddButton;
    Button mRemoveButton;
    EditText mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRemoveButton = (Button)findViewById(R.id.removeButton);

        mAddButton = (Button)findViewById(R.id.addButton);

        mInput = (EditText)findViewById(R.id.playerNameInput);

        mPlayers = new ArrayList<>();

        mPlayerListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mPlayers);

        mListView = (ListView)findViewById(R.id.listview);

        mListView.setAdapter(mPlayerListAdapter);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInput.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter Player's Name", Toast.LENGTH_SHORT).show();

                }else {
                    mPlayers.add(mInput.getText().toString());
                    mInput.setText("");
                    mPlayerListAdapter.notifyDataSetChanged();
                }

            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayers.size() == 0){
                    Toast.makeText(MainActivity.this, "There are no Players to remove", Toast.LENGTH_SHORT).show();
                }else {
                    mPlayers.remove(mPlayers.size()-1);
                    mPlayerListAdapter.notifyDataSetChanged();
                }
            }
        });


    }




}
