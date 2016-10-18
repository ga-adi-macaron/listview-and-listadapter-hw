package com.charlesdrews.footballroster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by charlie on 10/17/16.
 */

public class RosterAdapter extends BaseAdapter {

    // Save a reference to the data as a member variable
    private List<Player> mData;

    public RosterAdapter(List<Player> players) {
        mData = players;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if convertView is null
        // - if it is null, then we need to inflate an instance of our custom view and assign it to convertView
        // - if it isn't null, then it already has a instance of our custom view assigned to it
        if (convertView == null) {

            // This is where we specify which custom layout will be used in our ListView.
            // We do this by inflating an instance of the desired layout.
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.roster_entry, parent, false);
        }

        // Get references to the TextViews that are inside convertView
        TextView playerName = (TextView) convertView.findViewById(R.id.player_name);
        TextView playerPosition = (TextView) convertView.findViewById(R.id.player_position);

        // Populate the TextViews
        Player player = mData.get(position);
        playerName.setText(player.getName());
        playerPosition.setText(player.getPosition());

        return convertView;
    }
}
