package com.charlesdrews.footballroster;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Member variables to hold references to our views
    private EditText mPlayerNameInput;
    private Spinner mPositionDropDown;

    // Another member variable to hold our roster - a List of Player objects
    private List<Player> mRoster = new ArrayList<>();

    // Another member variable to hold a reference to our Adapter for the ListView
    private RosterAdapter mRosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the member variables
        mPlayerNameInput = (EditText) findViewById(R.id.name_input);
        mPositionDropDown = (Spinner) findViewById(R.id.position_dropdown);

        // Get references to the buttons (these could have been member variables, but they don't
        // really need to be because we don't need to access them from any other methods, so
        // local variables are fine).
        Button addButton = (Button) findViewById(R.id.add_button);
        Button removeButton = (Button) findViewById(R.id.remove_button);

        // Use "this" (a.k.a. the current instance of MainActivity) as the OnClickListener
        addButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);

        // Set up the ListView and its Adapter
        ListView rosterListView = (ListView) findViewById(R.id.roster_listView);
        mRosterAdapter = new RosterAdapter(mRoster);
        rosterListView.setAdapter(mRosterAdapter);
    }

    // Implement the necessary method from the View.OnClickListener interface, so that MainActivity
    // can be used as an OnClickListener for the Buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                addPlayer();
                break;
            case R.id.remove_button:
                removeLastPlayer();
                break;
        }
    }

    private void addPlayer() {
        // Check if the user provided a valid (non-blank) name
        if (isInputValid()) {
            Player newPlayer = createPlayerFromUserInput();

            // If the new player isn't already in the roster, add the player
            if (mRoster.contains(newPlayer)) {
                Toast.makeText(this, "That player is already in your roster", Toast.LENGTH_SHORT).show();
            }
            else {
                mRoster.add(newPlayer);

                // Notify the Adapter that the ArrayList it references has been updated
                mRosterAdapter.notifyDataSetChanged();

                // Clear out the EditText
                mPlayerNameInput.setText("");

                // Close the keyboard so the user can see the ListView better
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mPositionDropDown.getWindowToken(), 0);
            }
        }
    }

    private void removeLastPlayer() {
        // Can only remove a player if the list is non-empty
        if (mRoster.size() > 0) {

            // Get a reference to the last player added
            Player lastPlayer = mRoster.get(mRoster.size() - 1);

            // Remove that player from the roster
            mRoster.remove(lastPlayer);

            // Notify the Adapter that the ArrayList it references has been updated
            mRosterAdapter.notifyDataSetChanged();

            // Notify the user that the player was removed
            Toast.makeText(this, "Removed " + lastPlayer.getName() + " from roster", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInputValid() {
        // Name is valid as long as it isn't empty - if it is empty set an error message
        if (mPlayerNameInput.getText().toString().isEmpty()) {
            mPlayerNameInput.setError(getString(R.string.player_name_input_error));
            return false;
        }
        else {
            return true;
        }
    }

    private Player createPlayerFromUserInput() {
        // Get the info provided by the user and create a new Player object
        String name = mPlayerNameInput.getText().toString();
        String position = mPositionDropDown.getSelectedItem().toString();
        return new Player(name, position);
    }
}
