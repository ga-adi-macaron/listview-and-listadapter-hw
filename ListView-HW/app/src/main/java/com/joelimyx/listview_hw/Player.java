package com.joelimyx.listview_hw;

/**
 * Created by Joe on 10/17/16.
 */

public class Player {
    private String mName;
    private String mRole;

    public Player(String name,String role) {
        mName = name;
        mRole = role;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }
}
