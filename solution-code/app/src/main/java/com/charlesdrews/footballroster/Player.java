package com.charlesdrews.footballroster;

/**
 * Created by charlie on 10/17/16.
 */

public class Player {
    private String mName, mPosition;

    public Player(String name, String position) {
        mName = name;
        mPosition = position;
    }

    public String getName() {
        return mName;
    }

    public String getPosition() {
        return mPosition;
    }

    // Override this method to provide a custom implementation of equals() for this custom class
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player otherPlayer = (Player) obj;
            return mName.equals(otherPlayer.getName()) && mPosition.equals(otherPlayer.getPosition());
        }
        return false;
    }
}
