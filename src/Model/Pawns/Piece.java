package Model.Pawns;


import javax.swing.*;
import java.awt.*;


public abstract class Piece extends JButton {

    private int power;
    private boolean alive;
    private Color color;
    private Icon image;
    private boolean isChoosenByRandom = false;
    private boolean hasSaved;
    private boolean canMoveThere;
    private String SImage;


    /**
     * This constructor uses a method to set the place of a piece, another method
     * to set the power and makes some initializations.
     * @param place
     */
    public Piece(int place, int power, Color color,String image) {
        setPower(power);
        setColor(color);
        this.SImage = image;
        this.image = new ImageIcon(SImage);
        this.alive = true;
        this.hasSaved = false;
        canMoveThere = false;
    }

    /**
     * @return a string that cary the path of the piece's image
     */
    public String getSImage(){
        return  this.SImage;
    }

    public void setSImage(String str){
        this.SImage = str;
    }
    /**
     * this method will set the alive variable as the given parameter.
     * @param alive
     */
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    /**
     * @return true if the pawn is alive or false if it is beated.
     * it can be used also for traps and flags.
     */
    public boolean isAlive(){
        if(this.alive)
            return true;
        else
            return false;
    }

    /**
     * This method sets the place of the piece with the given parameter
     * @param power
     */
    public void setPower(int power){
        this.power = power;
    }

    /**
     * @return the private int power
     */
    public int getPower(){
        return this.power;
    }

    /**
     * @return if the piece is red or blue.
     */
    public Color getColor() {
        return color;
    }

    /**
     * set if the piece is red or blue
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * @return the image of the button
     */
    public Icon getImage() {
        return image;
    }

    /**
     * sets the image of the button
     * @param image
     */
    public void setImage(Icon image){
        this.image = image;
    }

    /**
     * this method will set the variable hasSaved to true if the piece has already saved once.
     * @param save
     */
    public void setHasSaved(boolean save){
        this.hasSaved = save;
    }

    /**
     * @return if the piece has already saved once.
     */
    public boolean getHasSaved(){
        return this.hasSaved;
    }



    public boolean getIsChoosenByRandom() {
        return isChoosenByRandom;
    }

    public void setChoosenByRandom(boolean choosenByRandom) {
        this.isChoosenByRandom = choosenByRandom;
    }

    public boolean getCanMoveThere() {
        return canMoveThere;
    }

    public void setCanMoveThere(boolean canMoveThere) {
        this.canMoveThere = canMoveThere;
    }
}
