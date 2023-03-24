package Model.Players;

import Model.Pawns.MoveablePiece;
import Model.Pawns.Piece;

import java.awt.*;

public class Player {

    private String name;
    private int capturesNum;
    private int WinRate;
    private int numberOfAttacks;
    private int numberOfSaves;
    private Piece[] personalCaptures = new Piece[30];

    /**
     * this constructor will initialize some variables of the player.
     * @param name
     */
    public Player(String name){
        this.name = name;
        capturesNum = 0;
        numberOfAttacks = 0;
        WinRate = 0;

        for (int i = 0; i < 30; i++) {
            personalCaptures[i] = new MoveablePiece(0,-1, Color.white,"");
        }
    }

    /**
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }


    /**
     * return the captures of the player.
     * @return
     */
    public int getCaptures() {
        return capturesNum;
    }

    /**
     * set the captures of the player ++ if he captures one
     */
    public void setcaptures() {
        capturesNum++;
    }

    /**
     * @return the win rate of the player.
     */
    public int getWinRate() {
        return WinRate;
    }

    /**
     * set the win rate of the player.
     * @param winRate
     */
    public void setWinRate(int winRate) {
        this.WinRate = winRate;
    }

    /**
     * @return the number each player saves.
     */
    public int getNumberOfSaves() {
        return numberOfSaves;
    }

    /**
     * set the number of saves for each player.
     * @param numberOfSaves
     */
    public void setNumberOfSaves(int numberOfSaves) {
        this.numberOfSaves = numberOfSaves;
    }

    /**
     * @return the array of total captures.
     */
    public Piece getPersonalCaptures(int i) {
        return personalCaptures[i];
    }

    /**
     * this method will add at the array of captures another capture
     * @param personalCaptures
     */
    public void setPersonalCaptures(Piece personalCaptures) {
        this.personalCaptures[capturesNum] = personalCaptures;
        this.personalCaptures[capturesNum].setSImage(personalCaptures.getSImage());
        setcaptures();
    }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public void setNumberOfAttacks() {
        this.numberOfAttacks ++;
    }
}
