package com.scottlindley.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mEditPlayerName;
    private Spinner mPositionDropDown;
    private Button mAddPlayerButton;
    private Button mRemovePlayerButton;
    private ListView mPlayerListView;
    private BaseAdapter mBaseAdapter;
    private ArrayList<Player> mPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListView();
        initSpinner();
        initButtons();

    }

    public void initListView(){
        mPlayers = new ArrayList<>();
        mPlayerListView = (ListView)findViewById(R.id.list_view);
        mBaseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mPlayers.size();
            }

            @Override
            public Object getItem(int i) {
                return mPlayers.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view == null){
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    inflater.inflate(R.layout.player_views, null);
                }
                ImageView icon = (ImageView)view.findViewById(R.id.position_image);
                TextView name = (TextView)view.findViewById(R.id.list_item_player_name);
                TextView position = (TextView)view.findViewById(R.id.list_item_player_postion);

                name.setText(mPlayers.get(i).getName());
                position.setText(mPlayers.get(i).getPosition());
                switch(position.getText().toString().toLowerCase()){
                    case "quarterback":
                        break;
                    case "running back":
                        break;
                    case "tackle":
                        break;
                    case "guard":
                        break;
                    case "wide receiver":
                        break;
                    case "tight end":
                        break;
                    case "center":
                        break;
                }

                return view;
            }
        };
    }

    public void initButtons(){
        mAddPlayerButton = (Button)findViewById(R.id.add_player_button);
        mRemovePlayerButton = (Button)findViewById(R.id.remove_player_button);

        mAddPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRemovePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void initSpinner(){
        mPositionDropDown = (Spinner)findViewById(R.id.position_drop_down);
        ArrayList<String> positions = new ArrayList<>();
        positions.add("Quarterback");
        positions.add("Running Back");
        positions.add("Tackle");
        positions.add("Guard");
        positions.add("Wide Receiver");
        positions.add("Tight End");
        positions.add("Center");

        ArrayAdapter arradpt = new ArrayAdapter(
                MainActivity.this, android.R.layout.simple_spinner_dropdown_item, positions);
        arradpt.notifyDataSetChanged();
    }
}
