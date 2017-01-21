import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.Socket;

/*
*This class deals with each user and interacts with them.
 */


public class UserServer extends AuctionServer implements Runnable{

    public static final int WAIT_NAME = 0;
    public static final int NAME_DONE = 1;
    public static final int SECURITY_DONE = 2;

    public static final String WELCOME_MESSAGE = "Welcome to the Auction Server!\n\n\n**Enter \"quit\" to exit the server at any time.**\n\nTo start bidding please enter your name: ";
    public static final String NAME_DONE_MSG = "\nYou can now start bidding.\n\nPlease enter the symbol of the stock you want to bid on: ";
    public static final String SYMBOL_SUCCESS = "\nSecurity selected. Here are the current stock details.\n";
    public static final String INVALID_BID_INPUT = "Invalid bid. Please enter a valid price: ";
    public static final String BID_RECORDED_FAILURE = "\nPlease enter a bid higher than the current stock price: \n";
    public static final String BID_RECORDED_SUCCESS    = "\nYour bid was successful.";

    private int currentState;
    private String clientName;
    private String securityName;


    public Socket socket;


    public UserServer(Socket socket){
        this.socket=socket;
    }

    public void handleUser(){
        Thread th=new Thread(this);
        th.start();
    }

    public void run(){
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.print(WELCOME_MESSAGE);
            out.flush();

            String line, outline;
            for(line = in.readLine(); line != null && !line.equals("quit"); line = in.readLine()) {

                switch(currentState) {
                    case WAIT_NAME:
                        currentState = NAME_DONE;
                        clientName   = line;
                        outline = "Hello "+ clientName+ "! "+NAME_DONE_MSG;
                        break;
                    case NAME_DONE:
                        if(isValidSecurity(line)){
                            currentState = SECURITY_DONE;
                            outline = SYMBOL_SUCCESS + getStockDetails(line) + "\n\nPlease enter a bid: ";
                            securityName = line;
                        }
                        else{
                            outline = "\n-1\n";
                        }
                        break;
                    case SECURITY_DONE:
                        try{
                            double bidPrice = Double.parseDouble(line);
                            if(handleOffer(clientName,securityName,System.currentTimeMillis(),bidPrice)){
                                outline = BID_RECORDED_SUCCESS+ "\n"+ getStockDetails(securityName) + "\n\nEnter another bid if needed(else quit):";
                            }
                            else outline = "\n"+ getStockDetails(securityName) + "\n"+ BID_RECORDED_FAILURE ;
                        }
                        catch(Exception e){
                            outline = "\n" + getStockDetails(securityName) + "\n" + INVALID_BID_INPUT;
                        }
                        break;
                    default:
                        System.out.println("Undefined state");
                        return;
                }

                out.print(outline);
                out.flush();

            }

            out.close();
            in.close();
            this.socket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

}
