package edu.up.cs301.mastermind;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Authors: Ibrahim Almubarak, Jacob Bryant, Jamie Fisher.
 * Version: 0.0.4a
 * Date: 3/30/2016
 *Description: This class will be used to specify the rules of the game.
 * The game state will be instantiated
 */

public class MMLocalGame extends LocalGame {
    MMGameState game;

    public MMLocalGame() {
        game = new MMGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        MMGameState copy = new MMGameState(game);

        p.sendInfo(copy);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if (game.playerId == playerIdx) {
            return true;
        }
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        int num = 0;
        //checks for winning condition
        if (game.currentRow > 0) {
            for (int i = 0; i < 4; i++) {

                if (!(game.checkCode[game.currentRow - 1][i].getColor() == 8)) {
                    break;
                }
                num++;

            }
        }
        if (num == 4) {
            game.gameOver=true;
            return "" + playerNames[1] + " won";

        }

        if (game.currentRow >= 10) {
            game.currentRow = 1;
            game.gameOver=true;
            return "" + playerNames[0] + " won";

        }

        return null;
    }


    @Override
    protected boolean makeMove(GameAction action) {

        //placing a master code peg
        if (action instanceof MMPlaceMasterPegAction) {
            MMPlaceMasterPegAction placeMasterPegAction = (MMPlaceMasterPegAction) action;
            //affect game state
            game.placeMasterPeg(placeMasterPegAction.getIndex(), placeMasterPegAction.getPeg());
            return true;
        }
        //placing a guess peg
        else if (action instanceof MMPlacePegAction) {
            MMPlacePegAction placePegAction = (MMPlacePegAction) action;
            //affect game state
            game.placePeg(placePegAction.getIndex(), placePegAction.getPeg());
            return true;
        }
        else if(action instanceof MMPlaceCombination)
        {
            for (int i=0;i<4;i++) {
                game.placePeg(i,((MMPlaceCombination) action).combo[i]);
            }
            return true;
        }

        //check to see if game is over
        else if (action instanceof MMCheckAction) {
            game.checkCode();
            game.playerId=1-game.playerId;
            return true;
        } else if (action instanceof MMPassTurnAction) {
            game.passTurn();
            return true;
        } else {

            return false;
        }
    }
}


