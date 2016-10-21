package com.scottlindley.myapplication;

/**
 * Created by Scott Lindley on 10/17/2016.
 */

public class Player {
    private String mName;
    private String mPosition;

    public Player(String name, String position) {
        mName = name;
        mPosition = position;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(String position) {
        mPosition = position;
    }
}
