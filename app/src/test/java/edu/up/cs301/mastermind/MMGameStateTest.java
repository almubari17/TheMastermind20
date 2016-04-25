package edu.up.cs301.mastermind;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Authors: Jacob Bryant, Jamie Fisher, Ibrahim Almubarak
 * Version: 0.0.3a
 * Date: 3/16/2016
 * Description: UNIT TEST FOR THE GAME
 */
public class MMGameStateTest {

    /**
     * checks the current row is initialized to first row
     * @throws Exception
     */
    @Test
    public void currentRowInit() throws Exception {
        MMGameState game = new MMGameState();

        //
        assertEquals(0,game.getCurrentRow());
    }

    /**
     * checks if the board is empty at the start of a game
     * @throws Exception
     */
    @Test
    public void boardSetEmpty() throws Exception {
        MMGameState game = new MMGameState();

        assertEquals(-1, game.board[2][3].getColor());
    }

    /**
     * Makes sure the deep copy is created correctly.
     * @throws Exception
     */
    @Test
    public void deepCopyTest() throws Exception {
        MMGameState game = new MMGameState();

        game.board[1][1].setColor(3);

        MMGameState copy = new MMGameState(game);

        assertEquals(game.board[1][1].getColor(),copy.board[1][1].getColor());
    }

    /**
     * Checks if the pegs are being placed correctly
     * @throws Exception
     */
    @Test
    public void testPlacePeg() throws Exception {
        MMGameState game = new MMGameState();

        Peg redPeg = new Peg(10);

        game.placePeg(2,redPeg);

        assertEquals(redPeg.getColor(),game.board[0][2].getColor());
    }

    /**
     * checks if the master pegs are placed correctly and
     * if they interact with placed pegs.
     * Checks the winning condition.
     * @throws Exception
     */
    @Test
    public void testPlaceMasterPeg() throws Exception {
        MMGameState game = new MMGameState();

        game.placeMasterPeg(0,new Peg(3));
        game.placeMasterPeg(1,new Peg(5));
        game.placeMasterPeg(2,new Peg(6));
        game.placeMasterPeg(3,new Peg(2));

        game.placePeg(0, new Peg(3));
        game.placePeg(1, new Peg(5));
        game.placePeg(2,new Peg(6));
        game.placePeg(3, new Peg(2));

        game.checkCode();

        assertTrue(game.gameOver);
    }

    /**
     * testing if red peg is the answer with white condition
     * @throws Exception
     */
    @Test
    public void testCheckCodeRed() throws Exception {
        MMGameState game = new MMGameState();

        game.placeMasterPeg(0,new Peg(3));
        game.placeMasterPeg(1,new Peg(5));
        game.placeMasterPeg(2,new Peg(3));
        game.placeMasterPeg(3,new Peg(2));

        game.placePeg(0, new Peg(3));
        game.placePeg(1, new Peg(5));
        game.placePeg(2, new Peg(6));
        game.placePeg(3, new Peg(2));

        game.checkCode();

        assertEquals(10, game.checkCode[0][0].getColor());
        assertEquals(10, game.checkCode[0][1].getColor());
        assertEquals(10, game.checkCode[0][2].getColor());

    }

    /**
     * test whether the first one would be white giving a complex
     * multi layered peg combination.
     * @throws Exception
     */
    @Test
    public void testComplexCodeWhite() throws Exception {
        MMGameState game = new MMGameState();

        game.placeMasterPeg(0,new Peg(3));
        game.placeMasterPeg(1,new Peg(5));
        game.placeMasterPeg(2,new Peg(4));
        game.placeMasterPeg(3,new Peg(2));

        game.placePeg(0, new Peg(4));
        game.placePeg(1, new Peg(5));
        game.placePeg(2, new Peg(6));
        game.placePeg(3, new Peg(7));

        game.checkCode();

        assertEquals(0,game.checkCode[0][0].getColor());
    }
}