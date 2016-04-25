package edu.up.cs301.mastermind;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Authors: Jamie Fisher, Jacob Bryant, Ibrahim Almubarak.
 * Description: Passing the turn
 */
public class MMPassTurnAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MMPassTurnAction(GamePlayer player) {
            super(player);
    }
}
