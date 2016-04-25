package edu.up.cs301.mastermind;

import android.content.pm.ActivityInfo;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * Author: Ibrahim Almubarak
 * Version:1.0.0
 * Date:4/25/2016
 * Description: This class handles the whole game.
 */
public class MMMainActivity extends GameMainActivity {
    final static int PORT_NUMBER= 5115;
    @Override
    public GameConfig createDefaultConfig() {
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new MMHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Random man") {
            public GamePlayer createPlayer(String name) {
                return new MMEasyComputerPlayer(name);
            }
        });

        playerTypes.add(new GamePlayerType("Steven Vegdahl") {
            public GamePlayer createPlayer(String name) {
                return new MMHardComputerPlayer(name);
            }});

        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Mastermind", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {
        return new MMLocalGame();
    }
}
