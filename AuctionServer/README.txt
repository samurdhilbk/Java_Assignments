The AuctionServer.java file contains the main() method.

Compile all the .java files together and run AuctionServer to start the server.

The GUI contains the real-time stock prices of the specified items and refreshes automatically every time a valid bid is made.
Hence, there is no need to referesh the items manually.

It is possible to connect to the server remotely through port 2000. 

The server interface is pretty self-explanatory.
After the bidder's name is provided the required security must be selected. It is not possible to change the bidder name or the 
bidding security after valid entries have been provided to the same.
Every bid made which is higher than the current stock price will be recorded. After each query(valid or invalid) the current stock
price of the selected security will be displayed along with it's symbol and name.

The world "quit" can be entered(without quotes) at any point in the program to exit it.
All valid bids made will be recorded and cannot be undone.




