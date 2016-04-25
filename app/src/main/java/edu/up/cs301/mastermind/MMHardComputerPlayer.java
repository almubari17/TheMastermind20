package edu.up.cs301.mastermind;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Author: Ibrahim Almubarak, Jacob Bryant, Jamie Fisher.
 * Date 3/30/2016
 * Description: This class will be used to create a smart AI who is going
 *              To beat Dr.Vegdahl the majority of games played
 */
public class MMHardComputerPlayer extends GameComputerPlayer {
    private boolean done= false;
    private PegCombination[][][][] possible_choices ;
    private MMGameState aState;
    private PegCombination[] feedback;
    private ArrayList<PegCombination> previousChoices;
    /**
     * constructor inherited
     *
     * @param name the player's name (e.g., "John")
     */
    public MMHardComputerPlayer(String name) {
        super(name);
        possible_choices= new PegCombination [8][8][8][8];
        feedback= new PegCombination[10];
        previousChoices= new ArrayList<PegCombination>();
    }

    /**
     * Inherited function to receive a copy of game state.
     * @param info
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof MMGameState)) {
            return;
        }
        aState=(MMGameState)info;
        if (!(info instanceof MMGameState)) {
            return;
        }
        if (this.playerNum != ((MMGameState) info).getPlayerId()) {
            return;
        } else {
            if (playerNum == 0) {
                chooseMasterPeg();
            }
            else if(playerNum == 1)
            {
                choosePegs();
            }
        }

    }

    /**
     * The main bulk of the Ai logic here the Ai will make educated guesses!!
     *
     */
    protected void choosePegs() {
        Random random = new Random();
        ArrayList<PegCombination>aChoice= new ArrayList<PegCombination>();

        if(aState.getCurrentRow()==0) {
            //choose 4 random pegs and send placing actions to local game
            int [] choices = new int[4];
            choices[0]= random.nextInt(8)+1;
            choices[1]=choices[0];
            choices[2]=random.nextInt(8)+1;
            choices[3]=choices[2];
            for (int i=0;i<4;i++) {
                game.sendAction(new MMPlacePegAction(this,choices[i] ,i));
            }
            game.sendAction(new MMPassTurnAction(this));
            return;
        }

        /**
         External Citation
         Date: 8 April 2016
         Problem: Could not get the hard ai to be hard
         Resource:
         Dr. Veghdal
         Dr. Nuxoll

         Solution: I followed their suggestion and made some small modification
         */
        for (int i=0 ; i<8; i++)
        {
            for (int j =0; j<8; j++)
            {
                for (int k=0; k<8; k++)
                {
                    for (int l=0; l<8; l++)
                    {
                        possible_choices[i][j][k][l]=new PegCombination(i+1,j+1,k+1,l+1);
                            if(checkCombination(possible_choices[i][j][k][l])){
                                aChoice.add(possible_choices[i][j][k][l]);
                            }

                    }
                }
            }
        }
        Log.i("Number of choices:",aChoice.size()+"" );
        int choice = random.nextInt(aChoice.size());

            game.sendAction(new MMPlaceCombination(this,aChoice.get(choice)));

        game.sendAction(new MMPassTurnAction(this));
    }


    /**
     * Allows the ai to choose a master pegs
     */
    protected void chooseMasterPeg() {
        Random random= new Random();
        if(!done) {

            for (int i = 0; i < 4; i++) {
                game.sendAction(new MMPlaceMasterPegAction(this, random.nextInt(8) + 1, i));}
            game.sendAction(new MMPassTurnAction(this));
            done =true;

        }
        else{
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.sendAction(new MMCheckAction(this));

        }


    }

    /**
     * This method is used to compare a specific combination to what is already
     * on the board.
     * @param combination
     * @return boolean
     */

        public boolean checkCombination(PegCombination combination) {
        if (aState.currentRow>0)
        {
                MMGameState testState = new MMGameState();
                testState.masterCode[0]= new Peg(combination.getColor1());
                testState.masterCode[1]= new Peg(combination.getColor2());
                testState.masterCode[2]= new Peg(combination.getColor3());
                testState.masterCode[3]= new Peg(combination.getColor4());
            /**
             External Citation
             Date: 20 April 2016
             Problem: Ai kept crashing
             Resource:
             Dr.Vegdhal
             Solution: I messed up some logical operations.
             */
                for(int i=0;i<aState.getCurrentRow();i++){
                    for (int j=0;j<4;j++){
                    testState.board[i][j]=aState.board[i][j];
                    }
                    testState.checkCode();

                }

            for (int i=0;i<aState.getCurrentRow()+1;i++)
            {
                for (int j=0;j<4;j++)
                {
                    if(testState.checkCode[i][j].getColor()!=aState.checkCode[i][j].getColor())
                    {
                        return false;
                    }
                }
            }
            return true;

        }
            return false;
        }
    }

