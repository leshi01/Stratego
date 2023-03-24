package Model.Dashboard;

import Model.Pawns.MoveablePiece;
import Model.Pawns.Piece;
import Utilities.RandomGenerator;

import java.awt.*;

public class Board {

    private boolean lessArmy = false;
    private boolean neverGoBack = false;
    private int totalRounds;
    private boolean player1Turn;
    private int totalCaptures;
    private Piece[] Captures = new Piece[60];

    /**
     * this constructor will do some initializations as the game starts.
     */
    public Board(){
        totalCaptures = 0;
        totalRounds = 1;
        if (RandomGenerator.getRandomVal(0,1) == 1){
            this.player1Turn = true;
        }else{
            this.player1Turn = false;
        }

        for (int i = 0; i < 60; i++) {
            Captures[i] = new MoveablePiece(0,-1, Color.white,"");
        }
    }

    /**
     * This method will do totalCaptures++ everytime someone captures somebody else.
     */
    public void setTotalCaptures(){
        totalCaptures++;
    }

    /**
     * @return the number of total captures of the game.
     */
    public int getTotalCaptures(){
        return this.totalCaptures;
    }

    /**
     * it will set the variable player1Turn to true if it is
     * player's 1 turn or false if it is player's 2 turn
     * @param turn
     */
    public void setPlayer1Turn(boolean turn){
        this.player1Turn = turn;
    }

    /**
     * @return true if is the turn of player 1 or false if it is the turn of player 2.
     */
    public boolean getPlayer1Turn(){
        return this.player1Turn;
    }

    /**
     * this method will set if the game will have less army or not.
     * @param lessArmy
     */
    public void setLessArmy(boolean lessArmy){
        this.lessArmy = lessArmy;
    }

    /**
     * @return if the game will have less army or not.
     */
    public boolean getLessArmy(){
        return this.lessArmy;
    }

    /**
     * this method will set if the army can go back or not.
     * @param neverGoBack
     */
    public void setNeverGoBack(boolean neverGoBack){
        this.neverGoBack = neverGoBack;
    }

    /**
     * @return if the pawns are allowed to go back or not.
     */
    public boolean getNeverGoBack(){
        return this.neverGoBack;
    }


    /**
     * @return the array of total captures.
     */
    public Piece getCaptures(int i) {
        return Captures[i];
    }

    /**
     * this method will add at the array of captures another capture
     * @param captures
     */
    public void setCaptures(Piece captures) {
        this.Captures[totalCaptures].setColor(captures.getColor());
        this.Captures[totalCaptures].setPower(captures.getPower());
        this.Captures[totalCaptures].setSImage(captures.getSImage());

        this.setTotalCaptures();
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds() {
        this.totalRounds++;
    }
}
