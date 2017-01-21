import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
*This is the Main class.
* It holds the main() method and acts as the Main Server.
 */


public class AuctionServer extends JFrame{

    public static final int BASE_PORT = 2000;

    public ServerSocket serverSocket;

    public static Map<String,StockItem> stocks;

    public static Map<String,Integer> symbolIDS;

    public static String[][] stockDetails;

    public static JTable stockTable;

    private static ArrayList<Offer> records;
    //This is what keeps track of all the valid offers made. Each item in this ArrayList is an object of class "Offer"
    //and holds the name of the bidder, the bid price, the time the bid was made and the security to which the bid was made.

    private static AuctionServer as;


    public AuctionServer(int socket){
        super("Auction Server");
        setResizable(false);
        try{
            records = new ArrayList<Offer>();
            this.serverSocket=new ServerSocket(socket);
        }
        catch(IOException e){
            System.err.println(e);
        }

    }

    public AuctionServer(){
        this(BASE_PORT);
    }


    public static void main(String[] args) {

        BufferedReader bf;

        stocks = new HashMap<String, StockItem>();

        symbolIDS = new HashMap<String, Integer>();


        symbolIDS.put("FB",0);
        symbolIDS.put("VRTU",1);
        symbolIDS.put("MSFT",2);
        symbolIDS.put("GOOGL",3);
        symbolIDS.put("YHOO",4);
        symbolIDS.put("XLNX",5);
        symbolIDS.put("TSLA",6);
        symbolIDS.put("TXN",7);


        stockDetails = new String[8][3];

        try {
            bf = new BufferedReader(new FileReader("stocks.csv"));
            String line;
            line=bf.readLine();
            line=bf.readLine();
            int count=0;
            while(line!=null) {
                String[] a = line.split(",");
                stocks.put(a[0], new StockItem(a[0], a[1], Double.parseDouble(a[2])));
                if(symbolIDS.get(a[0])!=null){
                    int index=symbolIDS.get(a[0]);
                    stockDetails[index][0] = a[0];
                    stockDetails[index][1] = a[1];
                    stockDetails[index][2] = a[2];
                }
                line=bf.readLine();
                count++;
            }
        }
        catch(Exception e){
            System.err.println(e);
        }

        as=new AuctionServer(BASE_PORT);
        as.createAndShowGUI();
        as.loop();

    }

    public static synchronized void printMessage(String msg){
        System.out.println(msg);
    }

    public static synchronized boolean isValidSecurity(String symbol){
        return !(stocks.get(symbol)==null);
    }

    public static synchronized boolean handleOffer(String clientName, String symbol, long time, double price){
        StockItem currentSecurity = stocks.get(symbol);
        if(currentSecurity.processOffer(price)){
            Offer offer=new Offer(time, clientName, price);
            records.add(offer);
            if(symbolIDS.get(symbol)!=null){
                int index=symbolIDS.get(symbol);
                stockDetails[index][2]=Double.toString(price);
                stockTable.getModel().setValueAt(Double.toString(price),index,2);
            }
            return true;
        }
        return false;
    }

    public static synchronized String getStockDetails(String symbol){
        StockItem currentSecurity = stocks.get(symbol);
        return "Security Name: "+currentSecurity.securityName+" | Symbol: "+currentSecurity.symbol+" | Current Price: "+currentSecurity.price;
    }


    public void loop(){

        while(true){
            try{
                Socket socket=serverSocket.accept();
                UserServer us=new UserServer(socket);
                us.handleUser();
            }
            catch(IOException e){
                System.err.println(e);
            }

        }
    }

    public void createAndShowGUI() {


        as.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane=as.getContentPane();

        String columnNames[] = {"Symbol", "Security Name", "Price"};

        stockTable = new JTable(stockDetails, columnNames);

        stockTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(stockTable);
        stockTable.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(1000,150));



        pane.add(new JLabel("Current Values of Stocks"),BorderLayout.NORTH);
        pane.add(scrollPane, BorderLayout.CENTER);

        as.pack();
        as.setVisible(true);
    }

}
