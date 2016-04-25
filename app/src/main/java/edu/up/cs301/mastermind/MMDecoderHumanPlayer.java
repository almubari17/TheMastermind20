package edu.up.cs301.mastermind;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * Author: Ibrahim Almubarak ,Jamie Fisher, Jacob Bryant,
 * Version: 1.0.0
 * Description: The gui for the decoder.
 *
 *
 *
 */
public class MMDecoderHumanPlayer extends MMHumanPlayer {
    ImageView[][] feedbackSpot; //The response the master code can place pegs there
    LinearLayout[] rows; //The linear layouts of each row
    ImageButton[][] board; //the actual board
    ImageView[] masterPegs; //The pegs chosen at the start of the game
    //The pegs
    protected ImageButton blackPin;
    protected ImageButton brownPin;
    protected ImageButton purplePin;
    protected ImageButton greenPin;
    protected ImageButton bluePin;
    protected ImageButton yellowPin;
    protected ImageButton redPin;
    protected ImageButton orangePin;
    protected ImageButton pinkPin;
    protected ImageButton lightBluePin;
    protected ImageView selectedPin;

    int color=0;//The selected color

    protected MMGameState gameState;//The game state
    protected GameMainActivity gameActivity; //the main activity
    protected Button checkButton; //The button used at the end of a guess
    protected MMHumanPlayer realPlayer; //The instance of the parent class
    protected Button quitButton; //Button used to exist the game
    protected MediaPlayer player; //The music of the game
    protected Spinner colorSpinner; //the spinner to change background
    protected LinearLayout background; //The overall background
    protected int backgroundColor; //the color of said background




    /**
     * constructor sets up real player for passing in
     * actions
     *
     * @param name
     */
    public MMDecoderHumanPlayer(String name, MMHumanPlayer p) {
        super(name);
        realPlayer=p;

    }

    @Override
    public View getTopView() {

        return null;

    }

    /**
     * redraws the board everytime an action
     * has been made.
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof MMGameState) {
            gameState = (MMGameState) info;
        }
        for(int i = 0;i < 10;i++) {
            for (int j = 0; j < 4; j++) {
                this.changeColor(board[i][j],gameState.board[i][j].getColor());
                this.feedBackColor(feedbackSpot[i][j], this.gameState.checkCode[i][j].getColor());
            }
        }

        //change highlighted row
        rowSelect();

        if(gameState.gameOver == true){
            //show master code
            for(int i = 0; i < 4; i++) {
                if(gameState.masterCode[i].getColor() == 1) {
                    masterPegs[i].setImageResource(R.mipmap.black_circle);
                }
                if(gameState.masterCode[i].getColor() == 2) {
                    masterPegs[i].setImageResource(R.mipmap.yellow_circle);
                }
                if(gameState.masterCode[i].getColor() == 3) {
                    masterPegs[i].setImageResource(R.mipmap.green_circle);
                }
                if(gameState.masterCode[i].getColor() == 4) {
                    masterPegs[i].setImageResource(R.mipmap.blue_circle);
                }
                if(gameState.masterCode[i].getColor() == 5) {
                    masterPegs[i].setImageResource(R.mipmap.purple_circle);
                }
                if(gameState.masterCode[i].getColor() == 6) {
                    masterPegs[i].setImageResource(R.mipmap.orange_circle);
                }
                if(gameState.masterCode[i].getColor() == 7) {
                    masterPegs[i].setImageResource(R.mipmap.brown_circle);
                }
                if(gameState.masterCode[i].getColor() == 8) {
                    masterPegs[i].setImageResource(R.mipmap.reball);
                }
            }
        }


    }

    /**
     * the on create method for the interface
     * @param activity
     */
    public void setAsGui(GameMainActivity activity) {
       //declaring variables
        activity.setContentView(R.layout.decoder_board);
        gameActivity = activity;
        blackPin = (ImageButton) activity.findViewById(R.id.blackButton);
        brownPin = (ImageButton) activity.findViewById(R.id.brownButton);
        orangePin = (ImageButton) activity.findViewById(R.id.orangeButton);
        redPin = (ImageButton) activity.findViewById(R.id.redButton);
        greenPin = (ImageButton) activity.findViewById(R.id.greenButton);
        yellowPin = (ImageButton) activity.findViewById(R.id.yellowButton);
        bluePin = (ImageButton) activity.findViewById(R.id.blueButton);
        purplePin = (ImageButton) activity.findViewById(R.id.purpleButton);
        pinkPin = (ImageButton) activity.findViewById(R.id.pinkButton);
        lightBluePin = (ImageButton) activity.findViewById(R.id.lightBlueButton);
        quitButton = (Button) activity.findViewById(R.id.quitButton);
        colorSpinner = (Spinner) activity.findViewById(R.id.colorSpinner);
        background = (LinearLayout) activity.findViewById(R.id.background);
        //setting up the music
        /**
         External Citation
         Date: 23 April 2016
         Problem: looping music
         Resource:
         http://stackoverflow.com/questions/9461270/media-player-looping-android
         Solution: I set looping to true (So much for a problem).
         resource credit: Credit of the music goes to: Rob Platt and NOTCH
         URL:https://www.youtube.com/watch?v=YPGnAZxmjtE
         */
        player= MediaPlayer.create(activity, R.raw.music);
        player.setLooping(true);
        player.start();

        rows = new LinearLayout[10];
        rows[0]= (LinearLayout) activity.findViewById(R.id.firstRow);
        rows[1]= (LinearLayout) activity.findViewById(R.id.secondRow);
        rows[2]= (LinearLayout) activity.findViewById(R.id.ThirdRow);
        rows[3]= (LinearLayout) activity.findViewById(R.id.fourthRow);
        rows[4]= (LinearLayout) activity.findViewById(R.id.fifthRow);
        rows[5]= (LinearLayout) activity.findViewById(R.id.sixthRow);
        rows[6]= (LinearLayout) activity.findViewById(R.id.seventhRow);
        rows[7]= (LinearLayout) activity.findViewById(R.id.eighthRow);
        rows[8]= (LinearLayout) activity.findViewById(R.id.ninethRow);
        rows[9]= (LinearLayout) activity.findViewById(R.id.tenthRow);

        //Listeners
        blackPin.setOnClickListener(this);
        brownPin.setOnClickListener(this);
        redPin.setOnClickListener(this);
        bluePin.setOnClickListener(this);
        greenPin.setOnClickListener(this);
        yellowPin.setOnClickListener(this);
        purplePin.setOnClickListener(this);
        orangePin.setOnClickListener(this);
        quitButton.setOnClickListener(this);

        selectedPin = (ImageView) activity.findViewById(R.id.selectedPin);

        //colorSpinner listener
        // get array of strings
        String[] spinnerNames = gameActivity.getResources().getStringArray(R.array.colors);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(gameActivity,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(new MySpinnerListener());
        colorSpinner.setSelection(0);

        //set master pegs
        masterPegs = new ImageView[4];
        masterPegs[0] = (ImageView) activity.findViewById(R.id.secretPin1);
        masterPegs[1] = (ImageView) activity.findViewById(R.id.secretPin2);
        masterPegs[2] = (ImageView) activity.findViewById(R.id.secretPin3);
        masterPegs[3] = (ImageView) activity.findViewById(R.id.secretPin4);

        //Set the board pin slots
        board = new ImageButton[10][4];
        board[0][0] = (ImageButton) activity.findViewById(R.id.emptyPin1);
        board[0][1] = (ImageButton) activity.findViewById(R.id.emptyPin2);
        board[0][2] = (ImageButton) activity.findViewById(R.id.emptyPin3);
        board[0][3] = (ImageButton) activity.findViewById(R.id.emptyPin4);
        board[1][0] = (ImageButton) activity.findViewById(R.id.emptyPin5);
        board[1][1] = (ImageButton) activity.findViewById(R.id.emptyPin6);
        board[1][2] = (ImageButton) activity.findViewById(R.id.emptyPin7);
        board[1][3] = (ImageButton) activity.findViewById(R.id.emptyPin8);
        board[2][0] = (ImageButton) activity.findViewById(R.id.emptyPin9);
        board[2][1] = (ImageButton) activity.findViewById(R.id.emptyPin10);
        board[2][2] = (ImageButton) activity.findViewById(R.id.emptyPin11);
        board[2][3] = (ImageButton) activity.findViewById(R.id.emptyPin12);
        board[3][0] = (ImageButton) activity.findViewById(R.id.emptyPin13);
        board[3][1] = (ImageButton) activity.findViewById(R.id.emptyPin14);
        board[3][2] = (ImageButton) activity.findViewById(R.id.emptyPin15);
        board[3][3] = (ImageButton) activity.findViewById(R.id.emptyPin16);
        board[4][0] = (ImageButton) activity.findViewById(R.id.emptyPin17);
        board[4][1] = (ImageButton) activity.findViewById(R.id.emptyPin18);
        board[4][2] = (ImageButton) activity.findViewById(R.id.emptyPin19);
        board[4][3] = (ImageButton) activity.findViewById(R.id.emptyPin20);
        board[5][0] = (ImageButton) activity.findViewById(R.id.emptyPin21);
        board[5][1] = (ImageButton) activity.findViewById(R.id.emptyPin22);
        board[5][2] = (ImageButton) activity.findViewById(R.id.emptyPin23);
        board[5][3] = (ImageButton) activity.findViewById(R.id.emptyPin24);
        board[6][0] = (ImageButton) activity.findViewById(R.id.emptyPin25);
        board[6][1] = (ImageButton) activity.findViewById(R.id.emptyPin26);
        board[6][2] = (ImageButton) activity.findViewById(R.id.emptyPin27);
        board[6][3] = (ImageButton) activity.findViewById(R.id.emptyPin28);
        board[7][0] = (ImageButton) activity.findViewById(R.id.emptyPin29);
        board[7][1] = (ImageButton) activity.findViewById(R.id.emptyPin30);
        board[7][2] = (ImageButton) activity.findViewById(R.id.emptyPin31);
        board[7][3] = (ImageButton) activity.findViewById(R.id.emptyPin32);
        board[8][0] = (ImageButton) activity.findViewById(R.id.emptyPin33);
        board[8][1] = (ImageButton) activity.findViewById(R.id.emptyPin34);
        board[8][2] = (ImageButton) activity.findViewById(R.id.emptyPin35);
        board[8][3] = (ImageButton) activity.findViewById(R.id.emptyPin36);
        board[9][0] = (ImageButton) activity.findViewById(R.id.emptyPin37);
        board[9][1] = (ImageButton) activity.findViewById(R.id.emptyPin38);
        board[9][2] = (ImageButton) activity.findViewById(R.id.emptyPin39);
        board[9][3] = (ImageButton) activity.findViewById(R.id.emptyPin40);

        checkButton = (Button) activity.findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);

        //sets the Listeners for the board
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 4;j++){

                board[i][j].setOnClickListener(this);
            }
        }

        //initalizing the feedback pegs
        feedbackSpot = new ImageView[10][4];
        feedbackSpot[0][0] = (ImageView) activity.findViewById(R.id.check_peg1);
        feedbackSpot[0][1] = (ImageView) activity.findViewById(R.id.check_peg2);
        feedbackSpot[0][2] = (ImageView) activity.findViewById(R.id.check_peg3);
        feedbackSpot[0][3] = (ImageView) activity.findViewById(R.id.check_peg4);
        feedbackSpot[1][0] = (ImageView) activity.findViewById(R.id.check_peg5);
        feedbackSpot[1][1] = (ImageView) activity.findViewById(R.id.check_peg6);
        feedbackSpot[1][2] = (ImageView) activity.findViewById(R.id.check_peg7);
        feedbackSpot[1][3] = (ImageView) activity.findViewById(R.id.check_peg8);
        feedbackSpot[2][0] = (ImageView) activity.findViewById(R.id.check_peg9);
        feedbackSpot[2][1] = (ImageView) activity.findViewById(R.id.check_peg10);
        feedbackSpot[2][2] = (ImageView) activity.findViewById(R.id.check_peg11);
        feedbackSpot[2][3] = (ImageView) activity.findViewById(R.id.check_peg12);
        feedbackSpot[3][0] = (ImageView) activity.findViewById(R.id.check_peg13);
        feedbackSpot[3][1] = (ImageView) activity.findViewById(R.id.check_peg14);
        feedbackSpot[3][2] = (ImageView) activity.findViewById(R.id.check_peg15);
        feedbackSpot[3][3] = (ImageView) activity.findViewById(R.id.check_peg16);
        feedbackSpot[4][0] = (ImageView) activity.findViewById(R.id.check_peg17);
        feedbackSpot[4][1] = (ImageView) activity.findViewById(R.id.check_peg18);
        feedbackSpot[4][2] = (ImageView) activity.findViewById(R.id.check_peg19);
        feedbackSpot[4][3] = (ImageView) activity.findViewById(R.id.check_peg20);
        feedbackSpot[5][0] = (ImageView) activity.findViewById(R.id.check_peg21);
        feedbackSpot[5][1] = (ImageView) activity.findViewById(R.id.check_peg22);
        feedbackSpot[5][2] = (ImageView) activity.findViewById(R.id.check_peg23);
        feedbackSpot[5][3] = (ImageView) activity.findViewById(R.id.check_peg24);
        feedbackSpot[6][0] = (ImageView) activity.findViewById(R.id.check_peg25);
        feedbackSpot[6][1] = (ImageView) activity.findViewById(R.id.check_peg26);
        feedbackSpot[6][2] = (ImageView) activity.findViewById(R.id.check_peg27);
        feedbackSpot[6][3] = (ImageView) activity.findViewById(R.id.check_peg28);
        feedbackSpot[7][0] = (ImageView) activity.findViewById(R.id.check_peg29);
        feedbackSpot[7][1] = (ImageView) activity.findViewById(R.id.check_peg30);
        feedbackSpot[7][2] = (ImageView) activity.findViewById(R.id.check_peg31);
        feedbackSpot[7][3] = (ImageView) activity.findViewById(R.id.check_peg32);
        feedbackSpot[8][0] = (ImageView) activity.findViewById(R.id.check_peg33);
        feedbackSpot[8][1] = (ImageView) activity.findViewById(R.id.check_peg34);
        feedbackSpot[8][2] = (ImageView) activity.findViewById(R.id.check_peg35);
        feedbackSpot[8][3] = (ImageView) activity.findViewById(R.id.check_peg36);
        feedbackSpot[9][0] = (ImageView) activity.findViewById(R.id.check_peg37);
        feedbackSpot[9][1] = (ImageView) activity.findViewById(R.id.check_peg38);
        feedbackSpot[9][2] = (ImageView) activity.findViewById(R.id.check_peg39);
        feedbackSpot[9][3] = (ImageView) activity.findViewById(R.id.check_peg40);

    }

    public void onClick(View v) {

        //Quit button
        if(v == quitButton){
            System.exit(1);
        }
            //checks for a specific colored peg
        if(v instanceof ImageButton) {
            if (v == blackPin) {
                selectedPin.setImageResource(R.mipmap.black_circle);
                color = 1;
            } else if (v == brownPin) {
                selectedPin.setImageResource(R.mipmap.brown_circle);
                color = 7;
            } else if (v == greenPin) {
                selectedPin.setImageResource(R.mipmap.green_circle);
                color = 3;
            } else if (v == redPin) {
                selectedPin.setImageResource(R.mipmap.reball);
                color = 8;
            } else if (v == purplePin) {
                selectedPin.setImageResource(R.mipmap.purple_circle);
                color = 5;
            } else if (v == yellowPin) {
                selectedPin.setImageResource(R.mipmap.yellow_circle);
                color = 2;
            } else if (v == orangePin) {
                selectedPin.setImageResource(R.mipmap.orange_circle);
                color = 6;
            } else if (v == bluePin) {
                selectedPin.setImageResource(R.mipmap.blue_circle);
                color = 4;
            } else {//a board pin is selected
                if (v == board[0][0]) {
                    if(gameState.currentRow == 0) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[0][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if (v == board[0][1]) {
                    if(gameState.currentRow == 0) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[0][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if (v == board[0][2])  {
                    if(gameState.currentRow == 0) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[0][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[0][3])) {
                    if(gameState.currentRow == 0) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[0][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if (v == board[1][0]) {
                    if(gameState.currentRow == 1) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[1][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[1][1])) {
                    if(gameState.currentRow == 1) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[1][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[1][2])) {
                    if(gameState.currentRow == 1) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[1][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[1][3])) {

                    if(gameState.currentRow == 1) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[1][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[2][0])) {

                    if(gameState.currentRow == 2) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[2][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[2][1])) {

                    if(gameState.currentRow == 2) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[2][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[2][2])) {

                    if(gameState.currentRow == 2) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[2][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[2][3])) {

                    if(gameState.currentRow == 2) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[2][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[3][0])) {

                    if(gameState.currentRow == 3) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[3][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[3][1])) {

                    if(gameState.currentRow == 3) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[3][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[3][2])) {

                    if(gameState.currentRow == 3) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[3][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[3][3])) {

                    if(gameState.currentRow == 3) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[3][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[4][0])) {

                    if(gameState.currentRow == 4) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[4][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[4][1])) {

                    if(gameState.currentRow == 4) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[4][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[4][2])) {

                    if(gameState.currentRow == 4) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[4][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[4][3])) {

                    if(gameState.currentRow == 4) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[4][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[5][0])) {

                    if(gameState.currentRow == 5) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[5][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[5][1])) {

                    if(gameState.currentRow == 5) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[5][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[5][2])) {

                    if(gameState.currentRow == 5) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[5][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[5][3])) {

                    if(gameState.currentRow == 5) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[5][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[6][0])) {

                    if(gameState.currentRow == 6) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[6][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[6][1])) {

                    if(gameState.currentRow == 6) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[6][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[6][2])) {

                    if(gameState.currentRow == 6) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[6][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[6][3])) {

                    if(gameState.currentRow == 6) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[6][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[7][0])) {

                    if(gameState.currentRow == 7) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[7][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[7][1])) {

                    if(gameState.currentRow == 7) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[7][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[7][2])) {

                    if(gameState.currentRow == 7) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[7][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[7][3])) {

                    if(gameState.currentRow == 7) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[7][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[8][0])) {

                    if(gameState.currentRow == 8) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                    }
                    else{
                        color=gameState.board[8][0].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[8][1])) {

                    if(gameState.currentRow == 8) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                    }
                    else{
                        color=gameState.board[8][1].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[8][2])) {

                    if(gameState.currentRow == 8) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                    }
                    else{
                        color=gameState.board[8][2].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[8][3])) {

                    if(gameState.currentRow == 8) {
                        game.sendAction(new MMPlacePegAction(realPlayer, color, 3));
                    }
                    else{
                        color=gameState.board[8][3].getColor();
                        this.changeColor(selectedPin,color);
                    }
                }
                if ((v == board[9][0]) && (gameState.currentRow == 9)) {

                    game.sendAction(new MMPlacePegAction(realPlayer, color, 0));
                }
                if ((v == board[9][1]) && (gameState.currentRow == 9)) {

                    game.sendAction(new MMPlacePegAction(realPlayer, color, 1));
                }
                if ((v == board[9][2]) && (gameState.currentRow == 9)) {

                    game.sendAction(new MMPlacePegAction(realPlayer, color, 2));
                }
                if ((v == board[9][3]) && (gameState.currentRow == 9)) {

                    game.sendAction(new MMPlacePegAction(realPlayer, color, 3));

                }
            }
        }
        //sets up the check button passes the turn and allow other player to check combination.
        if(v == checkButton){
            for (int i=0;i<4;i++)
            {
                if(gameState.currentRow>=10)
                {
                    return;
                }
                else if(gameState.board[gameState.getCurrentRow()][i].getColor()==-1)
                {
                    return;
                }
            }
            game.sendAction(new MMPassTurnAction(realPlayer));
            // gameState.checkCode();

        }

    }



    public void onNothingSelected(AdapterView<?> parent) {
        //not used
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    //not used
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    //not used
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    //not used
    }

    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    /**
     * overloaded method that takes in an image button and changes its
     * image to a sepcified color
     * @param button
     * @param aColor
     */
    private void changeColor(ImageButton button,int aColor){

        if(aColor == -1){
            button.setImageResource(R.mipmap.empty_circle);
        }
        if(aColor == 0){
            button.setImageResource(R.mipmap.white_pin);
        }
        if(aColor == 1){
            button.setImageResource(R.mipmap.black_circle);
        }
        if(aColor == 2){
            button.setImageResource(R.mipmap.yellow_circle);
        }
        if(aColor == 3){
            button.setImageResource(R.mipmap.green_circle);
        }
        if(aColor == 4){
            button.setImageResource(R.mipmap.blue_circle);
        }
        if(aColor == 5){
            button.setImageResource(R.mipmap.purple_circle);
        }
        if(aColor == 6){
            button.setImageResource(R.mipmap.orange_circle);
        }
        if(aColor == 7){
            button.setImageResource(R.mipmap.brown_circle);
        }
        if(aColor == 8){
            button.setImageResource(R.mipmap.reball);
        }
    }

    /**
     * Same as the above but takes in an imageView
     * @param button
     * @param aColor
     */
    private void changeColor(ImageView button,int aColor){

        if(aColor == -1){
            button.setImageResource(R.mipmap.empty_circle);
        }
        if(aColor == 0){
            button.setImageResource(R.mipmap.white_pin);
        }
        if(aColor == 1){
            button.setImageResource(R.mipmap.black_circle);
        }
        if(aColor == 2){
            button.setImageResource(R.mipmap.yellow_circle);
        }
        if(aColor == 3){
            button.setImageResource(R.mipmap.green_circle);
        }
        if(aColor == 4){
            button.setImageResource(R.mipmap.blue_circle);
        }
        if(aColor == 5){
            button.setImageResource(R.mipmap.purple_circle);
        }
        if(aColor == 6){
            button.setImageResource(R.mipmap.orange_circle);
        }
        if(aColor == 7){
            button.setImageResource(R.mipmap.brown_circle);
        }
        if(aColor == 8){
            button.setImageResource(R.mipmap.reball);
        }
    }


    public void feedBackColor(ImageView v, int aColor)
    {
        if(aColor == -1){
            v.setImageResource(R.mipmap.empty_circle);
        }
        if(aColor == 0){
            v.setImageResource(R.mipmap.question_mark_red);
        }
        if(aColor == 1){
            v.setImageResource(R.mipmap.black_circle);
        }
        if(aColor == 2){
            v.setImageResource(R.mipmap.yellow_circle);
        }
        if(aColor == 3){
            v.setImageResource(R.mipmap.green_circle);
        }
        if(aColor == 4){
            v.setImageResource(R.mipmap.blue_circle);
        }
        if(aColor == 5){
            v.setImageResource(R.mipmap.purple_circle);
        }
        if(aColor == 6){
            v.setImageResource(R.mipmap.orange_circle);
        }
        if(aColor == 7){
            v.setImageResource(R.mipmap.brown_circle);
        }
        if(aColor == 8){
            v.setImageResource(R.mipmap.red_pin);
        }
    }

    /**
     * helper method to help with setting the background color of the board limear layouts
     */
    private void rowSelect(){

        //Change the current row's background to indicate that it is the current row
        if(gameState.currentRow==0)
        {
            rows[0].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[0].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==1)
        {
            rows[1].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[1].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==2)
        {
            rows[2].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[2].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==3)
        {
            rows[3].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[3].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==4)
        {
            rows[4].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[4].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==5)
        {
            rows[5].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[5].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==6)
        {
            rows[6].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[6].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==7)
        {
            rows[7].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[7].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==8)
        {
            rows[8].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[8].setBackgroundColor(backgroundColor);
        }

        if(gameState.currentRow==9)
        {
            rows[9].setBackgroundColor(0xFFFF00FF);
        }
        else{
            rows[9].setBackgroundColor(backgroundColor);
        }
    }

    /**
     External Citation
     Date: 23 April 2016
     Problem: Could not find spinner listener
     Resource:
     Steven Veghdal's GitHub Lab
     Solution: I used the example code from this lab.
     */
    /**
     * MySpinnerListener class
     * <p/>
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements AdapterView.OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *android.widget.AdapterView, android.view.View, int, long)
         */
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            //change the background color based on the spinner
            if (position == 0) {
                backgroundColor = Color.parseColor("#FF104E8B");
            }
            if (position == 1) {
                backgroundColor = Color.parseColor("#FFA90000");
            }
            if (position == 2) {
                backgroundColor = Color.parseColor("#FF009500");
            }

            //set back most color
            background.setBackgroundColor(backgroundColor);

            //set the background color of the board's pin slots
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    feedbackSpot[i][j].setBackgroundColor(backgroundColor);
                    board[i][j].setBackgroundColor(backgroundColor);
                }
            }

            //change the background color of the pins
            blackPin.setBackgroundColor(backgroundColor);
            yellowPin.setBackgroundColor(backgroundColor);
            greenPin.setBackgroundColor(backgroundColor);
            bluePin.setBackgroundColor(backgroundColor);
            purplePin.setBackgroundColor(backgroundColor);
            orangePin.setBackgroundColor(backgroundColor);
            brownPin.setBackgroundColor(backgroundColor);
            redPin.setBackgroundColor(backgroundColor);

            //change the background color of the board's linear layouts
            rowSelect();
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}


