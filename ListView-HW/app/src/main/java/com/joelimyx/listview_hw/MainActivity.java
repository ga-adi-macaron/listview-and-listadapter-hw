package com.joelimyx.listview_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Initialization
    private ListView mListView;
    private Button mAddButton, mRemoveButton;
    private EditText mEditText;
    List<Player> mPlayerList;
    BaseAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        mAddButton = (Button) findViewById(R.id.addButton);
        mRemoveButton = (Button) findViewById(R.id.removeButton);
        mEditText = (EditText) findViewById(R.id.editText);
        mPlayerList = new ArrayList<>();

        myAdapter = new BaseAdapter() {
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
                if(convertView==null){
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    convertView = inflater.inflate(R.layout.player_adapter,null);
                }
                TextView textView = (TextView) convertView.findViewById(R.id.text1);
                textView.setText(mPlayerList.get(position).getName());
                return convertView;
            }
        };

        mListView.setAdapter(myAdapter);
        mAddButton.setOnClickListener(this);
        mRemoveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
                String temp = mEditText.getText().toString();
                mPlayerList.add(new Player(temp));
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.removeButton:
                if (!mPlayerList.isEmpty()) {
                    mPlayerList.remove(mPlayerList.size() - 1);
                    myAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
