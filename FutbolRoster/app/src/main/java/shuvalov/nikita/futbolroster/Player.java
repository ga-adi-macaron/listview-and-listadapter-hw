package shuvalov.nikita.futbolroster;

/**
 * Created by NikitaShuvalov on 10/17/16.
 */

public class Player {
    private String mName;
    private String mPosition;
    private int mImage;


    public Player(String name, String position){
        mName = name;
        mPosition=position;
        mImage = R.drawable.soccer_ball;
    }

    public String getName() {
        return mName;
    }

    public String getPosition() {
        return mPosition;
    }
}
