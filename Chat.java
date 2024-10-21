import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;


public class Chat extends JPanel{
    public static JTextField chat;

    
    public Chat() {
        super();
        
        chat = new JTextField("\n", 50);
 

        JPanel dpanel = new JPanel(new FlowLayout()); 
        dpanel.add(new JLabel("Message: "), BorderLayout.NORTH);
        dpanel.add(chat, BorderLayout.SOUTH);


        JButton b = new JButton("Send");
        dpanel.add(b);

        chat.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) { 
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    Connection.pw.write(chat.getText() + "\n");
                    Connection.pw.flush();
                    chat.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent arg0) {}
        });

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (b.isEnabled()) {
                    Connection.pw.write(chat.getText() + "\n");
                    Connection.pw.flush();
                    chat.setText("");
                }
            }
        });
       
        this.add(dpanel, BorderLayout.SOUTH);
    }

    
    public String getChat(){
        return (String)chat.getText();  
    }

 
  
 
}

    
         
    
        

    


