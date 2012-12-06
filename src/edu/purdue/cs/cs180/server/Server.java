package edu.purdue.cs.cs180.server;

import edu.purdue.cs.cs180.channel.Channel;
import edu.purdue.cs.cs180.channel.ChannelException;
import edu.purdue.cs.cs180.channel.MessageListener;
import edu.purdue.cs.cs180.channel.TCPChannel;
import edu.purdue.cs.cs180.common.Message;

public class Server implements MessageListener {
  private Channel channel = null;
  private Data data = new Data();
  
  public Server(int port, String mode, int sleepTime) {
    channel = new TCPChannel(port);
    channel.setMessageListener(this);
    Matcher matcher = new Matcher(data, sleepTime, mode, channel);
    matcher.start();
  }
  
  @Override
  public void messageReceived(String messageString, int clientID) {
    assert (messageString != null);
    System.out.println(clientID + ": " + messageString);
    Message message = new Message(messageString, clientID);
    
    synchronized(data){
      if(message.getType().equals("Request")){
        data.addRequest(message);
      }else if(message.getType().equals("Response")){
        data.addResponse(message);
      }
    }
    
    try{
      channel.sendMessage("Searching:", clientID);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    new Server(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));//creates a server object that uses a port passed from the comsole
  }
}
