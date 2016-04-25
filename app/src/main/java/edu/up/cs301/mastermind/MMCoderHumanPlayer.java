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
import android.widget.Spinner;
import android.widget.Toast;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;



/**
 * Author: Ibrahim Almubarak ,Jamie Fisher, Jacob Bryant,
 * Version: 1.0.0
 * Description: The gui for the coder.
 *
 *
 *
 */
public class MMCoderHumanPlayer extends MMHumanPlayer implements View.OnClickListener {

    ImageView[][] feedbackSpot; //The response the master code can place pegs there
    ImageButton[] masterPegs; //The pegs chosen at the start of the game
    ImageView[] master_copy; // The copy pegs from the master code will be displayed on the board
    ImageView[][] board; // the actual board
    ImageButton[] currentResponse; // the responses
     //the peg choices
    protected ImageButton blackPin;
    protected ImageButton brownPin;
    protected ImageButton purplePin;
    protected ImageButton greenPin;
    protected ImageButton bluePin;
    protected ImageButton yellowPin;
    protected ImageButton redPin;
    protected ImageButton orangePin;
    protected LinearLayout background;
    protected LinearLayout gameBackground;
    protected MMGameState aGame;
    protected int pegColor=-1;
    protected MMHumanPlayer realPlayer;
    protected Button setCode;
    protected Button checkButton;
    protected int [] reComb; //response combination the combination that user chosesn as a response
    protected ImageButton questionPeg;
    protected ImageButton exclamationPeg;
    protected GameMainActivity gameActivity;
    protected Button quitButton;
    protected MediaPlayer player;
    protected Spinner colorSpinner;
    protected int backgroundColor;
    protected Button clearButton;


    /**
     * constructor inherited
     *
     * @param name
     */
    public MMCoderHumanPlayer(String name,MMHumanPlayer p) {
        super(name);
        realPlayer=p;
    }

    @Override
    public View getTopView() {
        return null;
    }

    /**
     * method used to update the gui everytime an action has been passed
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof MMGameState)
        {
            aGame = (MMGameState)info;
        }

        for(int i = 0;i < 4;i++) {
                this.setPegs(masterPegs[i],aGame.masterCode[i].getColor());
                this.setPegs(master_copy[i],aGame.masterCode[i].getColor());
                this.feedBackColor(currentResponse[i],reComb[i]);
                        
        }
        for(int i = 0;i < 10;i++) {
            for (int j = 0; j < 4; j++) {
                this.setPegs(board[i][j], aGame.board[i][j].getColor());
                this.feedBackColor(feedbackSpot[i][j], this.aGame.checkCode[i][j].getColor());

            }
        }

    }

    public void setAsGui(GameMainActivity activity) {
      //  activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.setContentView(R.layout.coder_board);
        gameActivity=activity;
        blackPin = (ImageButton) activity.findViewById(R.id.black_button);
        brownPin = (ImageButton) activity.findViewById(R.id.brown_button);
        orangePin = (ImageButton) activity.findViewById(R.id.orange_button);
        redPin = (ImageButton) activity.findViewById(R.id.red_button);
        greenPin = (ImageButton) activity.findViewById(R.id.green_button);
        yellowPin = (ImageButton) activity.findViewById(R.id.yellow_button);
        bluePin = (ImageButton) activity.findViewById(R.id.blue_button);
        purplePin = (ImageButton) activity.findViewById(R.id.purple_button);
        background = (LinearLayout) activity.findViewById(R.id.coder_start);
        gameBackground = (LinearLayout) activity.findViewById(R.id.background);
        setCode = (Button) activity.findViewById(R.id.master_button);
        checkButton = (Button) activity.findViewById((R.id.check_button));
        exclamationPeg= (ImageButton) activity.findViewById(R.id.exclamation_peg);
        questionPeg= (ImageButton)activity.findViewById(R.id.question_mark_peg);
        quitButton = (Button)activity.findViewById(R.id.quitButton);
        colorSpinner = (Spinner) activity.findViewById(R.id.colorSpinner);
        clearButton = (Button) activity.findViewById(R.id.clear_button);

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

        player= MediaPlayer.create(activity,R.raw.music);
        player.setLooping(true);
        player.start();
        reComb= new int[4];

        for(int i=0;i<4;i++)
        {
            reComb[i]=-1;
        }

        // original pegs after set won't change.
        masterPegs= new ImageButton[4];
        masterPegs[0]= (ImageButton) activity.findViewById(R.id.empty_master1);
        masterPegs[1]= (ImageButton) activity.findViewById(R.id.empty_master2);
        masterPegs[2]= (ImageButton) activity.findViewById(R.id.empty_master3);
        masterPegs[3]= (ImageButton) activity.findViewById(R.id.empty_master4);

        //the copy pegs
        master_copy = new ImageView[4];
        master_copy[0]= (ImageView) activity.findViewById(R.id.master_code01);
        master_copy[1]= (ImageView) activity.findViewById(R.id.master_code02);
        master_copy[2]= (ImageView) activity.findViewById(R.id.master_code03);
        master_copy[3]= (ImageView) activity.findViewById(R.id.master_code04);

        //User input response
        currentResponse= new ImageButton[4];
        currentResponse[0]= (ImageButton) activity.findViewById(R.id.guess_peg1);
        currentResponse[1]= (ImageButton) activity.findViewById(R.id.guess_peg2);
        currentResponse[2]= (ImageButton) activity.findViewById(R.id.guess_peg3);
        currentResponse[3]= (ImageButton) activity.findViewById(R.id.guess_peg4);
        



        //Listeners
        blackPin.setOnClickListener(this);
        brownPin.setOnClickListener(this);
        redPin.setOnClickListener(this);
        bluePin.setOnClickListener(this);
        greenPin.setOnClickListener(this);
        yellowPin.setOnClickListener(this);
        purplePin.setOnClickListener(this);
        orangePin.setOnClickListener(this);
        masterPegs[0].setOnClickListener(this);
        masterPegs[1].setOnClickListener(this);
        masterPegs[2].setOnClickListener(this);
        masterPegs[3].setOnClickListener(this);
        clearButton.setOnClickListener(this);

        //response listener
        currentResponse[0].setOnClickListener(this);
        currentResponse[1].setOnClickListener(this);
        currentResponse[2].setOnClickListener(this);
        currentResponse[3].setOnClickListener(this);

        setCode.setOnClickListener(this);
        background.setVisibility(background.VISIBLE);
        checkButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);

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


        //board setting
        board = new ImageView[10][4];
        board[0][0] = (ImageView) activity.findViewById(R.id.emptyPin1);
        board[0][1] = (ImageView) activity.findViewById(R.id.emptyPin2);
        board[0][2] = (ImageView) activity.findViewById(R.id.emptyPin3);
        board[0][3] = (ImageView) activity.findViewById(R.id.emptyPin4);
        board[1][0] = (ImageView) activity.findViewById(R.id.emptyPin5);
        board[1][1] = (ImageView) activity.findViewById(R.id.emptyPin6);
        board[1][2] = (ImageView) activity.findViewById(R.id.emptyPin7);
        board[1][3] = (ImageView) activity.findViewById(R.id.emptyPin8);
        board[2][0] = (ImageView) activity.findViewById(R.id.emptyPin9);
        board[2][1] = (ImageView) activity.findViewById(R.id.emptyPin10);
        board[2][2] = (ImageView) activity.findViewById(R.id.emptyPin11);
        board[2][3] = (ImageView) activity.findViewById(R.id.emptyPin12);
        board[3][0] = (ImageView) activity.findViewById(R.id.emptyPin13);
        board[3][1] = (ImageView) activity.findViewById(R.id.emptyPin14);
        board[3][2] = (ImageView) activity.findViewById(R.id.emptyPin15);
        board[3][3] = (ImageView) activity.findViewById(R.id.emptyPin16);
        board[4][0] = (ImageView) activity.findViewById(R.id.emptyPin17);
        board[4][1] = (ImageView) activity.findViewById(R.id.emptyPin18);
        board[4][2] = (ImageView) activity.findViewById(R.id.emptyPin19);
        board[4][3] = (ImageView) activity.findViewById(R.id.emptyPin20);
        board[5][0] = (ImageView) activity.findViewById(R.id.emptyPin21);
        board[5][1] = (ImageView) activity.findViewById(R.id.emptyPin22);
        board[5][2] = (ImageView) activity.findViewById(R.id.emptyPin23);
        board[5][3] = (ImageView) activity.findViewById(R.id.emptyPin24);
        board[6][0] = (ImageView) activity.findViewById(R.id.emptyPin25);
        board[6][1] = (ImageView) activity.findViewById(R.id.emptyPin26);
        board[6][2] = (ImageView) activity.findViewById(R.id.emptyPin27);
        board[6][3] = (ImageView) activity.findViewById(R.id.emptyPin28);
        board[7][0] = (ImageView) activity.findViewById(R.id.emptyPin29);
        board[7][1] = (ImageView) activity.findViewById(R.id.emptyPin30);
        board[7][2] = (ImageView) activity.findViewById(R.id.emptyPin31);
        board[7][3] = (ImageView) activity.findViewById(R.id.emptyPin32);
        board[8][0] = (ImageView) activity.findViewById(R.id.emptyPin33);
        board[8][1] = (ImageView) activity.findViewById(R.id.emptyPin34);
        board[8][2] = (ImageView) activity.findViewById(R.id.emptyPin35);
        board[8][3] = (ImageView) activity.findViewById(R.id.emptyPin36);
        board[9][0] = (ImageView) activity.findViewById(R.id.emptyPin37);
        board[9][1] = (ImageView) activity.findViewById(R.id.emptyPin38);
        board[9][2] = (ImageView) activity.findViewById(R.id.emptyPin39);
        board[9][3] = (ImageView) activity.findViewById(R.id.emptyPin40);

        //Small pegs

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

        exclamationPeg.setOnClickListener(this);
        questionPeg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == blackPin) {

            pegColor=1;
        } else if (v == brownPin) {

            pegColor=7;
        } else if (v == greenPin) {

            pegColor=3;
        } else if (v == redPin) {

            pegColor=8;
        } else if (v == purplePin) {

            pegColor=5;
        } else if (v == yellowPin) {

            pegColor=2;
        } else if (v == orangePin) {

            pegColor=6;
        } else if (v == bluePin) {

            pegColor=4;
        }
        else if(v == masterPegs[0])
        {
            game.sendAction(new MMPlaceMasterPegAction(realPlayer,pegColor,0));
        }
        else if(v == masterPegs[1])
        {
            game.sendAction(new MMPlaceMasterPegAction(realPlayer, pegColor, 1));
        }
        else if(v == masterPegs[2])
        {
            game.sendAction(new MMPlaceMasterPegAction(realPlayer, pegColor, 2));
        }
        else if(v == masterPegs[3])
        {
            game.sendAction(new MMPlaceMasterPegAction(realPlayer, pegColor, 3));
        }
        //Used to get rid of the first layout in favor of the board layout
        else if (v==setCode ){
            boolean duplicates=false;
            for (int j=0;j<aGame.masterCode.length;j++) {

                if(aGame.masterCode[j].getColor()==-1)
                {
                    duplicates=true;
                }

            }

            if(!(duplicates)) {
                background.setVisibility(background.GONE);
                game.sendAction(new MMPassTurnAction(realPlayer));
                pegColor=-1;
            }
            else{
                Toast.makeText(gameActivity,"Invalid Code: No duplicates and no empty pins", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v==checkButton)
        {
            //resets the response
            for(int i=0;i<4;i++)
            {
                this.feedBackColor(currentResponse[i],-1);
            }
            //if code is correct passes an action else display an error
            if(aGame.checkCode(reComb)) {
                game.sendAction(new MMCheckAction(realPlayer));
            }
            else{
                Toast.makeText(gameActivity,"Invalid combination", Toast.LENGTH_SHORT).show();

            }
            //Resets the choice
            for(int i=0;i<4;i++)
            {
                reComb[i]=-1;
            }
        }
        else if(v == quitButton){
            System.exit(1);
        }
        else if(v==currentResponse[0])
        {
            this.feedBackColor(currentResponse[0],pegColor);
            reComb[0]=pegColor;
        }
        else if(v==currentResponse[1])
        {
            this.feedBackColor(currentResponse[1],pegColor);
            reComb[1]=pegColor;
        }
        else if(v==currentResponse[2])
        {
            this.feedBackColor(currentResponse[2],pegColor);
            reComb[2]=pegColor;
        }
        else if(v==currentResponse[3])
        {
            this.feedBackColor(currentResponse[3],pegColor);
            reComb[3]=pegColor;
        }
        else if( v== questionPeg)
        {
            pegColor=0;
        }
        else if( v==exclamationPeg){
            pegColor=8;
        } else if(v == clearButton){
            for(int i=0;i<reComb.length;i++){
                reComb[i] = -1;
                this.feedBackColor(currentResponse[i],-1);
            }
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    /**
     * Overloaded method used to change the resource of imagebuttons
     * @param aView
     * @param aColor
     */
    public void setPegs(ImageButton aView, int aColor)
    {

        if(aColor==-1){aView.setImageResource(R.mipmap.empty_circle);}
        if(aColor==1){aView.setImageResource(R.mipmap.black_circle);}
        if(aColor==2)aView.setImageResource(R.mipmap.yellow_circle);
        if(aColor==3)aView.setImageResource(R.mipmap.green_circle);
        if(aColor==4)aView.setImageResource(R.mipmap.blue_circle);
        if(aColor==5)aView.setImageResource(R.mipmap.purple_circle);
        if(aColor==6)aView.setImageResource(R.mipmap.orange_circle);
        if(aColor==7)aView.setImageResource(R.mipmap.brown_circle);
        if(aColor==8)aView.setImageResource(R.mipmap.reball);
    }

    /**
     * Overloaded method used to change the resource of ImageViews
     * @param aView
     * @param aColor
     */
    public void setPegs(ImageView aView, int aColor)
    {

        if(aColor==-1){aView.setImageResource(R.mipmap.empty_circle);}
        if(aColor==1){aView.setImageResource(R.mipmap.black_circle);}
        if(aColor==2)aView.setImageResource(R.mipmap.yellow_circle);
        if(aColor==3)aView.setImageResource(R.mipmap.green_circle);
        if(aColor==4)aView.setImageResource(R.mipmap.blue_circle);
        if(aColor==5)aView.setImageResource(R.mipmap.purple_circle);
        if(aColor==6)aView.setImageResource(R.mipmap.orange_circle);
        if(aColor==7)aView.setImageResource(R.mipmap.brown_circle);
        if(aColor==8)aView.setImageResource(R.mipmap.reball);
    }

    /**
     * different authors for the same function this one is specifically used for the check color
     * @param v
     * @param aColor
     */
    public void feedBackColor(ImageButton v, int aColor) {
        if (aColor == -1) {
            v.setImageResource(R.mipmap.empty_circle);
        }
        if (aColor == 0) {
            v.setImageResource(R.mipmap.question_mark_red);
        }
        if (aColor == 1) {
            v.setImageResource(R.mipmap.black_circle);
        }
        if (aColor == 2) {
            v.setImageResource(R.mipmap.yellow_circle);
        }
        if (aColor == 3) {
            v.setImageResource(R.mipmap.green_circle);
        }
        if (aColor == 4) {
            v.setImageResource(R.mipmap.blue_circle);
        }
        if (aColor == 5) {
            v.setImageResource(R.mipmap.purple_circle);
        }
        if (aColor == 6) {
            v.setImageResource(R.mipmap.orange_circle);
        }
        if (aColor == 7) {
            v.setImageResource(R.mipmap.brown_circle);
        }
        if (aColor == 8) {
            v.setImageResource(R.mipmap.red_pin);
        }
    }

    public void feedBackColor(ImageView v, int aColor) {
        if (aColor == -1) {
            v.setImageResource(R.mipmap.empty_circle);
        }
        if (aColor == 0) {
            v.setImageResource(R.mipmap.question_mark_red);
        }
        if (aColor == 1) {
            v.setImageResource(R.mipmap.black_circle);
        }
        if (aColor == 2) {
            v.setImageResource(R.mipmap.yellow_circle);
        }
        if (aColor == 3) {
            v.setImageResource(R.mipmap.green_circle);
        }
        if (aColor == 4) {
            v.setImageResource(R.mipmap.blue_circle);
        }
        if (aColor == 5) {
            v.setImageResource(R.mipmap.purple_circle);
        }
        if (aColor == 6) {
            v.setImageResource(R.mipmap.orange_circle);
        }
        if (aColor == 7) {
            v.setImageResource(R.mipmap.brown_circle);
        }
        if (aColor == 8) {
            v.setImageResource(R.mipmap.red_pin);
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
            //change the backround color based on the spinner
            if (position == 0) {
                backgroundColor = Color.parseColor("#FF104E8B");
            }
            if (position == 1) {
                backgroundColor = Color.parseColor("#FFA90000");
            }
            if (position == 2) {
                backgroundColor = Color.parseColor("#FF009500");
            }

            //set the color of the back most linear layout
            gameBackground.setBackgroundColor(backgroundColor);

            //set the background of the board's views
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 4; j++){
                    feedbackSpot[i][j].setBackgroundColor(backgroundColor);
                    board[i][j].setBackgroundColor(backgroundColor);
                }
            }
            for(int k = 0; k < 4; k++){
                master_copy[k].setBackgroundColor(backgroundColor);
                currentResponse[k].setBackgroundColor(backgroundColor);
            }
            questionPeg.setBackgroundColor(backgroundColor);
            exclamationPeg.setBackgroundColor(backgroundColor);
        }

        public void onNothingSelected(AdapterView<?> parent) {
        //Not used
        }
    }
}

