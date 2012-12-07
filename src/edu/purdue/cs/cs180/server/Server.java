/**
 * Project 8 -- Server
 * A server that interacts with the Request and Response GUI's.  The server itself has a CLI and can be 
 * terminated by typing exit into the console.  To run the server requires one argmuent, the port it is
 * to run on.
 *
 * @author Alex Aralis
 *
 * @recitation 004 Julian James Stephen
 *
 * @date 16/6/12
 *
 */

package edu.purdue.cs.cs180.server;

import edu.purdue.cs.cs180.channel.Channel;
import edu.purdue.cs.cs180.channel.ChannelException;
import edu.purdue.cs.cs180.channel.MessageListener;
import edu.purdue.cs.cs180.channel.TCPChannel;
import edu.purdue.cs.cs180.common.Message;

public class Server implements MessageListener {
  private Channel channel = null; 
  private Data data = new Data(); // an object that implements data interface and uses two linked lists to store data in between matching cycles
  
  public Server(int port, String mode, int sleepTime) {
    channel = new TCPChannel(port);
    channel.setMessageListener(this);
    Matcher matcher = new Matcher(data, sleepTime, mode, channel);//an object that spins of a new thread that matches the requesters and receivers every set period of time.
    matcher.start();
  }
  
  /**
   * A method called when a message is received by the channel object.
   *
   * @param messageString is a string sent in the message
   * @param int clientID is the ID of the sender
   */
  @Override
  public void messageReceived(String messageString, int clientID) {
    assert (messageString != null);
    System.out.println(clientID + ": " + messageString);
    Message message = new Message(messageString, clientID);
    
    synchronized(data){
      switch(message.getType()){
        case Request:
          data.addRequest(message);
          break;
        case Response:
          data.addResponse(message);
          break;
        default:
          System.out.println("Unknown message type.");
      }
    }
    
    try{
      channel.sendMessage("Searching:", clientID);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    new Server(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));//creates a server object that uses a port, mode, and sleep time passed from the comsole
  }
}
