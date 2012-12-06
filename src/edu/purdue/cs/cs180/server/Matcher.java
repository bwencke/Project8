package edu.purdue.cs.cs180.server;

import edu.purdue.cs.cs180.channel.*;
import edu.purdue.cs.cs180.common.Message;

public class Matcher extends Thread {
  private long sleepTime;
  private DataFeeder feeder;
  private String matchingType;
  private Channel channel;
  
  public Matcher(DataFeeder f, long sleep, String matchingType, Channel channel) {
    sleepTime = sleep;
    feeder = f;
    this.matchingType = matchingType;
    this.channel = channel;
  }
  
  @Override
  public void run() {
    while (feeder.hasNextResponse() && feeder.hasNextRequest()){
      synchronized(feeder){
        if (matchingType.equalsIgnoreCase("FCSC")){
          messageSender(feeder.getFirstResponse(),feeder.getFirstRequest());
          feeder.removeFirstResponse();
          feeder.removeFirstRequest();
          
        }else if (matchingType.equalsIgnoreCase("mostRecent")){
          messageSender(feeder.getLastResponse(),feeder.getLastRequest());
          feeder.removeLastResponse();
          feeder.removeLastRequest();
        }
      }
    }
    
    try{
      sleep(sleepTime);
    }catch(Exception e){
      //needs catch code
    }
  }
  
  private void messageSender(Message response, Message request){
    
    try{
      channel.sendMessage("Assigned:" + response.getInfo(), request.getClientID());//sends a message to the requester telling them a responder has been assigned and what team that responder is on
      channel.sendMessage("Assigned:" + request.getInfo(), response.getClientID());//tells the longest waiting responder they have been assigned and the location of the requester they are to help
      
    }catch(ChannelException e){//catch for all that channel stuff
      System.out.println("Exception occured while sending a message after being contacted by a requester!!!");
    }
  }
}
