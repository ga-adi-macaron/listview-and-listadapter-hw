package com.joelimyx.listview_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Initialization
    private ListView mListView;
    private Button mAddButton, mRemoveButton;
    private EditText mEditText;
    private List<Player> mPlayerList;
    private BaseAdapter myAdapter;
    private Spinner mSpinner;
    private String mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaration
        mListView = (ListView) findViewById(R.id.list_view);
        mAddButton = (Button) findViewById(R.id.addButton);
        mRemoveButton = (Button) findViewById(R.id.removeButton);
        mEditText = (EditText) findViewById(R.id.editText);
        mPlayerList = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //Spinner Adapter
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.position_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //ListView Adapter
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
                TextView textView2 = (TextView) convertView.findViewById(R.id.text2);
                textView2.setText(mPlayerList.get(position).getRole());
                return convertView;
            }
        };

        // spinner Onclick
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mListView.setAdapter(myAdapter);
        mAddButton.setOnClickListener(this);
        mRemoveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
                String temp = mEditText.getText().toString();
                if (temp.isEmpty()){
                    mEditText.setError("Please Enter a Name");
                }else {
                    mPlayerList.add(new Player(temp, mPosition));
                    myAdapter.notifyDataSetChanged();
                }
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
