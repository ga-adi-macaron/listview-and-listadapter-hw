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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mEditPlayerName;
    private Spinner mPositionDropDown;
    private Button mAddPlayerButton;
    private Button mRemovePlayerButton;
    private ListView mPlayerListView;
    private BaseAdapter mBaseAdapter;
    private ArrayList<Player> mPlayers;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditPlayerName = (EditText)findViewById(R.id.player_name);

        initSpinner();
        initListView();
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
                    view = inflater.inflate(R.layout.player_views, null);
                }
                ImageView icon = (ImageView)view.findViewById(R.id.position_image);
                TextView name = (TextView)view.findViewById(R.id.list_item_player_name);
                TextView position = (TextView)view.findViewById(R.id.list_item_player_postion);

                name.setText(mPlayers.get(i).getName());
                position.setText(mPlayers.get(i).getPosition());
                switch(position.getText().toString().toLowerCase()){
                    case "quarterback":
                        icon.setImageResource(R.drawable.quarterback);
                        break;
                    case "running back":
                        icon.setImageResource(R.drawable.runningback);
                        break;
                    case "tackle":
                        icon.setImageResource(R.drawable.tackle);
                        break;
                    case "guard":
                        icon.setImageResource(R.drawable.guard);
                        break;
                    case "wide receiver":
                        icon.setImageResource(R.drawable.widereceiver);
                        break;
                    case "tight end":
                        icon.setImageResource(R.drawable.tightend);
                        break;
                    case "center":
                        icon.setImageResource(R.drawable.center);
                        break;
                }
                return view;
            }
        };
        mPlayerListView.setAdapter(mBaseAdapter);
        mBaseAdapter.notifyDataSetChanged();
    }

    public void initButtons(){
        mAddPlayerButton = (Button)findViewById(R.id.add_player_button);
        mRemovePlayerButton = (Button)findViewById(R.id.remove_player_button);

        mAddPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextString = mEditPlayerName.getText().toString();
                String positionString = mPositionDropDown.getSelectedItem().toString();
                for(Player player : mPlayers){
                    if(player.getName().equals(editTextString)
                        &&player.getPosition().equals(positionString)){
                        Toast.makeText(MainActivity.this, "Player already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (!(mEditPlayerName.getText().toString().equals(""))) {
                    mPlayers.add(new Player(editTextString, positionString));
                    mBaseAdapter.notifyDataSetChanged();
                }else{
                    mEditPlayerName.setError("Please enter player name");
                }
            }
        });

        mRemovePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mPlayers.isEmpty()){
                    mPlayers.remove(mPlayers.size()-1);
                    mBaseAdapter.notifyDataSetChanged();
                }
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
        mPositionDropDown.setAdapter(arradpt);
        arradpt.notifyDataSetChanged();

    }
}
