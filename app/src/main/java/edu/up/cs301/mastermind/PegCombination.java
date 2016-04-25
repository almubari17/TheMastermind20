package edu.up.cs301.mastermind;

import java.io.Serializable;

/**
 * Authors: Jacob Bryant, Jamie Fisher, Ibrahim Almubarak
 * Version: 0.0.3a
 * Date: 3/15/2016
 * Description: Used to decribe a combination of pegs
 */
public class PegCombination implements Serializable {
    private static final long serialVersionUID = 10472013L;
    private int color1;
    private int color2;
    private int color3;
    private int color4;
    private int white=-1;
    private int red=-1;

    public PegCombination(int c1,int c2, int c3, int c4)
    {
        color1=c1;
        color2=c2;
        color3=c3;
        color4=c4;

    }

    public void setFeedbackPegs(int w,int r)
    {
        white=w;
        red=r;
    }

    public int getColor1()
    {
        return color1;
    }

    public int getColor2()
    {
        return color2;
    }

    public int getColor3(){
        return color3;
    }

    public int getColor4()
    {
        return color4;
    }
    public int getWhite()
    {
        return white;
    }
    public int getRed()
    {
        return red;
    }
    //Check if combination has a specific color
    public boolean contains(int aColor)
    {
        if((aColor== color1)||(aColor==color2)||(aColor==color3)||(aColor==color4)||(aColor==color4))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //Overloaded checks if combination has multiple colors
    public int contains(int[] aColor)
    {
        int num=0;
        for (int i=0; i<aColor.length;i++)
        {
            if((aColor[i]== color1)||(aColor[i]==color2)||(aColor[i]==color3)||(aColor[i]==color4)||(aColor[i]==color4))
            {
                num++;
            }

        }
        return num;
    }
    //checks how many reds it got.
    public int containsRed(int[] aColor)
    {
        int num=0;
            if(aColor[0]==color1)
            {
                num++;
            }

            if(aColor[1]==color2)
            {
                num++;
            }

            if(aColor[2]==color3) {
            num++;
            }

            if(aColor[3]==color4)
            {
                num++;
            }
        return num;
    }
}
