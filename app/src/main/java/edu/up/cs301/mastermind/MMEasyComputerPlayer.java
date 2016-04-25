package edu.up.cs301.mastermind;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Authors: Jacob Bryant, Jamie Fisher, Ibrahim Almubarak.
 */
public class MMEasyComputerPlayer extends GameComputerPlayer {
   private boolean done= false;

    /**
     * constructor inherited
     *
     * @param name the player's name (e.g., "John")
     */
    public MMEasyComputerPlayer(String name) {
        super(name);
    }

    /**
     * Inherited function to receive a copy of game state.
     *
     * @param info
     */
    @Override
    protected void receiveInfo(GameInfo info) {

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
     * The main bulk of the Ai logic here the Ai will make dumb guesses!!
     *
     * @return
     */
    protected void choosePegs() {
        Random random = new Random();

        //choose 4 random pegs and send placing actions to local game
        for (int i = 0; i < 4; i++) {
            game.sendAction(new MMPlacePegAction(this, random.nextInt(8) + 1, i));
        }
            game.sendAction(new MMPassTurnAction(this));
    }

    protected void chooseMasterPeg() {
        Random random= new Random();
        ArrayList<Integer> choices = new ArrayList<Integer>();
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
}


