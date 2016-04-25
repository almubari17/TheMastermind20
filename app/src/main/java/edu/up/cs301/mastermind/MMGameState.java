package edu.up.cs301.mastermind;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Authors: Jacob Bryant, Jamie Fisher, Ibrahim Almubarak
 * Version: 0.0.3a
 * Date: 3/16/2016
 * Description: The current state of the game and
 * it implements all methods needed to play the game.
 */



public class MMGameState extends GameState {
    //Instance Variables
    protected Peg[][] board = new Peg[10][4];
    protected Peg [] masterCode= new Peg[4];
    protected Peg [] currentGuess = new Peg[4];
    protected int currentRow;
    protected Peg[][] checkCode= new Peg[10][4];
    protected static final int WHITE =0;
    protected static final int RED =8;
    protected int playerId;
    protected Boolean gameOver;

    /**
     * Main Constructor, initializes the board to be empty.
     */
    public MMGameState() {
        Random rand=new Random();
        //sets random code

        for (int i =0 ; i<10;i++)
        {
            for(int j= 0; j<4;j++)
            {
                board[i][j] = new Peg();
                checkCode [i][j] = new Peg();
            }
        }

        //Sets The Master Code and guess Pegs to empty Pegs

        for (int i = 0; i<4; i++)
        {
            masterCode[i]=  new Peg ();
            currentGuess[i] = new Peg();
        }
        currentRow=0;
        playerId=0;
        gameOver = false;
    }

    /**
     * Creates a "deep" copy.
     * @param aGame
     */
    public MMGameState(MMGameState aGame)
    {

        if(aGame==null)
        {
            return;
        }

        for (int i =0 ; i<10;i++)
        {
            for(int j= 0; j<4;j++)
            {
                this.board[i][j] = new Peg ( aGame.board[i][j].getColor());
                this.checkCode [i][j] = new Peg (aGame.checkCode[i][j].getColor());
            }
        }

        for (int i = 0; i<4; i++)
        {
            this.masterCode[i]= new Peg(aGame.masterCode[i].getColor());
            this.currentGuess[i] = new Peg(aGame.currentGuess[i].getColor());
        }
        this.currentRow= aGame.currentRow;
        this.playerId= aGame.playerId;
        this.gameOver = aGame.gameOver;
    }

    /**
     * Places pegs on the current row with specified position.
     * @param position
     * @param selected
     */

    public void placePeg(int position, Peg selected)
    {
        board[currentRow][position] = selected;
        currentGuess[position]= selected;
    }

    /**
     * Place pegs for the master code
     * @param position
     * @param selected
     */
    public void placeMasterPeg(int position, Peg selected)
    {
            masterCode[position]= selected;

    }

    /**
     * Compares the current row of pegs with the master code.
     * Assigns indication pegs to checkCode.
     *
     */
    public void checkCode()
    {
        if(currentRow>=10)
        {
            return;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i=0;i<4;i++)
        {

            currentGuess[i]=board[currentRow][i];
            masterCode[i].setChecked(false);
            currentGuess[i].setChecked(false);
        }

        int count=0;
        int color;
        //Double nested loop that loops through guess pegs to compare with master code
        for (int i =0; i<4; i++)
        {
            if(currentGuess[i].getColor() == masterCode[i].getColor())
            {
                result.add(RED);
                masterCode[i].setChecked(true);
                currentGuess[i].setChecked(true);

            }
        }

        //checks for white peg
        for (int i=0 ; i<4; i++) {


            //sets the color to gray (empty).
            color = -1;


            for (int j = 0; j < 4; j++) {


                //checks for white pegs
                if(( currentGuess[i].getColor() == masterCode[j].getColor())&& (i!=j)&&(!(masterCode[j].isChecked()))&&(!(currentGuess[i].isChecked())))
                {
                    result.add(WHITE);
                    masterCode[j].setChecked(true);
                    currentGuess[i].setChecked(true);
                }
            }

        }

        for (int i=0;i<result.size();i++)
        {
            checkCode[currentRow][i].setColor(result.get(i));
        }


        currentRow++;
        if(currentRow==10)
        {
            gameOver=true;
        }
        int reds=0;
        for (int i =0; i<result.size(); i++)
        {
            if(result.get(i)==8)
            {
                reds++;
            }
        }
        if (reds==4){
            gameOver=true;
        }


    }

    public boolean checkCode(int[] choice)
    {
        if(currentRow>=10)
        {
            return false;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i=0;i<4;i++)
        {

            currentGuess[i]=board[currentRow][i];
            masterCode[i].setChecked(false);
            currentGuess[i].setChecked(false);
        }

        int count=0;
        int color;
        //Double nested loop that loops through guess pegs to compare with master code
        for (int i =0; i<4; i++)
        {
            if(currentGuess[i].getColor() == masterCode[i].getColor())
            {
                result.add(RED);
                masterCode[i].setChecked(true);
                currentGuess[i].setChecked(true);

            }
        }

        //checks for white peg
        for (int i=0 ; i<4; i++) {


            //sets the color to gray (empty).
            color = -1;


            for (int j = 0; j < 4; j++) {


                //checks for white pegs
                if(( currentGuess[i].getColor() == masterCode[j].getColor())&& (i!=j)&&(!(masterCode[j].isChecked()))&&(!(currentGuess[i].isChecked())))
                {
                    result.add(WHITE);
                    masterCode[j].setChecked(true);
                    currentGuess[i].setChecked(true);
                }
            }

        }

        int gameWhite = 0;
        int gameRed = 0;
        int userWhite = 0;
        int userRed = 0;
        for (int i=0;i<result.size();i++){
            if(result.get(i) == 0){
                gameWhite++;
            } else if(result.get(i) == 8){
                gameRed++;
            }
        }
        for (int i=0;i<choice.length;i++)
        {
            if(choice[i] == 0){
                userWhite++;
            } else if(choice[i] == 8){
                userRed++;
            }
        }

        if(gameRed == userRed && gameWhite == userWhite){
            return true;
        } else {
            return false;
        }
    }


    /**
     * returns current row.
     * @return
     */
    public int getCurrentRow() {
        return currentRow;
    }

    public int getPlayerId()
    {
        return playerId;
    }

     public void passTurn()
     {
       if (playerId==0)
       {
           playerId=1;
       }
         else if (playerId==1)
       {
           playerId=0;
       }
     }
}
