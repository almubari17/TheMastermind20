package edu.up.cs301.mastermind;

import java.io.Serializable;

/**
 * Authors: Jacob Bryant, Jamie Fisher, Ibrahim Almubarak
 * Version: 0.0.3a
 * Date: 3/15/2016
 * Description: describes a single peg.
 */
public class Peg implements Serializable {
    private static final long serialVersionUID = 20272013L;

    //Main color scheme in order to compare
    private static final int GRAY = -1;
    private static final int WHITE =0;
    private static final int BLACK =1;
    private static final int YELLOW= 2;
    private static final int GREEN = 3;
    private static final int DARK_BLUE=4;
    private static final int PURPLE =5;
    private static final int ORANGE =6;
    private static final int BROWN=7;
    private static final int RED = 8;
    private int color;
    private boolean checked =false;

    //constructor of an empty peg
    public Peg(){
        color = GRAY;
    }

    //constructor of a specific peg
    public Peg(int colorID){
        color = colorID;
    }

    //getter for color of the peg
    public int getColor(){
        return color;
    }

    //setter for the color of peg used in testing
    public void setColor(int newColor){
        color = newColor;
    }

    //not implemented yet
    public boolean isChecked(){
        return checked;

    }

    public void setChecked(boolean i)
    {
        checked=i;
    }
}
