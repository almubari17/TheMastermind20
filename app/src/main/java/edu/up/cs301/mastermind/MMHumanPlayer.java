package edu.up.cs301.mastermind;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by almubari17 on 3/30/2016.
 */
public class MMHumanPlayer extends GameHumanPlayer implements  View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    private GameMainActivity myActivity; // the current activity

    protected MMHumanPlayer player;
    protected Button contButton;
    /**
     * constructor
     *
     * @param name
     */
    public MMHumanPlayer(String name) {
        super(name);

    }

    @Override
    public View getTopView() {

        return player.getTopView();

    }

    @Override
    public void receiveInfo(GameInfo info) {
        player.receiveInfo(info);

    }

    public void setAsGui(GameMainActivity activity) {
        myActivity=activity;

        if(player ==null)
        {
            activity.setContentView(R.layout.loading);
            contButton = (Button) activity.findViewById(R.id.cont_button);
            contButton.setOnClickListener(this);

            return;
        }
        player.setAsGui(activity);

    }
    @Override
    protected void initAfterReady(){

        if(this.playerNum==0)
        {
            player= new MMCoderHumanPlayer(name,this);


        }
        else if(this.playerNum==1){
            player = new MMDecoderHumanPlayer(name,this);
        }
        player.playerNum= this.playerNum;
        player.allPlayerNames= this.allPlayerNames;
        player.game= this.game;
        player.setAsGui(myActivity);

    }

    public void onClick(View v) {
        if (v== contButton)
        {

            if(this.playerNum==0)
            {
                player= new MMCoderHumanPlayer(name,this);


            }
            else if(this.playerNum==1){
                player = new MMDecoderHumanPlayer(name,this);
            }
            player.playerNum= this.playerNum;
            player.allPlayerNames= this.allPlayerNames;
            player.game= this.game;
            player.setAsGui(myActivity);
            setAsGui(myActivity);
        }

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
