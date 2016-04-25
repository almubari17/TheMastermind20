package edu.up.cs301.mastermind;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Author: Ibrahim Almubarak, Jacob Bryant, Jamie Fisher
 * Version: 0.0.4a
 * Description: The action of actually placing a peg in an empty spot or replace
 * an existing one.
 */
public class MMPlacePegAction extends GameAction {

    //Instance Variables
    private Peg peg;
    private int index;
    /**
     * constructor for MMPlacePegAction
     *
     * @param player the player who created the action
     */
    public MMPlacePegAction(GamePlayer player) {
        super(player);
    }

    /**
     * Constructor for MMPlacePegAction
     *
     * @param player player doing action
     * @param initColor color of peg
     * @param initIndex index of peg
     */
    public MMPlacePegAction(GamePlayer player, int initColor, int initIndex) {

        super(player);
        peg = new Peg(initColor);
        index = initIndex;
    }


    //getters
    public Peg getPeg(){return peg;}

    public int getIndex(){return index;}
}
