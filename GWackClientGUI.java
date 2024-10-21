import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;



public class GWackClientGUI{
    public static JFrame f;


    public static void main(String args[]) {
    
        
        f = new JFrame();
        Connection c = new Connection();
        Chat h = new Chat();
        Members m = new Members();
        MsgStream s = new MsgStream();
        m.setVisible(true);
        c.setVisible(true);
       
       

        f.setTitle("GWACK: GW Slack!"); // sets title that appears on the top bar
        f.setSize(1000, 1000);          // sets the size (in pixels) of the frame
        f.setLocation(100, 100);      // sets the top-left corner of the window on the desktop
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes it so that closing window exits program
        f.setVisible(true);
        f.setBackground(Color.BLUE);


        f.add(c, BorderLayout.NORTH);
        f.add(s, BorderLayout.CENTER);
        f.add(h, BorderLayout.SOUTH);
        f.add(m, BorderLayout.WEST);
       
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

       
    }

    }
    

