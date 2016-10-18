package com.jonathanlieblich.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mPlayersView;
    BaseAdapter mBaseAdapter;
    Button mAdd;
    Button mRemoveLast;
    ArrayList<String> mPlayerList;
    EditText mPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdd = (Button)findViewById(R.id.add);
        mRemoveLast = (Button)findViewById(R.id.remove);
        mPlayerName = (EditText)findViewById(R.id.player_name);
        mPlayersView = (ListView)findViewById(R.id.player_list);

        mPlayerList = new ArrayList<>();

        mBaseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mPlayerList.size();
            }

            @Override
            public Object getItem(int position) {
                return mPlayerList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if(convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    v = inflater.inflate(android.R.layout.simple_list_item_1, null);
                }

                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(mPlayerList.get(position));
                return v;
            }
        };

        mPlayersView.setAdapter(mBaseAdapter);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerName.getText().length() == 0) {
                    mPlayerName.setError("ENTER NAME");
                    return;
                }
                mPlayerList.add(mPlayerName.getText().toString());
                mPlayerName.setText("");
                mBaseAdapter.notifyDataSetChanged();
            }
        });

        mRemoveLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayerList.size() > 0) {
                    mPlayerList.remove(mPlayerList.size() - 1);
                    mBaseAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "No players to remove!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
