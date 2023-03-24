package Controller;

import Model.Dashboard.Board;
import Model.Pawns.ImmovablePiece;
import Model.Pawns.MoveablePiece;
import Model.Pawns.Piece;
import Model.Players.Player;
import View.View;
import Utilities.RandomGenerator;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller  implements ActionListener {

    private Piece[][] butt = new Piece[8][10];

    private final Piece[] blue = new Piece[30];
    private final Piece[] red = new Piece[30];

    View view;
    Board board;
    Player p1,p2;


    Piece selected = null;


    /**
     * this will be the constructor of the class controller,
     * here we will make some initializations that we need for our project.
     */
    public Controller() {
        view  = new View();
        board = new Board();
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");



        initializePices(blue,Color.blue);
        initializePices(red,Color.red);

        initializeButtons();
        addButtonsToPanel();

        setHiddenimages();

        view.frame.setVisible(true);

    }

    /**
     * move the button p2 to the button p1
     * @param pl1
     * @param pl2
     */
    void moveButton(Piece pl1, Piece pl2){
        pl1.setColor(pl2.getColor());
        pl1.setImage(pl2.getImage());
        pl1.setPower(pl2.getPower());
        pl1.setIcon(pl2.getImage());
        pl1.setHasSaved(pl2.getHasSaved());
        pl1.setAlive(pl2.isAlive());

        disapearPiece(pl2);

        endOfTurn();


    }

    /**
     * if a player make a movement this method will make the changes we need.
     */
    void endOfTurn(){
        board.setPlayer1Turn(!board.getPlayer1Turn());
        board.setTotalRounds();
        if (board.getPlayer1Turn()) {
            doEveryTurn(this.p1);
            putNormalimages(butt,Color.blue);

        }else {
            doEveryTurn(this.p2);
            putNormalimages(butt,Color.red);
        }
        setHiddenimages();
    }

    void disapearPiece(Piece p){
        p.setColor(Color.white);
        p.setImage(new ImageIcon(""));
        p.setPower(1);
        p.setIcon(null);



        removeBorder();
    }

    /**
     * @param pl1 will get attacked by pl2
     * @param pl2 will attack pl1
     */
    void attack(Piece pl1, Piece pl2){

        if (pl1 instanceof ImmovablePiece && pl1.getPower() == 0 ){
            if (pl2.getColor() == Color.blue){
                JOptionPane.showMessageDialog(null, "Blue Team won!", "Winner team", JOptionPane.INFORMATION_MESSAGE);
                view.frame.dispose();
            }else if(pl2.getColor() == Color.red){
                JOptionPane.showMessageDialog(null, "Red Team won!", "Winner team", JOptionPane.INFORMATION_MESSAGE);
                view.frame.dispose();
            }
        }else if (pl1.getPower() == pl2.getPower()){

            board.setCaptures(new MoveablePiece(0,pl1.getPower(),pl1.getColor(),pl1.getSImage()));
            board.setCaptures(new MoveablePiece(0,pl2.getPower(),pl2.getColor(),pl2.getSImage()));


            if(pl2.getColor() == Color.blue) {
                increasePlayersWinETC(this.p1, pl1);
                increasePlayersWinETC(this.p2, pl2);
            }else if (pl2.getColor() == Color.red){
                increasePlayersWinETC(this.p1, pl2);
                increasePlayersWinETC(this.p2, pl1);
            }


            disapearPiece(pl1);
            disapearPiece(pl2);

            removeBorder();

            endOfTurn();


        }else if (whichPieceWon(pl1,pl2)){
            board.setCaptures(new MoveablePiece(0,pl1.getPower(),pl1.getColor(),pl1.getSImage()));
            if(pl2.getColor() == Color.blue) {
                increasePlayersWinETC(this.p1, pl1);
            }else if (pl2.getColor() == Color.red){
                increasePlayersWinETC(this.p2, pl1);
            }

            moveButton(pl1,pl2);
        }else if(whichPieceWon(pl2,pl1)){
            board.setCaptures(new MoveablePiece(0,pl2.getPower(),pl2.getColor(),pl2.getSImage()));
            if(pl2.getColor() == Color.blue) {
                increasePlayersWinETC(this.p1, pl2);
            }else if (pl2.getColor() == Color.red){
                increasePlayersWinETC(this.p2, pl2);
            }

            disapearPiece(pl2);

            removeBorder();

            endOfTurn();
        }

    }

    /**
     * @param p the player we want to change some things
     * @param p1 the captured piece we want to add.
     */
    void increasePlayersWinETC(Player p, Piece p1){
        p.setNumberOfAttacks();
        p.setPersonalCaptures(new MoveablePiece(0,p1.getPower(),p1.getColor(),p1.getSImage()));
        p.setWinRate(p.getCaptures() / p.getNumberOfAttacks()   * 100);

        doEveryTurn(p);
    }

    /**
     * changes that me need to make every time the turn changes.
     * @param p
     */
    void doEveryTurn(Player p){
        view.playerTurnLabel.setText(p.getName() + " turn");
        view.winRateLabel.setText("Ποσοστό επιτ. επίθεσης: " + p.getWinRate() + "%");
        view.savesLabel.setText("Διασώσεις: " + p.getNumberOfSaves());
        view.roundsLabel.setText("Γύρος: " + board.getTotalRounds());
        view.totalCapturesLabel.setText("Συνολικές αιχμαλωτίσεις: "  + board.getTotalCaptures());


    }

    /**
     * set to the team that is not its turn the hidden image
     */
    void setHiddenimages(){
        if (board.getPlayer1Turn()) {
            doEveryTurn(p1);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 10; j++) {
                    if (butt[i][j].getColor() == Color.red){
                        butt[i][j].setIcon(new ImageIcon("..\\Stratego\\Phase_A\\images\\images\\RedPieces\\redHidden.png"));
                    }
                }

            }
        }else{
            doEveryTurn(p2);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 10; j++) {
                    if (butt[i][j].getColor() == Color.blue){
                        butt[i][j].setIcon(new ImageIcon("..\\Stratego\\Phase_A\\images\\images\\bluePieces\\blueHidden.png"));
                    }
                }

            }
        }
    }


    /**
     * initialize each team, blue and red
     * @param p the team we want to initialize
     * @param color, we use it to see if it is the blue or the red team
     */
    void initializePices(Piece[] p, Color color){
        if (color == Color.blue){
            p[0] = new ImmovablePiece(0,0,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\flagB.png");
            for (int i = 1; i < 7; i++) {
                p[i] = new ImmovablePiece(0,1,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\trapB.png");
            }
            for (int i = 7; i < 11; i++) {
                p[i] =new MoveablePiece(0,2,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\scoutB.png");
            }
            for (int i = 11; i < 16; i++) {
                p[i] =new MoveablePiece(0,3,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\dwarfB.png");
            }
            for (int i = 16; i < 18; i++) {
                p[i] =new MoveablePiece(0,4,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\elfB.png");
            }
            for (int i = 18; i < 20; i++) {
                p[i] =new MoveablePiece(0,5,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\yeti.png");
            }
            for (int i = 20; i < 22; i++) {
                p[i] =new MoveablePiece(0,6,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\sorceressB.png");
            }
            for (int i = 22; i < 25; i++) {
                p[i] =new MoveablePiece(0,7,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\beastRiderB.png");
            }
            for (int i = 25; i < 27; i++) {
                p[i] =new MoveablePiece(0,8,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\knightB.png");
            }
            p[27] =new MoveablePiece(0,9,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\mageB.png");
            p[28] =new MoveablePiece(0,10,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\dragonB.png");
            p[29] =new MoveablePiece(0,1,color,"..\\Stratego\\Phase_A\\images\\images\\bluePieces\\slayerB.png");

        }else if(color == Color.red){
            p[0] = new ImmovablePiece(0,0,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\flagR.png");
            for (int i = 1; i < 7; i++) {
                p[i] = new ImmovablePiece(0,1,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\trapR.png");
            }
            for (int i = 7; i < 11; i++) {
                p[i] =new MoveablePiece(0,2,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\scoutB.png");
            }
            for (int i = 11; i < 16; i++) {
                p[i] =new MoveablePiece(0,3,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\dwarfR.png");
            }
            for (int i = 16; i < 18; i++) {
                p[i] =new MoveablePiece(0,4,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\elfR.png");
            }
            for (int i = 18; i < 20; i++) {
                p[i] =new MoveablePiece(0,5,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\lavaBeast.png");
            }
            for (int i = 20; i < 22; i++) {
                p[i] =new MoveablePiece(0,6,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\sorceressR.png");
            }
            for (int i = 22; i < 25; i++) {
                p[i] =new MoveablePiece(0,7,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\beastRiderR.png");
            }
            for (int i = 25; i < 27; i++) {
                p[i] =new MoveablePiece(0,8,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\knightR.png");
            }
            p[27] =new MoveablePiece(0,9,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\mageR.png");
            p[28] =new MoveablePiece(0,10,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\dragonR.png");
            p[29] =new MoveablePiece(0,1,color,"..\\Stratego\\Phase_A\\images\\images\\RedPieces\\slayerR.png");

        }

        addImages(p);
    }

    /**
     * add images to every button.
     * @param p
     */
    void addImages(Piece[] p){

        for (int i = 0; i < p.length; i++) {
            if (p[i].getColor() != Color.white) {
                p[i].setIcon(p[i].getImage());
            }
        }

    }

    /**
     *
     * @param p the total buttons we have
     * @param color the color of the pieces we want to change image
     */
    void putNormalimages(Piece[][] p, Color color){
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (p[i][j].getColor() != Color.white && color == p[i][j].getColor()) {
                    p[i][j].setIcon(p[i][j].getImage());
                }
            }

        }
    }

    /**
     * this method will initialize and get the buttons ready to use.
     */
    void initializeButtons(){

        view.lessArmyButton.addActionListener(this);
        view.notGoingBackButton.addActionListener(this);

        int rand;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (i < 3 ){
                    rand = RandomGenerator.getRandomVal(0,29);
                    while(blue[rand].getIsChoosenByRandom()){
                        rand = RandomGenerator.getRandomVal(0,29);
                    }
                    blue[rand].setChoosenByRandom(true);
                    butt[i][j] = blue[rand];
                }else if (i > 4){
                    rand = RandomGenerator.getRandomVal(0,29);
                    while(red[rand].getIsChoosenByRandom()){
                        rand = RandomGenerator.getRandomVal(0,29);
                    }
                    red[rand].setChoosenByRandom(true);
                    butt[i][j] = red[rand];

                }else {
                    butt[i][j] = new MoveablePiece(1, 1, Color.white,"hi");
                }



                // This if statement make the buttons that we cannot use yellow and unclickable.
                if(i == 3 && j == 2 || i == 3 && j ==3 || i == 4 && j == 2 || i == 4 && j ==3 ||
                        i == 3 && j == 6 || i == 4 && j ==6 || i == 3 && j == 7 || i == 4 && j ==7){

                    butt[i][j].setBackground(Color.yellow);
                    butt[i][j].setEnabled(false);
                    butt[i][j].setBorderPainted(false);
                    butt[i][j].setColor(Color.yellow);

                }else {
                    butt[i][j].setBackground(Color.white);
                    butt[i][j].setBorder(new LineBorder(Color.lightGray,1) );
                    butt[i][j].addActionListener(this);
                    butt[i][j].setBorder(new LineBorder(null));
                    makeButtNotSelected(butt[i][j]); //make button not selected
                }

            }
        }
    }


    /**
     * this method will add our buttons to the panel.
     */
    void addButtonsToPanel(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {

                this.view.background.add(butt[i][j]);

            }
        }
    }

    /**
     * set a green border to the buttons we can move
     * @param p
     * @param color
     */
    void setBorder(Piece p,Color color){

        if ( p.isEnabled() && (p.getColor() == Color.white || p.getColor() != color) && color!=Color.white &&
           ((color == Color.blue && board.getPlayer1Turn()) || (color == Color.red && !board.getPlayer1Turn())) ){

                p.setBorder(new LineBorder(Color.green,4) );
                p.setCanMoveThere(true);

        }

    }

    /**
     * remove the green border of the buttons
     */
    void removeBorder(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                butt[i][j].setBorder(new LineBorder(null));
                butt[i][j].setCanMoveThere(false);
            }
        }
    }

    /**
     * @return true if the second given piece win,
     * or false if the first piece given win.
     */
    boolean whichPieceWon(Piece p1, Piece p2)
    {
        if(p1 instanceof ImmovablePiece && p1.getPower() == 1){
            if (p2.getPower() == 3)
                return true;
            else
                return false;
        }else if (p1.getPower() == 1 && p2.getPower() == 10){
            return false;
        }else if (p1.getPower() == 10 && p2.getPower() == 1){
            return false;
        }else{
            if (p1.getPower() < p2.getPower())
                return true;
            else
                return false;
        }

    }

    /**
     * make button not selected
     * @param p
     */
    void makeButtNotSelected(Piece p){
        if (p.getColor() != Color.white)
            p.getModel().setSelected(false);
    }

    /**
     * make button selected
     * @param p
     */
    void makeButtSelected(Piece p){
        if (p.getColor() != Color.white)
            p.getModel().setSelected(true);
    }


    void saveGui(Player p){
        JFrame frame = new JFrame("Save me!");
        JPanel panel = new JPanel();

        panel.setBounds(0,0,400,400);
        panel.setLayout(new GridLayout());
        System.out.println(p.getCaptures());
        for (int k = 0; k < 30; k++) {
            if (p.getPersonalCaptures(k).getSImage() != ""){
                p.getPersonalCaptures(k).addActionListener(this);
                p.getPersonalCaptures(k).setIcon(p.getPersonalCaptures(k).getImage());
                System.out.println(p.getPersonalCaptures(k).getPower());
                panel.add(p.getPersonalCaptures(k));
            }
        }

        frame.add(panel);

        frame.setBounds(400,200,500,500);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    /**
     * here we will perform the action listener of the buttons.
     */
    public void actionPerformed(ActionEvent e) {


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (e.getSource() == butt[i][j]){
                    //if we click on the bordered button here we check if we want to move or we want to attack
                    if (butt[i][j].getCanMoveThere() && selected != null) {
                        if (selected.getColor() != butt[i][j].getColor() && butt[i][j].getColor() != Color.white) {
                            attack(butt[i][j], selected);
                            removeBorder();
                        } else {
                            moveButton(butt[i][j], selected);
                        }
                        makeButtNotSelected(selected);
                        removeBorder();
                    }
                    if (!(butt[i][j] instanceof ImmovablePiece)) {
                        //the first click on o button that we decide which button will be bordered and we can move or attack it.
                        if (!butt[i][j].getModel().isSelected() && !butt[i][j].getCanMoveThere()) {
                            removeBorder();
                            selected = butt[i][j];

                            //if it is a scout
                            if (butt[i][j].getPower() == 2) {
                                try {
                                    int n = 1;

                                    if (i != 7) {
                                        while (butt[i + n][j].getColor() == Color.white) {
                                            setBorder(butt[i + n][j], butt[i][j].getColor());
                                            n++;
                                        }
                                        if (butt[i + (n - 1)][j].isEnabled())
                                            setBorder(butt[i + n][j], butt[i][j].getColor());
                                    }
                                    if (j != 0) {
                                        n = 1;

                                        while (butt[i][j - n].getColor() == Color.white) {
                                            setBorder(butt[i][j - n], butt[i][j].getColor());
                                            n++;
                                        }
                                        if (butt[i][j - (n - 1)].isEnabled())
                                            setBorder(butt[i][j - n], butt[i][j].getColor());
                                    }
                                    if (j != 9) {
                                        n = 1;

                                        while (butt[i][j + n].getColor() == Color.white) {
                                            setBorder(butt[i][j + n], butt[i][j].getColor());
                                            n++;
                                        }
                                        if (butt[i][j + (n - 1)].isEnabled())
                                            setBorder(butt[i][j + n], butt[i][j].getColor());
                                    }
                                    if (i != 0) {
                                        n = 1;

                                        while (butt[i - n][j].getColor() == Color.white) {
                                            setBorder(butt[i - n][j], butt[i][j].getColor());
                                            n++;
                                        }
                                        if (butt[i - (n - 1)][j].isEnabled())
                                            setBorder(butt[i - n][j], butt[i][j].getColor());
                                    }
                                } catch (Exception d) {
                                    System.out.println("Corner Piece!");
                                }

                            } else {
                                //if it is anything but not a scout
                                // check if the button isn't on any corner
                                if (i != 7) setBorder(butt[i + 1][j], butt[i][j].getColor());
                                if (j != 0) setBorder(butt[i][j - 1], butt[i][j].getColor());
                                if (j != 9) setBorder(butt[i][j + 1], butt[i][j].getColor());
                                if (i != 0) setBorder(butt[i - 1][j], butt[i][j].getColor());
                            }

                            //make button selected
                            makeButtSelected(butt[i][j]);

                        } else {

                            removeBorder();

                            //make button not selected
                            makeButtNotSelected(butt[i][j]);

                        }
                    }

                }



                // the saves
                if (butt[i][j].getColor() == Color.blue && i == 7 && !butt[i][j].getHasSaved()){

                    ///////

                    saveGui(p2);

                    ///////

                    butt[i][j].setHasSaved(true);
                }

                if (butt[i][j].getColor() == Color.red && i == 0 && !butt[i][j].getHasSaved()){

                    ///////

                    saveGui(p1);

                    ///////

                    butt[i][j].setHasSaved(true);
                }

            }
        }
        
    }

}

