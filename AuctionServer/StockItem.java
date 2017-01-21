import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

/*
*This class keeps the details of each stock item.
 */


public class StockItem {

    public String symbol;
    public String securityName;
    public double price;



    public StockItem(String symbol, String securityName, double price){
        this.symbol=symbol;
        this.securityName=securityName;
        this.price=price;
    }


    public boolean processOffer(double offerPrice){
        if(this.price>=offerPrice) return false;
        this.price=offerPrice;

        return true;
    }



}
