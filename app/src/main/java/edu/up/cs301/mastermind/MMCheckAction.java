package edu.up.cs301.mastermind;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Author: Jamie Fisher, Jacob Bryant, Ibrahim Almubarak
 * Version: 1.0.0
 * Description: The action for checking a guess
 */
public class MMCheckAction extends GameAction {

    /**
     * constructor for MMCheckAction
     *
     * @param player the player who created the action
     */
    public MMCheckAction(GamePlayer player) {
        super(player);
    }
}
