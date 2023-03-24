package View;

import javax.swing.*;

public class MyFrame extends JFrame {



    /**
     * Inside this constructor we will do some
     * initializations that we need for our frame.
     */
    MyFrame(){
        this.setBounds(130,10,1300,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Stratego");
        this.setLayout(null);
        this.setResizable(false);

    }
    
}
