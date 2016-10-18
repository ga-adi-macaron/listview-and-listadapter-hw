package net.serkanbal.fantasyfootball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mPlayerName;
    Button mButtonAdd;
    Button mButtonRemove;
    ListView mListView;
    List<String> mPlayerList;
    ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerName = (EditText) findViewById(R.id.edittext);
        mButtonAdd = (Button) findViewById(R.id.buttonadd);
        mButtonRemove = (Button) findViewById(R.id.buttonremove);
        mListView = (ListView) findViewById(R.id.listview);

        mPlayerList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, mPlayerList);

        mListView.setAdapter(mArrayAdapter);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText playerName = (EditText) findViewById(R.id.edittext);
                String playerNameInput = playerName.getText().toString();
                if (playerNameInput.isEmpty()) {
                    playerName.setError("Can't be blank");
                } else {
                    mPlayerList.add(playerNameInput);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });

        mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerList.size() > 0) {
                    mPlayerList.remove(mPlayerList.size() - 1);
                    mArrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Nothing to delete!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
