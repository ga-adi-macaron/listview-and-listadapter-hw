package com.korbkenny.listviewhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText mEdit;
    ArrayList<String> mList;
    Button mAdd;
    Button mRemove;
    ListView mListView;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = (EditText) findViewById(R.id.EditText);
        mList = new ArrayList<>();
        mAdd = (Button) findViewById(R.id.Add);
        mRemove = (Button) findViewById(R.id.Remove);
        mListView = (ListView) findViewById(R.id.TheListView);
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mList);

        mListView.setAdapter(mAdapter);

        mAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(mEdit.getText().toString().equals("")){
                    mEdit.setError("Can't add NOBODY");
                    return;
                }
                mList.add(mEdit.getText().toString());
                mAdapter.notifyDataSetChanged();
                mEdit.setText("");
                }
            });

        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.size() == 0){
                    mEdit.setError("Can't remove NOBODY");
                    return;
                }
                mList.remove(mList.size()-1);
                mAdapter.notifyDataSetChanged();
            }
        });


    }
}
