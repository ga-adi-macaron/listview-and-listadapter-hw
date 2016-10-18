package shuvalov.nikita.futbolroster;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    Button addButt;
    Button remButt;
    EditText userInp;
    ListView listView;
    Spinner spinner;

    BaseAdapter baseAdapter;

    ArrayList<Player> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButt = (Button) findViewById(R.id.addButt);
        remButt = (Button) findViewById(R.id.removeButt);
        userInp = (EditText) findViewById(R.id.userInput);
        spinner =(Spinner) findViewById(R.id.position);

        listView = (ListView)findViewById(R.id.listView);

        players = new ArrayList<>();

        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return players.size();
            }

            @Override
            public Object getItem(int position) {
                return players.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView== null){
                    LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                    convertView= layoutInflater.inflate(R.layout.roster_format, null);
                    }
                TextView textView1 = (TextView) convertView.findViewById(R.id.pName);
                TextView textView2 = (TextView) convertView.findViewById(R.id.pPosition);
                textView1.setText(players.get(position).getName());
                textView2.setText(players.get(position).getPosition());
                return convertView;
                }

            };

        listView.setAdapter(baseAdapter);

        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean notDuplicate=true;
                HashMap<String, String> tempPlayInfo = new HashMap<String, String>();
                for (int i = 0; i<players.size();i++){
                    tempPlayInfo.put(players.get(i).getName().toLowerCase(), players.get(i).getPosition());
                    if(tempPlayInfo.containsKey(userInp.getText().toString().toLowerCase()) && tempPlayInfo.get(players.get(i).getName().toLowerCase()).equals(spinner.getSelectedItem().toString())){//If the entered player name exists in the temporary list and that player has the same position assigned to that name, then it's treated as a duplicate. Also added toLowerCase to make case insensitive.
                        Toast.makeText(MainActivity.this, players.get(i).getName()+" is already on the roster.", Toast.LENGTH_SHORT).show();
                        notDuplicate=false;
                    }
                }
                if(userInp.getText().toString().equals("")){
                    userInp.setError("Empty field");
                } else if(notDuplicate){
                    players.add(new Player(userInp.getText().toString(),spinner.getSelectedItem().toString()));
                    Toast.makeText(MainActivity.this, userInp.getText().toString()+" was added to roster.", Toast.LENGTH_SHORT).show();
                    baseAdapter.notifyDataSetChanged();
                    userInp.setText("");
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        remButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (players.isEmpty()){
                    Toast.makeText(MainActivity.this, "There are no players to remove", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, String.format("%s was removed from roster", players.get(players.size() - 1).getName()), Toast.LENGTH_SHORT).show();
                    players.remove(players.size() - 1);
                    baseAdapter.notifyDataSetChanged();
                }
            }
        });



    }


}
