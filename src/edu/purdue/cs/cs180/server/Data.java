package edu.purdue.cs.cs180.server;

import java.util.LinkedList;
import edu.purdue.cs.cs180.common.*;

public class Data implements DataFeeder {
  private LinkedList<Message> responderMessages;
  private LinkedList<Message> requesterMessages;
  
  public Data(){
    responderMessages = new LinkedList<Message>();
    requesterMessages = new LinkedList<Message>();
  }
  
  public void addRequest(Message newM){
    requesterMessages.add(newM);
  }
  
  public void addResponse(Message newM){
    responderMessages.add(newM);
  }
  
  public Message getFirstRequest(){
    return requesterMessages.getFirst();
  }
  
  public Message getFirstResponse(){
    return responderMessages.getFirst();
  }
  
  public Message getLastRequest(){
    return requesterMessages.getLast();
  }
  
  public Message getLastResponse(){
    return responderMessages.getLast();
  }
  
  public boolean hasNextRequest(){
    return requesterMessages.size() > 0;
  }
  
  public boolean hasNextResponse(){
    return responderMessages.size() > 0;
  }
  
  public void removeFirstRequest(){
    requesterMessages.removeFirst();
  }
  
  public void removeFirstResponse(){
    responderMessages.removeFirst();
  }
  
  public void removeLastRequest(){
    requesterMessages.removeLast();
  }
  
  public void removeLastResponse(){
    responderMessages.removeLast();
  }
}