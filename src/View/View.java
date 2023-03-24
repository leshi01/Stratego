package View;


import javax.swing.*;

import java.awt.*;


public class View{

    public MyFrame frame;
    public MyBackgroundPanel background;
    MyMenuPanel menu;


    public JCheckBox lessArmyButton, notGoingBackButton;

    public JPanel rulesPanel, statisticPanel, capturesPanel;

    public JLabel menuLabel1,menuLabel2,menuLabel3,playerTurnLabel,winRateLabel,savesLabel,roundsLabel,capturesLabel,totalCapturesLabel;

    private boolean lessArmy = false;

    /**
     * The constructor of the class View, here we will make
     * the initializations that we need for the GUI of the project.
     */
    public View(){

        frame = new MyFrame();
        background = new MyBackgroundPanel();
        menu = new MyMenuPanel();

        initializePanels();
        initializeLabels();

        initializeCheckBox();


        frame.add(background);
        frame.add(menu);

        frame.setVisible(true);
    }


    /**
     * this method will initialize the checkboxes
     */
    void initializeCheckBox(){
        lessArmyButton = new JCheckBox("Μειωμένος στρατός");
        lessArmyButton.setFocusable(false);
        lessArmyButton.setBackground(null);
        lessArmyButton.setHorizontalTextPosition(SwingConstants.LEFT);
        lessArmyButton.setForeground(Color.lightGray);
        lessArmyButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        rulesPanel.add(lessArmyButton);

//////////////////////////////////////////////////////////////////////////////////////////
        notGoingBackButton = new JCheckBox("Καμία Υποχώριση");
        notGoingBackButton.setFocusable(false);
        notGoingBackButton.setBackground(null);
        notGoingBackButton.setHorizontalTextPosition(SwingConstants.LEFT);
        notGoingBackButton.setForeground(Color.lightGray);
        notGoingBackButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        rulesPanel.add(notGoingBackButton);
    }



    /**
     * here we will initialize the panels of the gui.
     */
    void initializePanels(){
        //rules
        rulesPanel = new JPanel();
        rulesPanel.setBounds(8,40,325,100);
        rulesPanel.setBackground(Color.darkGray);
        rulesPanel.setLayout(new GridLayout(2,1));
        menu.add(rulesPanel);

        //statistics
        statisticPanel = new JPanel();
        statisticPanel.setBounds(8,240,325,120);
        statisticPanel.setBackground(Color.darkGray);
        statisticPanel.setLayout(null);
        menu.add(statisticPanel);


        //captures
        capturesPanel = new JPanel();
        capturesPanel.setBounds(8,490,325,270);
        capturesPanel.setBackground(Color.darkGray);
        capturesPanel.setLayout(null);
        menu.add(capturesPanel);
    }

    /**
     * here we will initialize the labels of the gui.
     */
    void initializeLabels(){

        //label 1
        menuLabel1 = new JLabel("Ενεργοί κανόνες",JLabel.CENTER);
        menuLabel1.setBounds(0,0,350,40);
        menuLabel1.setFont(new Font("Verdana", Font.BOLD, 22));
        menu.add(menuLabel1);

        //label 2
        menuLabel2 = new JLabel("Στατιστικά",JLabel.CENTER);
        menuLabel2.setBounds(0,200,350,40);
        menuLabel2.setFont(new Font("Verdana", Font.BOLD, 22));
        menuLabel2.setVerticalAlignment(JLabel.TOP);
        menu.add(menuLabel2);


        //label 3
        menuLabel3 = new JLabel("Αιχμαλωτίσεις",JLabel.CENTER);
        menuLabel3.setBounds(0,450,350,40);
        menuLabel3.setFont(new Font("Verdana", Font.BOLD, 22));
        menuLabel3.setVerticalAlignment(JLabel.TOP);
        menu.add(menuLabel3);

        //playerTurn label
        playerTurnLabel = new JLabel();
        playerTurnLabel.setBounds(80,10,200,40);
        playerTurnLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        playerTurnLabel.setForeground(Color.lightGray);
        statisticPanel.add(playerTurnLabel);

        //winRate label
        winRateLabel = new JLabel();
        winRateLabel.setBounds(10,50,300,20);
        winRateLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        winRateLabel.setForeground(Color.lightGray);
        statisticPanel.add(winRateLabel);

        //saves Label
        savesLabel = new JLabel();
        savesLabel.setBounds(10,90,150,20);
        savesLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        savesLabel.setForeground(Color.lightGray);
        statisticPanel.add(savesLabel);

        //rounds Label
        roundsLabel = new JLabel();
        roundsLabel.setBounds(200,90,100,20);
        roundsLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        roundsLabel.setForeground(Color.lightGray);
        statisticPanel.add(roundsLabel);

        //captures Label
        capturesLabel = new JLabel();
        capturesLabel.setLayout(new GridLayout(4,3));
        capturesLabel.setBounds(5,5,315,230);
        capturesPanel.add(capturesLabel);


        //totalCaptures Label
        totalCapturesLabel = new JLabel();
        totalCapturesLabel.setBounds(15,237,300,30);
        totalCapturesLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        totalCapturesLabel.setForeground(Color.lightGray);
        capturesPanel.add(totalCapturesLabel);

    }


}
