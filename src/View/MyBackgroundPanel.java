package View;

import javax.swing.*;
import java.awt.*;

public class MyBackgroundPanel extends JPanel {

    /**
     * Inside this constructor we will do some
     * initializations that we need for our Background Label.
     */
    MyBackgroundPanel(){
        this.setBounds(5,0,950,760);
        this.setBackground(Color.darkGray);
        this.setLayout(new GridLayout(8,10) );
    }

}
