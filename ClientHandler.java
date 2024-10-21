    import java.net.*;
    import javax.swing.JTextArea;
    import java.io.*;

    public class ClientHandler extends Thread{

    Socket sock;
    JTextArea messages;
    JTextArea users;
    BufferedReader in;

    public ClientHandler(Socket sock, JTextArea messages, JTextArea users){
        this.sock=sock;
        this.messages = messages;
        this.users = users;
    }

    public void run(){
       
        BufferedReader in=null;
        try{
            
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

    //read and echo back forever!
    while(!sock.isClosed()){
        String msg = in.readLine();
        if(msg == null) break; //read null, remote closed
        if(msg.equals("START_CLIENT_LIST")){
        String clientList = "";
        msg = in.readLine();

        while(!msg.equals("END_CLIENT_LIST")){
            clientList += msg +"\n";
            msg = in.readLine();
        }
        users.setText(clientList);
        }
        
        else{
            messages.append(msg + "\n");
        }

    }
    // //close the connections
    // in.close();

    }catch(Exception e){}

    //note the loss of the connection
    //
    System.out.println("Connection lost: "+sock.getRemoteSocketAddress());
    }

    }