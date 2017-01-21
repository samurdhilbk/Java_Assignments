

/*
*This class keep the details of each valid offer made.
 */

public class Offer {

    public long time;
    public String bidderName;
    public double offerPrice;

    public Offer(long time,String bidderName,double offerPrice){
        this.time=time;
        this.bidderName=bidderName;
        this.offerPrice=offerPrice;
    }

}
