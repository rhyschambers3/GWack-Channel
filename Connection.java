import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Connection extends JPanel {

    private JTextField name;
    private JTextField ipAddy;
    private JTextField portNum;
    public ServerSocket serverSock;
    public static PrintWriter pw;
    public Socket sock; 
    ClientHandler handler;


    public Connection() {
        super();

        name = new JTextField("", 10);
        ipAddy   = new JTextField("", 10);
        portNum   = new JTextField("", 10);


        // Top panel
        JPanel dpanel = new JPanel(new FlowLayout()); 
        dpanel.add(new JLabel("Name: "));
        dpanel.add(name);
        
        dpanel.add(new JLabel(" IP Address: "));
        dpanel.add(ipAddy);

        dpanel.add(new JLabel("Port: "));
        dpanel.add(portNum);

        
    


        //add a button listener for connect/disconnect
        JButton b = new JButton("Connect");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (b.getText().equals("Connect")) { 
                    if (name.getText().equals("") || portNum.getText().equals("") || ipAddy.getText().equals("")){
                        createWindow();
                    }
                    sock=null;        
                    try{
                        
                        sock = new Socket(ipAddy.getText(),Integer.parseInt(portNum.getText()));
                        pw = new PrintWriter(sock.getOutputStream());
                        pw.write("SECRET\n");
                        pw.write("3c3c4ac618656ae32b7f3431e75f7b26b1a14a87\n");
                        pw.write("NAME\n");
                        pw.write(name.getText() + "\n");
                        
                        pw.flush(); //flush the output (recall PrintWriters buffer)
                        
                        b.setText("Disconnect");
                        name.setEditable(false); //can't edit name or IP while connected
                        ipAddy.setEditable(false);
                        
                        //get messages
                        handler = new ClientHandler(sock, MsgStream.chatMsgs, Members.members);
                        handler.start();
                
                }
                 catch(Exception p){
                        System.err.print(p.getMessage());
                        System.exit(1);
                    }

     
                }
                else {
                    b.setText("Connect");
                    name.setEditable(true);
                    ipAddy.setEditable(true);
                
                    pw.close(); //close the stream
                    // handler.in.close();
                    try {
                        sock.close();
                    } catch (IOException e2) {
                        // do more logging if appropiate
                    }
                
                }
                
            }
            
        });

        dpanel.add(b);

        this.add(dpanel);
        
    }



    private static void createWindow() {  
        
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();  
        panel.setLayout(layout);   

     
        JOptionPane.showMessageDialog(GWackClientGUI.f, "Please ensure compliance!",
               "Swing Tester", JOptionPane.ERROR_MESSAGE);
        GWackClientGUI.f.getContentPane().add(panel, BorderLayout.CENTER);   

     }


}









