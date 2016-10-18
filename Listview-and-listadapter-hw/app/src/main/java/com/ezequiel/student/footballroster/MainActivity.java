package com.ezequiel.student.footballroster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    Button mAddButton;
    Button mRmButton;
    ArrayList<String> mList;
    ListView mListView;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mAddButton = (Button) findViewById(R.id.add_button);
        mRmButton = (Button) findViewById(R.id.rm_button);

        mList = new ArrayList<>();

        mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, mList);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.getText().length() == 0){
                    mEditText.setError("Enter player name!");
                    return;
                }

                mList.add(mEditText.getText().toString());
                mEditText.setText("");
                mAdapter.notifyDataSetChanged();
            }
        });

        mRmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mList.isEmpty()){
                    mList.remove(mList.size() -1);
                    Toast.makeText(MainActivity.this, "Removed from Roster",
                            Toast.LENGTH_SHORT).show();
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Roster is Empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
