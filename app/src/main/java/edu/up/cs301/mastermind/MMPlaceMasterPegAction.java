package edu.up.cs301.mastermind;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Authors: Jacob Bryant, Jamie Fisher, Ibrahim Almubarak.
 * Description: the action that places a master peg
 */
public class MMPlaceMasterPegAction extends GameAction {

    //Instance Variables
    private Peg peg;
    private int index;

    /**
     * constructor for MMPlaceMasterPegAction
     *
     * @param player the player who created the action
     */
    public MMPlaceMasterPegAction(GamePlayer player) {
        super(player);
    }

    /**
     * Constructor for MMPlaceMasterPegAction
     *
     * @param player player doing action
     * @param initColor color of peg
     * @param initIndex index of peg
     */
    public MMPlaceMasterPegAction(GamePlayer player, int initColor, int initIndex) {

        super(player);
        peg = new Peg(initColor);
        index = initIndex;
    }

    //getters
    public Peg getPeg(){return peg;}

    public int getIndex(){return index;}
}
