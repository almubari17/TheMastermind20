package edu.up.cs301.mastermind;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Authors:  Ibrahim Almubarak,Jacob Bryant, Jamie Fisher.
 * Description: the action that places a combination rather than a single color.
 */
public class MMPlaceCombination extends GameAction {

    protected Peg[] combo= new Peg[4];

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MMPlaceCombination(GamePlayer player, PegCombination aCombination) {
        super(player);

        combo[0]= new Peg(aCombination.getColor1());
        combo[1]= new Peg(aCombination.getColor2());
        combo[2]= new Peg(aCombination.getColor3());
        combo[3]= new Peg(aCombination.getColor4());

    }
}
